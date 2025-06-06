import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private static DatabaseService instance;
    private static Connection connection;

    private DatabaseService() {
        try {
            String url = "jdbc:mysql://localhost:3306/fitness_db";
            String user = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    // ----------- CRUD pentru Sport -----------
    public void createSport(Sport sport) throws SQLException {
        String sql = "INSERT INTO sporturi (nume, calorii_pe_minut) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, sport.getNume());
            stmt.setDouble(2, sport.getCaloriiPeMinut());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) sport.setId(rs.getInt(1));
        }
    }

    public Sport readSport(int id) throws SQLException {
        String sql = "SELECT * FROM sporturi WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? new Sport(rs.getInt("id"), rs.getString("nume"), rs.getDouble("calorii_pe_minut")) : null;
        }
    }

    public void updateSport(Sport sport) throws SQLException {
        String sql = "UPDATE sporturi SET nume = ?, calorii_pe_minut = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sport.getNume());
            stmt.setDouble(2, sport.getCaloriiPeMinut());
            stmt.setInt(3, sport.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteSport(int id) throws SQLException {
        String sql = "DELETE FROM sporturi WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // ----------- CRUD pentru AntrenamentSpecial -----------
    public void createAntrenamentSpecial(AntrenamentSpecial antrenament) throws SQLException {
        String sql = "INSERT INTO antrenamente_speciale (tip, dificultate, durata) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, antrenament.getTip());
            stmt.setString(2, antrenament.getDificultate());
            stmt.setInt(3, antrenament.getDurata());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) antrenament.setId(rs.getInt(1));
        }
    }

    public AntrenamentSpecial readAntrenamentSpecial(int id) throws SQLException {
        String sql = "SELECT * FROM antrenamente_speciale WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? new AntrenamentSpecial(rs.getInt("id"), rs.getString("tip"), rs.getString("dificultate"), rs.getInt("durata")) : null;
        }
    }

    public void updateAntrenamentSpecial(AntrenamentSpecial antrenament) throws SQLException {
        String sql = "UPDATE antrenamente_speciale SET tip = ?, dificultate = ?, durata = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, antrenament.getTip());
            stmt.setString(2, antrenament.getDificultate());
            stmt.setInt(3, antrenament.getDurata());
            stmt.setInt(4, antrenament.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteAntrenamentSpecial(int id) throws SQLException {
        String sql = "DELETE FROM antrenamente_speciale WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // ----------- CRUD pentru Utilizator -----------
    public boolean userExists(String nume) throws SQLException {
        String sql = "SELECT COUNT(*) FROM utilizatori WHERE nume = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nume);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }

    public Utilizator readUserByName(String nume) throws SQLException {
        String sql = "SELECT * FROM utilizatori WHERE nume = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nume);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilizator(rs.getString("nume"), rs.getInt("id"), rs.getString("rol"), rs.getInt("varsta"), rs.getDouble("greutate"), rs.getDouble("inaltime"));
            }
            return null;
        }
    }

    public void createUser(String nume, String role) throws SQLException {
        String sql = "INSERT INTO utilizatori (nume, rol) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nume);
            stmt.setString(2, role);
            stmt.executeUpdate();
        }
    }

    public Utilizator readUser(int id) throws SQLException {
        String sql = "SELECT * FROM utilizatori WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilizator(rs.getString("nume"), rs.getInt("id"), rs.getString("rol"), rs.getInt("varsta"), rs.getDouble("greutate"), rs.getDouble("inaltime"));
            }
            return null;
        }
    }

    public void updateUser(Utilizator user) throws SQLException {
        String sql = "UPDATE utilizatori SET nume = ?, rol = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getNume());
            stmt.setString(2, user.getRol());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM utilizatori WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // ----------- CRUD pentru Antrenor -----------
    public void createAntrenor(Antrenor antrenor) throws SQLException {
        String sql = "INSERT INTO antrenori (nume, specializare) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, antrenor.getNume());
            stmt.setString(2, antrenor.getSpecializare());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) antrenor.setId(rs.getInt(1));
        }
    }

    public Antrenor readAntrenor(int id) throws SQLException {
        String sql = "SELECT * FROM antrenori WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Antrenor(rs.getInt("id"), rs.getString("nume"), rs.getString("specializare"));
            }
            return null;
        }
    }

    public List<Antrenor> readAllAntrenori() throws SQLException {
        String sql = "SELECT * FROM antrenori";
        List<Antrenor> antrenori = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                antrenori.add(new Antrenor(rs.getInt("id"), rs.getString("nume"), rs.getString("specializare")));
            }
        }
        return antrenori;
    }

    public void updateAntrenor(Antrenor antrenor) throws SQLException {
        String sql = "UPDATE antrenori SET nume = ?, specializare = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, antrenor.getNume());
            stmt.setString(2, antrenor.getSpecializare());
            stmt.setInt(3, antrenor.getId());
            stmt.executeUpdate();
        }
    }

    public void updateAntrenor(int id, Antrenor antrenor) throws SQLException {
        updateAntrenor(antrenor);
    }

    public void deleteAntrenor(int id) throws SQLException {
        String sql = "DELETE FROM antrenori WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // ----------- CRUD pentru Obiectiv -----------
    public void createObiectiv(Obiectiv obiectiv) throws SQLException {
        String sql = "INSERT INTO obiective (tip, valoare_tinta, data_limita) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, obiectiv.getTip());
            stmt.setDouble(2, obiectiv.getValoareTinta());
            stmt.setDate(3, Date.valueOf(obiectiv.getDataLimita()));
            stmt.executeUpdate();
        }
    }

    public List<Obiectiv> readAllObiective() throws SQLException {
        String sql = "SELECT * FROM obiective";
        List<Obiectiv> obiective = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                obiective.add(new Obiectiv(
                    rs.getString("tip"),
                    rs.getDouble("valoare_tinta"),
                    rs.getDate("data_limita").toLocalDate()
                ));
            }
        }
        return obiective;
    }

    public void updateObiectiv(int id, Obiectiv obiectiv) throws SQLException {
        String sql = "UPDATE obiective SET tip = ?, valoare_tinta = ?, data_limita = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, obiectiv.getTip());
            stmt.setDouble(2, obiectiv.getValoareTinta());
            stmt.setDate(3, Date.valueOf(obiectiv.getDataLimita()));
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    public void deleteObiectiv(int id) throws SQLException {
        String sql = "DELETE FROM obiective WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}