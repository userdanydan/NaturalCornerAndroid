package be.daniel.naturalcornerandroid.naturalcornerapplication;

import android.app.Application;

import be.daniel.naturalcornerandroid.model.Panier;
import be.daniel.naturalcornerandroid.model.User;

/**
 * Created by ivymike on 13/09/16.
 */
public class NaturalCornerApplication extends Application {

    private Panier panier;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
}
