package be.daniel.naturalcornerandroid.model;

import java.io.Serializable;

/**
 * Created by ivymike on 14/09/16.
 */
public class Rayon implements Serializable {
    private int id;
    private String denomination;

    public Rayon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    @Override
    public String toString() {
        return "Rayon{" +
                "id=" + id +
                ", denomination='" + denomination + '\'' +
                '}';
    }
}
