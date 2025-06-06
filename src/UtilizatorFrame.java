import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UtilizatorFrame extends JFrame {
    private Utilizator utilizator;

    private JTextField numeField;
    private JTextField varstaField;
    private JTextField greutateField;
    private JTextField inaltimeField;

    public UtilizatorFrame(Utilizator utilizator) {
        this.utilizator = utilizator;

        setTitle("Utilizator - " + utilizator.getNume());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Nume:"));
        numeField = new JTextField(utilizator.getNume());
        panel.add(numeField);

        panel.add(new JLabel("Vârstă:"));
        varstaField = new JTextField(String.valueOf(utilizator.getVarsta()));
        panel.add(varstaField);

        panel.add(new JLabel("Greutate:"));
        greutateField = new JTextField(String.valueOf(utilizator.getGreutate()));
        panel.add(greutateField);

        panel.add(new JLabel("Înălțime:"));
        inaltimeField = new JTextField(String.valueOf(utilizator.getInaltime()));
        panel.add(inaltimeField);

        JButton saveButton = new JButton("Salvează");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utilizator.setNume(numeField.getText());
                try {
                    utilizator.setVarsta(Integer.parseInt(varstaField.getText()));
                    utilizator.setGreutate(Double.parseDouble(greutateField.getText()));
                    utilizator.setInaltime(Double.parseDouble(inaltimeField.getText()));
                    // TODO: Save changes to database if needed
                    JOptionPane.showMessageDialog(UtilizatorFrame.this, "Date salvate cu succes.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UtilizatorFrame.this, "Date invalide.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(saveButton);

        add(panel);
    }
}