package be.daniel.naturalcornerandroid;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Size;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import be.daniel.naturalcornerandroid.model.Commande;
import be.daniel.naturalcornerandroid.model.LignePanier;
import be.daniel.naturalcornerandroid.model.Panier;
import be.daniel.naturalcornerandroid.naturalcornerapplication.NaturalCornerApplication;

public class BasketActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private TextView tvTotal;
    private int counter;
    private Panier panier;
    private boolean listDisplayed;
    private NaturalCornerApplication nCApp;
    private Button btnBasketOrder;
    private Commande commande;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        setAppBar();
        nCApp = (NaturalCornerApplication)getApplication();
        if(nCApp !=null){
            panier = nCApp.getPanier();
            tvTotal = (TextView)findViewById(R.id.textViewTotalPrice);
            tvTotal.setText(String.valueOf(panier.getPrixTotal()));
            setUpTable();
        }
    }
    private void setAppBar() {
        if (nCApp == null)
            nCApp = (NaturalCornerApplication) getApplication();
        Toolbar myToolBar = (Toolbar) findViewById(R.id.toolbar_basket);
        if(myToolBar!=null) {
            myToolBar.setSubtitle("TOTAL : " +
                    ((nCApp.getPanier().getPrixTotal() == null) ? .0 : nCApp.getPanier().getPrixTotal()) + " €");
            setSupportActionBar(myToolBar);
        }
    }
    private void setUpTable() {
        tableLayout = (TableLayout) findViewById(R.id.table_layout_id);
        for(LignePanier lignePanier : panier.getLignes()) {

            final TableRow tableRow = new TableRow(getApplicationContext());
            final TableRow tableRow2 = new TableRow(getApplicationContext());


            final TextView tvArticleName = new TextView(getApplicationContext());
            tvArticleName.setText((counter+1) + " : " +lignePanier.getArticle().getDenomination());
            tvArticleName.setTextSize(24);
            tvArticleName.setPadding(16, 0, 16, 0);


            TextView tvArticlePrice = new TextView(getApplicationContext());
            tvArticlePrice.setText(String.valueOf(lignePanier.getArticle().getPrixUnitaire()) + " X " + lignePanier.getQuantite());
            tvArticlePrice.setTextSize(24);
            tvArticlePrice.setPadding(16, 0, 16, 0);
            tvArticlePrice.setGravity(Gravity.CENTER_HORIZONTAL);

            TextView tvArticleTotalPrice = new TextView(getApplicationContext());
            tvArticleTotalPrice.setText(String.valueOf(lignePanier.getMontant()));
            tvArticleTotalPrice.setTextSize(24);
            tvArticleTotalPrice.setPadding(16, 0, 16, 0);


            LinearLayout linearLayout = new LinearLayout(getApplicationContext());

            tableRow2.setGravity(Gravity.CENTER_HORIZONTAL);
            tableRow2.setPadding(16, 16, 16, 16);

            final EditText etChangeNbArticle = new EditText(getApplicationContext());
            etChangeNbArticle.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_VARIATION_NORMAL);
            etChangeNbArticle.setWidth(100);
            etChangeNbArticle.setTextSize(32);
            etChangeNbArticle.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            etChangeNbArticle.setText(Integer.toString(lignePanier.getQuantite()));


            Button btnChangeArticlePlus = new Button(getApplicationContext());

            btnChangeArticlePlus.setText("+");
            btnChangeArticlePlus.setTextSize(25);
            btnChangeArticlePlus.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

            btnChangeArticlePlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nbArticle = etChangeNbArticle.getText().toString();
                    int nbArticleInt = Integer.parseInt(nbArticle);
                    nbArticleInt++;
                    String text =tvArticleName.getText().toString();
                    panier.getLignes().get(Integer.valueOf(text.substring(0, text.indexOf(" ")))-1).setQuantite(nbArticleInt);
                    panier.recalculer();
                    String str = getSupportActionBar().getSubtitle().toString();
                    getSupportActionBar().setSubtitle(str.substring(0, str.indexOf(" : ")) + " : " + panier.getPrixTotal().toString() + " €");
                    tvTotal.setText(String.valueOf(panier.getPrixTotal()));
                    tableLayout.removeAllViews();
                    counter=0;
                    setUpTable();
                }
            });

            btnChangeArticlePlus.setGravity(Gravity.CENTER_HORIZONTAL);

            Button btnChangeArticleMinus = new Button(getApplicationContext());

            btnChangeArticleMinus.setText("-");
            btnChangeArticleMinus.setTextSize(25);
            btnChangeArticleMinus.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

            btnChangeArticleMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nbArticle = etChangeNbArticle.getText().toString();
                    int nbArticleInt = Integer.parseInt(nbArticle);
                    if(nbArticleInt>0)
                        nbArticleInt--;
                    String text =tvArticleName.getText().toString();
                    panier.getLignes().get(Integer.valueOf(text.substring(0, text.indexOf(" ")))-1).setQuantite(nbArticleInt);
                    panier.recalculer();
                    String str = getSupportActionBar().getSubtitle().toString();
                    getSupportActionBar().setSubtitle(str.substring(0, str.indexOf(" : ")) + " : " + panier.getPrixTotal().toString() + " €");
                    tvTotal.setText(String.valueOf(panier.getPrixTotal()));
                    tableLayout.removeAllViews();
                    counter=0;
                    setUpTable();
                }
            });

            linearLayout.addView(btnChangeArticleMinus);
            linearLayout.addView(etChangeNbArticle);
            linearLayout.addView(btnChangeArticlePlus);




//            Button btnChangeArticle = new Button(getApplicationContext());
//            btnChangeArticle.setText("Change");
//            btnChangeArticle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String nbArticle = etChangeNbArticle.getText().toString();
//                    int nbArticleInt = Integer.parseInt(nbArticle);
//                    String text =tvArticleName.getText().toString();
//                    panier.getLignes().get(Integer.valueOf(text.substring(0, text.indexOf(" ")))-1).setQuantite(nbArticleInt);
//                    panier.recalculer();
//                    String str = getSupportActionBar().getSubtitle().toString();
//                    getSupportActionBar().setSubtitle(str.substring(0, str.indexOf(" : ")) + " : " + panier.getPrixTotal().toString() + " €");
//                    tvTotal.setText(String.valueOf(panier.getPrixTotal()));
//                    tableLayout.removeAllViews();
//                    counter=0;
//                    setUpTable();
//                }
//            });
//
//            btnChangeArticle.setWidth(70);
//            btnChangeArticle.setHeight(75);


            tableRow.addView(tvArticleName);
            tableRow.addView(tvArticlePrice);
            tableRow.addView(tvArticleTotalPrice);

            tableRow2.addView(linearLayout);
           // tableRow2.addView(etChangeNbArticle);
           // tableRow2.addView(btnChangeArticle);

            tableRow.setMinimumHeight(100);

            tableLayout.addView(tableRow);
            tableLayout.addView(tableRow2);

            counter++;
        }
        btnBasketOrder = new Button(getApplicationContext());
        btnBasketOrder.setText(R.string.order);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        btnBasketOrder.setLayoutParams(lp);
        btnBasketOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commande = new Commande();
                commande.setPanier(panier);
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                commande.setDate(date);
                commande.setDelaisCollecte(3);
                commande.setMontantTotal(panier.getPrixTotal());
                Toast.makeText(BasketActivity.this, commande.toString(), Toast.LENGTH_SHORT).show();
                //TODO SEND ORDER TO THE SERVER.
                panier.viderPanier();
                commande=null;
                String str = getSupportActionBar().getSubtitle().toString();
                getSupportActionBar().setSubtitle(str.substring(0, str.indexOf(" : ")) + " : " + panier.getPrixTotal().toString() + " €");
                tvTotal.setText(String.valueOf(panier.getPrixTotal()));
            }
        });
        tableLayout.addView(btnBasketOrder);
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
            case R.id.see_basket:
                nCApp = (NaturalCornerApplication) getApplication();
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);
                break;
            //Toast.makeText(MainScreenActivity.this, nCApp.getPanier().toString(), Toast.LENGTH_LONG).show();
            case R.id.natural_corner_logo_mini:
                //Intent intent1 = new Intent(getApplicationContext(), ShowAccountActivity.class);
                //startActivity(intent1);
                Toast.makeText(BasketActivity.this, "BOUMSHAKALAKA!", Toast.LENGTH_LONG).show();

                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
