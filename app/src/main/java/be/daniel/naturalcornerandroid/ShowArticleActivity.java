package be.daniel.naturalcornerandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import be.daniel.naturalcornerandroid.model.Article;
import be.daniel.naturalcornerandroid.model.LignePanier;
import be.daniel.naturalcornerandroid.naturalcornerapplication.NaturalCornerApplication;

public class ShowArticleActivity extends AppCompatActivity {
    private TextView tvDenomination, tvCommentaire, tvPrice;
    private Button btnMinus, btnPlus;
    private ImageButton btnAdd;
    private EditText nbArticle;
    private  Article article;
    private boolean expandedTextClicked;
    private NaturalCornerApplication nCApp;
    private boolean listDisplayed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_article);
        setAppBar();
        tvDenomination = (TextView)findViewById(R.id.textViewDenomination);
        tvCommentaire = (TextView)findViewById(R.id.textViewCommentaire);
        tvPrice = (TextView)findViewById(R.id.textViewPrice);
        btnAdd = (ImageButton)findViewById(R.id.buttonAdd);
        btnMinus =(Button)findViewById(R.id.buttonMinusItem);
        btnPlus =(Button)findViewById(R.id.buttonAddItem);
        nbArticle = (EditText)findViewById(R.id.editTextSetNbArticle);
        final Bundle extra = getIntent().getExtras();
        if(extra!=null){
            article = (Article) extra.get("article");
            tvDenomination.setText(article.getDenomination());
            tvCommentaire.setText(article.getCommentaire().substring(0, 250)+ "\n\r"+" [...] ");
            tvCommentaire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!expandedTextClicked) {
                        tvCommentaire.setText(article.getCommentaire());
                        expandedTextClicked = true;
                    }else{
                        tvCommentaire.setText(article.getCommentaire().substring(0, 250)+ "\n\r"+" [...] ");
                        expandedTextClicked = false;
                    }
                }
            });
            tvPrice.setText(String.valueOf(article.getPrixUnitaire()) + " €");
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NaturalCornerApplication app = (NaturalCornerApplication) getApplication();
                    LignePanier lignePanier = new LignePanier();
                    lignePanier.setArticle(article);
                    lignePanier.setQuantite(Integer.parseInt(nbArticle.getText().toString()));
                    boolean addOK =app.getPanier().ajouterLigne(lignePanier);
                    if(addOK) {
                        app.getPanier().recalculer();
                        String str = getSupportActionBar().getSubtitle().toString();
                        Log.i("STR", str);
                        getSupportActionBar().setSubtitle(str.substring(0, str.indexOf(" : ")) + " : " + app.getPanier().getPrixTotal().toString() + " €");
                        Toast.makeText(ShowArticleActivity.this, "Added to your Basket", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ShowArticleActivity.this, "Not Added to your Basket", Toast.LENGTH_SHORT).show();
                    }
                    nbArticle.setText("1");

                }
            });
            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nbArticle.setText(String.valueOf(Integer.parseInt(nbArticle.getText().toString())+1));
                }
            });
            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(nbArticle.getText().toString())>0)
                        nbArticle.setText(String.valueOf(Integer.parseInt(nbArticle.getText().toString())-1));
                    else
                        nbArticle.setText("0");
                }
            });
        }
    }

    private void setAppBar() {
        if(nCApp==null)
            nCApp = (NaturalCornerApplication)getApplication();
        Toolbar myToolBar = (Toolbar) findViewById(R.id.toolbar_show_article);

        myToolBar.setSubtitle("TOTAL : " +
                ( (nCApp.getPanier().getPrixTotal()==null) ? .0 : nCApp.getPanier().getPrixTotal()) + " €");
        setSupportActionBar(myToolBar);
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
                Toast.makeText(ShowArticleActivity.this, "BOUH!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
