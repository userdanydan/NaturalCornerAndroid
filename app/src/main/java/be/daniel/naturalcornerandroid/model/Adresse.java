package be.daniel.naturalcornerandroid.model;

import java.io.Serializable;

/**
 * Created by ivymike on 14/09/16.
 */
public class Adresse implements Serializable {
    private int id;
    private String rue, numero, nomBoite, codePostal, localite, pays;


    public Adresse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomBoite() {
        return nomBoite;
    }

    public void setNomBoite(String nomBoite) {
        this.nomBoite = nomBoite;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", rue='" + rue + '\'' +
                ", numero='" + numero + '\'' +
                ", nomBoite='" + nomBoite + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", localite='" + localite + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
