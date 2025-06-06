import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private String role;

    public LoginFrame(String role) {
        this.role = role;
        setTitle("Login - " + role);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JTextField numeField = new JTextField(20);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume = numeField.getText();
                try {
                    if (DatabaseService.getInstance().userExists(nume)) {
                        if (role.equals("antrenor")) {
                            // Open AntrenorFrame for trainer role
                            new AntrenorFrame().setVisible(true);
                        } else if (role.equals("utilizator")) {
                            Utilizator user = DatabaseService.getInstance().readUserByName(nume);
                            if (user != null) {
                                new UtilizatorFrame(user).setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(LoginFrame.this, "Utilizator nu a fost găsit.");
                            }
                        }
                        dispose(); // Close login frame
                    } else {
                        int choice = JOptionPane.showConfirmDialog(
                                LoginFrame.this,
                                "Utilizator nou. Doriți să-l creați?",
                                "Confirmare",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (choice == JOptionPane.YES_OPTION) {
                            try {
                                DatabaseService.getInstance().createUser(nume, role);
                                JOptionPane.showMessageDialog(LoginFrame.this, "Utilizator creat cu succes.");
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                AuditService.getInstance().logAction("LOGIN");
            }
        });

        panel.add(numeField);
        panel.add(loginButton);
        add(panel);
    }
}