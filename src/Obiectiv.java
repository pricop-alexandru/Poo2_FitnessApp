import java.time.LocalDate;

public class Obiectiv {
    private String tip;
    private double valoareTinta;
    private LocalDate dataLimita;

    public Obiectiv(String tip, double valoareTinta, LocalDate dataLimita) {
        this.tip = tip;
        this.valoareTinta = valoareTinta;
        this.dataLimita = dataLimita;
    }

    // Getters
    public String getTip() { return tip; }
    public double getValoareTinta() { return valoareTinta; }
    public LocalDate getDataLimita() { return dataLimita; }

    // Setters
    // public void setTip(String tip) { this.tip = tip; }
    // public void setValoareTinta(double valoareTinta) { this.valoareTinta = valoareTinta; }
    // public void setDataLimita(LocalDate dataLimita) { this.dataLimita = dataLimita; }

    // Am realizat tarziu ca multi setters din aceste parti de proiect nu mai sunt folositi fix pentru ca modific in database.
}