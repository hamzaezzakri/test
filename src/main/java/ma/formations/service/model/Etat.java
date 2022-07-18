package ma.formations.service.model;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/20/2022 6:23 PM
 */
public enum Etat {

    EN_ATTENTE("en attente"),
    FAIT("fait"),
    ANNULE("annule");

    private final String etat;

    Etat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Etat{" +
                "etat='" + etat + '\'' +
                '}';
    }
}
