package be.daniel.naturalcornerandroid.model;

import java.io.Serializable;

/**
 * Created by ivymike on 14/09/16.
 */
public class Categorie implements Serializable {
    private int id;
    private String denomination;
    private Categorie categorie;

    public Categorie() {
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", denomination='" + denomination + '\'' +
                ", categorie=" + categorie +
                '}';
    }
}
