package be.daniel.naturalcornerandroid.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by ivymike on 13/09/16.
 */
public class Panier {
    private int id, nbArticles;
    private BigDecimal prixTotal;
    private ArrayList<LignePanier> lignes;

    public Panier() {
        lignes=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbArticles() {
        return nbArticles;
    }

    public void setNbArticles(int nbArticles) {
        this.nbArticles = nbArticles;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public boolean ajouterLigne(LignePanier lPanier){
        if(lignes!=null)
            return lignes.add(lPanier);
        return false;
    }
    public boolean retirerLigne(LignePanier lignePanier){
        if(lignes!=null)
            return this.lignes.remove(lignePanier);
        return false;
    }
    public boolean viderPanier(){
        if(lignes!=null) {
            lignes.clear();
            return lignes.isEmpty();
        }else
            return false;
    }

    public void recalculer(){
        BigDecimal total= new BigDecimal(.0);
        for(LignePanier ligne : lignes){
            total=total.add(ligne.getMontant());
        }
        setPrixTotal(total);
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", nbArticles=" + nbArticles +
                ", prixTotal=" + prixTotal +
                '}';
    }
}
