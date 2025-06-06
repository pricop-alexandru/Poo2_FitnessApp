import java.time.LocalDate;
import java.util.*;

public class FitnessService {
    private Map<String, Utilizator> utilizatori = new HashMap<>();
    private List<Activitate> activitati = new ArrayList<>();
    private Set<Obiectiv> obiective = new TreeSet<>(Comparator.comparing(Obiectiv::getDataLimita));
    private List<Antrenor> antrenori = new ArrayList<>();

    // Adaugare utilizator (inlocuit de baza de date
    // public void adaugaUtilizator(Utilizator user) {
    //    utilizatori.put(user.getNume(), user);
    //}

    public void adaugaAntrenor(Antrenor antrenor) {
        antrenori.add(antrenor);
    }
    public void asigneazaUtilizatorLaAntrenor(String userId, String numeAntrenor) {
        Utilizator user = utilizatori.get(userId);
        Antrenor antrenor = antrenori.stream()
                .filter(a -> a.getNume().equals(numeAntrenor))
                .findFirst()
                .orElse(null);

        if (user != null && antrenor != null) {
            antrenor.adaugaUtilizator(user);
        }
    }

    // Adaugare activitate și calcul calorii
    public void adaugaActivitate(String userId, Activitate activitate) {
        Utilizator user = utilizatori.get(userId);
        if (user != null) {
            activitate.calculeazaCaloriiArse(user.getGreutate());
            user.adaugaActivitate(activitate);
            activitati.add(activitate);
        }
    }

    // Generare raport saptamanal
    public void genereazaRaport(String userId) {
        Utilizator user = utilizatori.get(userId);
        if (user == null) return;

        System.out.println("Raport saptamanal pentru " + user.getNume());
        user.getListaActivitati().stream()
                .filter(a -> a.getData().isAfter(LocalDate.now().minusWeeks(1)))
                .forEach(a -> System.out.println(a.getTip() + ": " + a.getCaloriiArse() + " calorii"));
    }
}