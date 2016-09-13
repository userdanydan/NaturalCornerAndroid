package be.daniel.naturalcornerandroid.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by ivymike on 13/09/16.
 */
public class LignePanier {
    private int id, quantite;
    private BigDecimal montant;
    private ArrayList<Article> articles;

    public LignePanier() {
        articles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return articles.size();
    }


    public BigDecimal getMontant() {
        BigDecimal total = new BigDecimal(.0);
        for(Article article : articles){
            total = total.add(article.getPrixUnitaire());
        }
        return total;
    }

    @Override
    public String toString() {
        return "LignePanier{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", montant=" + montant +
                '}';
    }
}
