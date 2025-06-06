import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoleSelectionFrame extends JFrame {
    public RoleSelectionFrame() {
        setTitle("Select Role");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton antrenorButton = new JButton("Antrenor");
        JButton utilizatorButton = new JButton("Utilizator");

        antrenorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame("antrenor").setVisible(true);
                dispose();
            }
        });

        utilizatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame("utilizator").setVisible(true);
                dispose();
            }
        });

        panel.add(antrenorButton);
        panel.add(utilizatorButton);

        add(panel);
    }
}