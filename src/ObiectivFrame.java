import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ObiectivFrame extends JFrame {
    private JTextField tipField;
    private JTextField valoareTintaField;
    private JTextField dataLimitaField;
    private JTextField idField;
    private JButton createButton, readButton, updateButton, deleteButton;
    private JTextArea outputArea;

    public ObiectivFrame() {
        setTitle("Gestionare Obiective");
        setSize(600, 400);
        setLayout(new FlowLayout());

        idField = new JTextField(5);
        tipField = new JTextField(20);
        valoareTintaField = new JTextField(20);
        dataLimitaField = new JTextField(10);

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
        add(new JLabel("Valoare țintă:"));
        add(valoareTintaField);
        add(new JLabel("Data limită (YYYY-MM-DD):"));
        add(dataLimitaField);

        add(createButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);
        add(outputArea);

        createButton.addActionListener(e -> {
            try {
                String tip = tipField.getText();
                double valoareTinta = Double.parseDouble(valoareTintaField.getText());
                LocalDate dataLimita = LocalDate.parse(dataLimitaField.getText());
                Obiectiv obiectiv = new Obiectiv(tip, valoareTinta, dataLimita);
                DatabaseService.getInstance().createObiectiv(obiectiv);
                AuditService.getInstance().logAction("CREATE_OBIECTIV");
                outputArea.setText("Obiectiv creat cu succes.");
            } catch (NumberFormatException | DateTimeParseException ex) {
                outputArea.setText("Date invalide: " + ex.getMessage());
            } catch (SQLException ex) {
                outputArea.setText("Eroare la baza de date: " + ex.getMessage());
            }
        });

        readButton.addActionListener(e -> {
            try {
                List<Obiectiv> obiective = DatabaseService.getInstance().readAllObiective();
                StringBuilder sb = new StringBuilder();
                for (Obiectiv o : obiective) {
                    sb.append("Tip: ").append(o.getTip())
                      .append(", Valoare țintă: ").append(o.getValoareTinta())
                      .append(", Data limită: ").append(o.getDataLimita())
                      .append("\n");
                }
                outputArea.setText(sb.toString());
            } catch (SQLException ex) {
                outputArea.setText("Eroare la baza de date: " + ex.getMessage());
            }
        });

        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String tip = tipField.getText();
                double valoareTinta = Double.parseDouble(valoareTintaField.getText());
                LocalDate dataLimita = LocalDate.parse(dataLimitaField.getText());
                Obiectiv obiectiv = new Obiectiv(tip, valoareTinta, dataLimita);
                DatabaseService.getInstance().updateObiectiv(id, obiectiv);
                AuditService.getInstance().logAction("UPDATE_OBIECTIV");
                outputArea.setText("Obiectiv actualizat cu succes.");
            } catch (NumberFormatException | DateTimeParseException ex) {
                outputArea.setText("Date invalide: " + ex.getMessage());
            } catch (SQLException ex) {
                outputArea.setText("Eroare la baza de date: " + ex.getMessage());
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                DatabaseService.getInstance().deleteObiectiv(id);
                AuditService.getInstance().logAction("DELETE_OBIECTIV");
                outputArea.setText("Obiectiv șters cu succes.");
            } catch (NumberFormatException ex) {
                outputArea.setText("ID invalid: " + ex.getMessage());
            } catch (SQLException ex) {
                outputArea.setText("Eroare la baza de date: " + ex.getMessage());
            }
        });
    }
}