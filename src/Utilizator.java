import java.util.ArrayList;
import java.util.List;

public class Utilizator {
    private String nume;
    private int Id;
    private String rol;
    private int varsta;
    private double greutate;
    private double inaltime;
    private List<Activitate> listaActivitati = new ArrayList<>();

    public Utilizator(String nume, int Id, String rol, int varsta, double greutate, double inaltime) {
        this.nume = nume;
        this.Id = Id;
        this.rol = rol;
        this.varsta = varsta;
        this.greutate = greutate;
        this.inaltime = inaltime;
    }

    // Getters si Setters
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public int getId() { return Id; }
    public void setId(int Id) { this.Id = Id; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public int getVarsta() { return varsta; }
    public void setVarsta(int varsta) { this.varsta = varsta; }
    public double getGreutate() { return greutate; }
    public void setGreutate(double greutate) { this.greutate = greutate; }
    public double getInaltime() { return inaltime; }
    public void setInaltime(double inaltime) { this.inaltime = inaltime; }

    public List<Activitate> getListaActivitati() { return listaActivitati; }

    public void adaugaActivitate(Activitate activitate) {
        listaActivitati.add(activitate);
    }
}