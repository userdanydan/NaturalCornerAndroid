package be.daniel.naturalcornerandroid.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ivymike on 08/09/16.
 */
public class Article implements Serializable{
    private int id;
    private String denomination, commentaire, categorie;
    private BigDecimal prixUnitaire;
    private boolean selected;

    public Article() {

    }
    public Article(String denomination, String commentaire, String categorie, BigDecimal prixUnitaire) {

        this.denomination = denomination;
        this.commentaire = commentaire;
        this.categorie = categorie;
        this.prixUnitaire = prixUnitaire;
    }

    public int getId() {
        return id;
    }

    public String getDenomination() {
        return denomination;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getCategorie() {
        return categorie;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!getDenomination().equals(article.getDenomination())) return false;
        return getCategorie() != null ? getCategorie().equals(article.getCategorie()) : article.getCategorie() == null;

    }

    @Override
    public int hashCode() {
        return getDenomination().hashCode();
    }

    @Override
    public String toString() {
        return "Article{" +
                "denomination='" + denomination + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", categorie='" + categorie + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }

}
