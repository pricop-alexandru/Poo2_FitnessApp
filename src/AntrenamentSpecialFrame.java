import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class AntrenamentSpecialFrame extends JFrame {
    private JTextField tipField;
    private JTextField dificultateField;
    private JTextField durataField;
    private JTextField idField;
    private JButton createButton, readButton, updateButton, deleteButton;
    private JTextArea outputArea;

    public AntrenamentSpecialFrame() {
        setTitle("Gestionare Antrenament Special");
        setSize(600, 400);
        setLayout(new FlowLayout());

        idField = new JTextField(5);
        tipField = new JTextField(20);
        dificultateField = new JTextField(20);
        durataField = new JTextField(5);

        createButton = new JButton("Creare");
        readButton = new JButton("Citire");
        updateButton = new JButton("Actualizare");
        deleteButton = new JButton("Stergere");

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Tip:"));
        add(tipField);
        add(new JLabel("Dificultate:"));
        add(dificultateField);
        add(new JLabel("Durata:"));
        add(durataField);

        add(createButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);
        add(outputArea);

        createButton.addActionListener(e -> {
            try {
                String tip = tipField.getText();
                String dificultate = dificultateField.getText();
                int durata = Integer.parseInt(durataField.getText());
                AntrenamentSpecial antrenament = new AntrenamentSpecial(0, tip, dificultate, durata);
                DatabaseService.getInstance().createAntrenamentSpecial(antrenament);
                AuditService.getInstance().logAction("CREATE_ANTRENAMENT_SPECIAL");
                outputArea.setText("Antrenament special creat cu succes.");
            } catch (NumberFormatException ex) {
                outputArea.setText("Date invalide: " + ex.getMessage());
            } catch (SQLException ex) {
                outputArea.setText("Eroare la baza de date: " + ex.getMessage());
            }
        });

        readButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                AntrenamentSpecial antrenament = DatabaseService.getInstance().readAntrenamentSpecial(id);
                if (antrenament != null) {
                    outputArea.setText("ID: " + antrenament.getId() + "\nTip: " + antrenament.getTip() +
                            "\nDificultate: " + antrenament.getDificultate() + "\nDurata: " + antrenament.getDurata());
                } else {
                    outputArea.setText("Antrenament special cu ID-ul " + id + " nu a fost găsit.");
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("ID invalid: " + ex.getMessage());
            } catch (SQLException ex) {
                outputArea.setText("Eroare la baza de date: " + ex.getMessage());
            }
        });

        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String tip = tipField.getText();
                String dificultate = dificultateField.getText();
                int durata = Integer.parseInt(durataField.getText());
                AntrenamentSpecial antrenament = new AntrenamentSpecial(id, tip, dificultate, durata);
                DatabaseService.getInstance().updateAntrenamentSpecial(antrenament);
                AuditService.getInstance().logAction("UPDATE_ANTRENAMENT_SPECIAL");
                outputArea.setText("Antrenament special actualizat cu succes.");
            } catch (NumberFormatException ex) {
                outputArea.setText("Date invalide: " + ex.getMessage());
            } catch (SQLException ex) {
                outputArea.setText("Eroare la baza de date: " + ex.getMessage());
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                DatabaseService.getInstance().deleteAntrenamentSpecial(id);
                AuditService.getInstance().logAction("DELETE_ANTRENAMENT_SPECIAL");
                outputArea.setText("Antrenament special șters cu succes.");
            } catch (NumberFormatException ex) {
                outputArea.setText("ID invalid: " + ex.getMessage());
            } catch (SQLException ex) {
                outputArea.setText("Eroare la baza de date: " + ex.getMessage());
            }
        });
    }
}