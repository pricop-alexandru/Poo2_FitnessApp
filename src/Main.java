import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Pornește interfața Swing pe firul de despachetare (thread-safe)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RoleSelectionFrame().setVisible(true);
            }
        });
    }
}