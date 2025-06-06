import java.util.*;

public class Antrenor {
    private int Id;
    private String nume;
    private String specializare; // ex: "Cardio", "Forță"
    private List<Utilizator> utilizatoriAsignati = new ArrayList<>();

    public Antrenor(int Id, String nume, String specializare) {
        this.Id = Id;
        this.nume = nume;
        this.specializare = specializare;
    }

    // Adaugă un utilizator la lista de asignări
    public void adaugaUtilizator(Utilizator user) {
        utilizatoriAsignati.add(user);
    }

    // Getters si setters
    public int getId() {return Id;}
    public void setId(int Id) {this.Id = Id;}
    public String getNume() { return nume; }
    public void setNume(String Nume) {this.nume = Nume;}
    public String getSpecializare() { return specializare; }
    public void setSpecializare(String Specializare) {this.specializare = Specializare;}
    public List<Utilizator> getUtilizatoriAsignati() { return utilizatoriAsignati; }
}