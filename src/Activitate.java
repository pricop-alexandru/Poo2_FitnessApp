import java.time.LocalDate;

public abstract class Activitate {
    private String tip;
    private int durata; // minute
    private double caloriiArse;
    private LocalDate data;

    public Activitate(String tip, int durata, LocalDate data) {
        this.tip = tip;
        this.durata = durata;
        this.data = data;
    }

    // Metoda abstracta pentru calculul caloriilor
    public abstract void calculeazaCaloriiArse(double greutateUtilizator);

    // Getters
    public String getTip() { return tip; }
    public int getDurata() { return durata; }
    public double getCaloriiArse() { return caloriiArse; }
    public LocalDate getData() { return data; }

    protected void setCaloriiArse(double calorii) {
        this.caloriiArse = calorii;
    }
}