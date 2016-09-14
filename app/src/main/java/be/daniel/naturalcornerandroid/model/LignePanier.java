package be.daniel.naturalcornerandroid.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by ivymike on 13/09/16.
 */
public class LignePanier {
    private int id, quantite;
    private BigDecimal montant;
    private Article article;

    public LignePanier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
        setMontant(this.article.getPrixUnitaire().multiply(new BigDecimal(quantite)));
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getMontant() {
        return article.getPrixUnitaire().multiply(new BigDecimal(quantite));
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "LignePanier{" +
                "id=" + getId() +
                ", quantite=" + getQuantite() +
                ", montant=" + getMontant() +
                '}';
    }
}
