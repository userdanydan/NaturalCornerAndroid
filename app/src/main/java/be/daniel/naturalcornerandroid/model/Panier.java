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
        int nbArticle=0;
        for(LignePanier ligne : lignes){
            nbArticle+=ligne.getQuantite();
        }
        return nbArticle;
    }

    public void setNbArticles(int nbArticles) {
        this.nbArticles = nbArticles;
        recalculer();
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public boolean ajouterLigne(LignePanier lPanier){
        if(lignes!=null) {
            boolean addOK = lignes.add(lPanier);
            recalculer();
            return addOK;
        }
        return false;
    }
    public boolean retirerLigne(LignePanier lignePanier){
        if(lignes!=null) {
            boolean removeOK = this.lignes.remove(lignePanier);
            return removeOK;
        }
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

    public ArrayList<LignePanier> getLignes() {
        return lignes;
    }

    @Override
    public String toString() {
        String str ="";
        for(LignePanier ligne : this.lignes){
            str += ligne.toString();
            str +=  "\r\n";
        }
        return "Panier{" +
                "id=" + getId()+
                ", nbArticles=" + getNbArticles() +
                ", prixTotal=" + getPrixTotal() +
                ", contenu du panier=" + str +
                '}';
    }
}
