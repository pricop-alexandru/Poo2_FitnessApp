public class AntrenamentSpecial {
    private int id;
    private String tip;
    private String dificultate;
    private int durata;

    // Constructor
    public AntrenamentSpecial(int id, String tip, String dificultate, int durata) {
        this.id = id;
        this.tip = tip;
        this.dificultate = dificultate;
        this.durata = durata;
    }

    // Getters și Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }
    public String getDificultate() { return dificultate; }
    public void setDificultate(String dificultate) { this.dificultate = dificultate; }
    public int getDurata() { return durata; }
    public void setDurata(int durata) { this.durata = durata; }
}