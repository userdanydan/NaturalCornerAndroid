package be.daniel.naturalcornerandroid.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ivymike on 14/09/16.
 */
public class Commande implements Serializable {
    private int id;
    private Date date;
    private int delaisCollecte;
    private BigDecimal montantTotal;
    private Panier panier;

    public Commande() {
    }

    public void addPanier(Panier panier){
        if(panier!=null)
            this.panier=panier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDelaisCollecte() {
        return delaisCollecte;
    }

    public void setDelaisCollecte(int delaisCollecte) {
        this.delaisCollecte = delaisCollecte;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", date=" + date +
                ", delaisCollecte=" + delaisCollecte +
                ", montantTotal=" + montantTotal +
                '}';
    }
}
