import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class AntrenorFrame extends JFrame {
    private JTextField idField;
    private JTextField numeField;
    private JTextField specializareField;
    private JButton createButton, readButton, updateButton, deleteButton;
    private JTextArea outputArea;

    public AntrenorFrame() {
        setTitle("Gestionare Antrenori");
        setSize(600, 400);
        setLayout(new FlowLayout());

        idField = new JTextField(5);
        numeField = new JTextField(20);
        specializareField = new JTextField(20);

        createButton = new JButton("Creare");
        readButton = new JButton("Citire");
        updateButton = new JButton("Actualizare");
        deleteButton = new JButton("Stergere");

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Nume:"));
        add(numeField);
        add(new JLabel("Specializare:"));
        add(specializareField);

        add(createButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);
        add(outputArea);

        createButton.addActionListener(e -> {
            try {
                String nume = numeField.getText();
                String specializare = specializareField.getText();
                Antrenor antrenor = new Antrenor(0, nume, specializare);
                DatabaseService.getInstance().createAntrenor(antrenor);
                AuditService.getInstance().logAction("CREATE_ANTRENOR");
                outputArea.setText("Antrenor creat cu succes.");
            } catch (Exception ex) {
                outputArea.setText("Eroare: " + ex.getMessage());
            }
        });

        readButton.addActionListener(e -> {
            try {
                List<Antrenor> antrenori = DatabaseService.getInstance().readAllAntrenori();
                StringBuilder sb = new StringBuilder();
                for (Antrenor a : antrenori) {
                    sb.append("ID: ").append(a.getId())
                      .append(", Nume: ").append(a.getNume())
                      .append(", Specializare: ").append(a.getSpecializare())
                      .append("\n");
                }
                outputArea.setText(sb.toString());
            } catch (Exception ex) {
                outputArea.setText("Eroare: " + ex.getMessage());
            }
        });

        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String nume = numeField.getText();
                String specializare = specializareField.getText();
                Antrenor antrenor = new Antrenor(id, nume, specializare);
                DatabaseService.getInstance().updateAntrenor(id, antrenor);
                AuditService.getInstance().logAction("UPDATE_ANTRENOR");
                outputArea.setText("Antrenor actualizat cu succes.");
            } catch (Exception ex) {
                outputArea.setText("Eroare: " + ex.getMessage());
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                DatabaseService.getInstance().deleteAntrenor(id);
                AuditService.getInstance().logAction("DELETE_ANTRENOR");
                outputArea.setText("Antrenor șters cu succes.");
            } catch (Exception ex) {
                outputArea.setText("Eroare: " + ex.getMessage());
            }
        });
    }
}