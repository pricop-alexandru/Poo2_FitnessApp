import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditService {
    private static AuditService instance;

    private AuditService() {}

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void logAction(String actionName) {
        try (FileWriter writer = new FileWriter("audit.csv", true)) {
            writer.write(actionName + "," + LocalDateTime.now() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}