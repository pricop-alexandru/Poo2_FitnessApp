public class Sport {
    private int id;
    private String nume;
    private double caloriiPeMinut;

    // Constructor
    public Sport(int id, String nume, double caloriiPeMinut) {
        this.id = id;
        this.nume = nume;
        this.caloriiPeMinut = caloriiPeMinut;
    }

    // Getters și Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public double getCaloriiPeMinut() { return caloriiPeMinut; }
    public void setCaloriiPeMinut(double caloriiPeMinut) { this.caloriiPeMinut = caloriiPeMinut; }
}