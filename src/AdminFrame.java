import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminFrame extends JFrame {
    public AdminFrame() {
        setTitle("Admin Panel");
        setSize(400, 600);

        JPanel panel = new JPanel();
        JTextField numeSportField = new JTextField(20);
        JTextField caloriiField = new JTextField(20);
        JButton addSportButton = new JButton("Adaugă Sport");

        JButton updateSportButton = new JButton("Actualizează Sport");
        JButton deleteSportButton = new JButton("Șterge Sport");

        JButton manageAntrenamentButton = new JButton("Gestionează Antrenamente Speciale");
        JButton manageUtilizatorButton = new JButton("Gestionează Utilizatori");
        JButton manageAntrenorButton = new JButton("Gestionează Antrenori");
        JButton manageObiectivButton = new JButton("Gestionează Obiective");

        JButton updateUserButton = new JButton("Actualizează Utilizator");
        JButton deleteUserButton = new JButton("Șterge Utilizator");

        addSportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Sport sport = new Sport(
                            0,
                            numeSportField.getText(),
                            Double.parseDouble(caloriiField.getText())
                    );
                    DatabaseService.getInstance().createSport(sport);
                    AuditService.getInstance().logAction("CREATE_SPORT");
                    JOptionPane.showMessageDialog(AdminFrame.this, "Sport adăugat!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            AdminFrame.this,
                            "Eroare: " + ex.getMessage(),
                            "Eroare",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        updateSportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nume = numeSportField.getText();
                    double calorii = Double.parseDouble(caloriiField.getText());
                    // For update, we need the sport id, but since UI doesn't have it, this is a placeholder
                    // You may want to add an ID field for sport update
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Introduceți ID-ul sportului de actualizat:"));
                    Sport sport = new Sport(id, nume, calorii);
                    DatabaseService.getInstance().updateSport(sport);
                    AuditService.getInstance().logAction("UPDATE_SPORT");
                    JOptionPane.showMessageDialog(AdminFrame.this, "Sport actualizat!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            AdminFrame.this,
                            "Eroare: " + ex.getMessage(),
                            "Eroare",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        deleteSportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Introduceți ID-ul sportului de șters:"));
                    DatabaseService.getInstance().deleteSport(id);
                    AuditService.getInstance().logAction("DELETE_SPORT");
                    JOptionPane.showMessageDialog(AdminFrame.this, "Sport șters!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            AdminFrame.this,
                            "Eroare: " + ex.getMessage(),
                            "Eroare",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        manageAntrenamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AntrenamentSpecialFrame().setVisible(true);
            }
        });

        manageUtilizatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = JOptionPane.showInputDialog(AdminFrame.this, "Introduceți numele utilizatorului:");
                if (nume != null && !nume.trim().isEmpty()) {
                    try {
                        Utilizator user = DatabaseService.getInstance().readUserByName(nume.trim());
                        if (user != null) {
                            new UtilizatorFrame(user).setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(AdminFrame.this, "Utilizatorul nu a fost găsit.");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(AdminFrame.this, "Eroare la baza de date: " + ex.getMessage());
                    }
                }
            }
        });

        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nume = JOptionPane.showInputDialog(AdminFrame.this, "Introduceți numele utilizatorului:");
                    if (nume == null || nume.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(AdminFrame.this, "Nume invalid.");
                        return;
                    }
                    Utilizator user = DatabaseService.getInstance().readUserByName(nume.trim());
                    if (user == null) {
                        JOptionPane.showMessageDialog(AdminFrame.this, "Utilizatorul nu a fost găsit.");
                        return;
                    }
                    String newNume = JOptionPane.showInputDialog(AdminFrame.this, "Introduceți noul nume:");
                    String newRol = JOptionPane.showInputDialog(AdminFrame.this, "Introduceți noul rol:");
                    if (newNume != null && newRol != null) {
                        user.setNume(newNume);
                        user.setRol(newRol);
                        DatabaseService.getInstance().updateUser(user);
                        AuditService.getInstance().logAction("UPDATE_USER");
                        JOptionPane.showMessageDialog(AdminFrame.this, "Utilizator actualizat!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Eroare: " + ex.getMessage());
                }
            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(AdminFrame.this, "Introduceți ID-ul utilizatorului de șters:"));
                    DatabaseService.getInstance().deleteUser(id);
                    AuditService.getInstance().logAction("DELETE_USER");
                    JOptionPane.showMessageDialog(AdminFrame.this, "Utilizator șters!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Eroare: " + ex.getMessage());
                }
            }
        });

        manageAntrenorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AntrenorFrame().setVisible(true);
            }
        });

        manageObiectivButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ObiectivFrame().setVisible(true);
            }
        });

        panel.add(new JLabel("Nume Sport:"));
        panel.add(numeSportField);
        panel.add(new JLabel("Calorii pe minut:"));
        panel.add(caloriiField);
        panel.add(addSportButton);
        panel.add(updateSportButton);
        panel.add(deleteSportButton);
        panel.add(manageAntrenamentButton);
        panel.add(manageUtilizatorButton);
        panel.add(updateUserButton);
        panel.add(deleteUserButton);
        panel.add(manageAntrenorButton);
        panel.add(manageObiectivButton);
        add(panel);
    }
}