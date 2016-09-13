package be.daniel.naturalcornerandroid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import be.daniel.naturalcornerandroid.adapter.ArticleCursorAdapter;
import be.daniel.naturalcornerandroid.db.ArticleDAO;
import be.daniel.naturalcornerandroid.model.Article;
import be.daniel.naturalcornerandroid.task.LoadArticlesTask;


public class MainScreenActivity extends AppCompatActivity {
    private static final double LATITUDE_NATURAL_CORNER = 50.8436;
    private static final double LONGITUDE_NATURAL_CORNER = 4.3512;
    private ListView listView;
    private double latitude, longitude;
    private DrawerLayout drawerLayout;
    private HashMap<String, List<Article>> articlesCategories;
    private boolean listDisplayed, naturalAlphaOrderDone, naturalPriceOrderDone, isDisplayedByCategory;
    private String currentCategory;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        //receiveIntent();
        setList();
        setDrawer();
        setAdapter();
        geoLocalization();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setList() {
        ArticleDAO articleDAO = new ArticleDAO(getApplicationContext());
        articleDAO.openReadable();
        List<Article> articlesAll = articleDAO.readAll();

        Fragment fragment = new ArticleListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.list, fragment).commit();

        Bundle bundle = new Bundle();
        ArrayList<Article> list = new ArrayList(articlesAll);
        bundle.putSerializable("list_articles", new MySerializableList(list));
        fragment.setArguments(bundle);

        listDisplayed = true;
    }

    private void setDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_main_screen);
    }


    private void geoLocalization() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        ArrayList<LocationProvider> listProvider = new ArrayList<>();
        List<String> names = locationManager.getProviders(true);
        for (String name : names) {
            listProvider.add(locationManager.getProvider(name));
        }
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Location locationNaturalCorner = new Location("natural_corner");
                locationNaturalCorner.setLatitude(LATITUDE_NATURAL_CORNER);
                locationNaturalCorner.setLongitude(LONGITUDE_NATURAL_CORNER);
                float distanceFromNaturalCorner = location.distanceTo(locationNaturalCorner);
                if (distanceFromNaturalCorner <= 200.0) {
                    Toast.makeText(MainScreenActivity.this, "Vous êtes à proximité de NATURAL CORNER", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }

    private void receiveIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            switch (intent.getStringExtra("from")) {
                case LoginActivity.FROM_SIGN_IN:
                    Log.i("FROM", LoginActivity.FROM_SIGN_IN + " to " + this.toString());
                    break;
                case LoginActivity.FROM_CONTINUE_WITHOUT_SIGNING_IN:
                    Log.i("FROM", LoginActivity.FROM_CONTINUE_WITHOUT_SIGNING_IN + " to " + this.toString());
                    break;
            }
        }
    }

    private void setAdapter() {
        listView = (ListView) findViewById(R.id.lv_main_screen_list);
        ArticleDAO articleDAO = new ArticleDAO(getApplicationContext());
        articleDAO.openReadable();
        List<Article> articlesAll = articleDAO.readAll();
        Log.i("ARTICLE_ALL", articlesAll.toString());
        articlesCategories = new HashMap<>();
        Set<String> categories = new HashSet<>();
        for (Article article : articlesAll) {
            categories.add(article.getCategorie());
        }
        for (String cat : categories) {
            List<Article> listCategorie = new ArrayList<Article>();
            for (Article article : articlesAll) {
                if (article.getCategorie().equals(cat)) {
                    listCategorie.add(article);
                }
            }
            articlesCategories.put(cat + " (" + listCategorie.size() + ")", listCategorie);
        }
        String[] categoriesNames = articlesCategories.keySet().toArray(new String[articlesCategories.keySet().size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_activated_1,
                categoriesNames
        );
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = new ArticleListFragment();

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.list, fragment).commit();

                Bundle bundle = new Bundle();
                String cat = (String) parent.getItemAtPosition(position);
                setCurrentCategory(cat);
                ArrayList<Article> list = new ArrayList(MainScreenActivity.this.articlesCategories.get(cat));
                bundle.putSerializable("list_articles", new MySerializableList(list));
                fragment.setArguments(bundle);

                isDisplayedByCategory=true;
                listDisplayed = true;
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (listDisplayed) {
            menu.getItem(1).setVisible(true);
            menu.getItem(2).setVisible(true);
        } else {
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notification_menu:
                disableNotification();
                break;
            case R.id.sort_a_z_menu:
                sortByAlpha();
                break;
            case R.id.sort_price_menu:
                sortByPrice();
            default:
                break;
        }
        return true;
    }

    private void sortByPrice() {
        naturalPriceOrderDone = !naturalPriceOrderDone;
        Fragment fragment = new ArticleListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.list, fragment).commit();

        Bundle bundle = new Bundle();

        ArrayList<Article> list =null;

        if(isDisplayedByCategory) {
            String cat = getCurrentCategory();
            list = new ArrayList(MainScreenActivity.this.articlesCategories.get(cat));
        }else{
            ArticleDAO articleDAO = new ArticleDAO(getApplicationContext());
            articleDAO.openReadable();
            list = new ArrayList<Article>(articleDAO.readAll());
        }
        if(naturalPriceOrderDone) {
            Collections.sort(list, new Comparator<Article>() {
                @Override
                public int compare(Article art1, Article art2) {
                    return art1.getPrixUnitaire().compareTo(art2.getPrixUnitaire());
                }
            });
        }else{
            Collections.sort(list, new Comparator<Article>() {
                @Override
                public int compare(Article art1, Article art2) {
                    int resultComparePrice = art1.getPrixUnitaire().compareTo(art2.getPrixUnitaire());
                    if(resultComparePrice<0){
                        return 1;
                    }else if(resultComparePrice>0){
                        return -1;
                    }else{
                        return 0;
                    }
                }
            });
        }
        bundle.putSerializable("list_articles", new MySerializableList(list));
        fragment.setArguments(bundle);
    }

    private void sortByAlpha( ) {
        naturalAlphaOrderDone=!naturalAlphaOrderDone;
        Fragment fragment = new ArticleListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.list, fragment).commit();

        Bundle bundle = new Bundle();

        ArrayList<Article> list = null;
        if(isDisplayedByCategory) {
            String cat = getCurrentCategory();
            list = new ArrayList(MainScreenActivity.this.articlesCategories.get(cat));
        }else{
            ArticleDAO articleDAO = new ArticleDAO(getApplicationContext());
            articleDAO.openReadable();
            list = new ArrayList<Article>(articleDAO.readAll());
        }

        if(naturalAlphaOrderDone) {
            Collections.sort(list, new Comparator<Article>() {
                @Override
                public int compare(Article art1, Article art2) {
                    return art1.getDenomination().compareTo(art2.getDenomination());
                }
            });
        }else{
            Collections.sort(list, new Comparator<Article>() {
                @Override
                public int compare(Article art1, Article art2) {
                    int compareResult = art1.getDenomination().compareTo(art2.getDenomination());
                    if(compareResult>0){
                         return -1;
                     }else if (compareResult<0){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            });
        }
        bundle.putSerializable("list_articles", new MySerializableList(list));
        fragment.setArguments(bundle);
    }

    private void disableNotification() {
        //TODO DISABLE NOTIFICATIONS
        Toast.makeText(MainScreenActivity.this, "Notifications disabled (fake)", Toast.LENGTH_SHORT).show();
    }

    public String getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(String currentCategory) {
        this.currentCategory = currentCategory;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainScreen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://be.daniel.naturalcornerandroid/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainScreen Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://be.daniel.naturalcornerandroid/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

class MySerializableList extends AbstractList implements Serializable{

    private List<Article> listArticle;

    public MySerializableList(ArrayList<Article> list) {
        this.listArticle = new ArrayList<>(list);
    }

    @Override
    public Object get(int location) {
        return this.listArticle.get(location);
    }

    @Override
    public int size() {
        return listArticle.size();
    }

    List<Article> toArrayList(){
        return this.listArticle;
    }
}
























