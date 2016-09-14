package be.daniel.naturalcornerandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import be.daniel.naturalcornerandroid.model.LignePanier;
import be.daniel.naturalcornerandroid.model.Panier;
import be.daniel.naturalcornerandroid.naturalcornerapplication.NaturalCornerApplication;

public class BasketActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private TextView tvTotal;
    private int counter;
    private Panier panier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        NaturalCornerApplication ncApp = (NaturalCornerApplication)getApplication();
        if(ncApp !=null){
            panier = ncApp.getPanier();
            tvTotal = (TextView)findViewById(R.id.textViewTotalPrice);
            tvTotal.setText(String.valueOf(panier.getPrixTotal()));
            setUpTable();
        }
    }

    private void setUpTable() {
        tableLayout = (TableLayout) findViewById(R.id.table_layout_id);
        for(LignePanier lignePanier : panier.getLignes()) {

            final TableRow tableRow = new TableRow(getApplicationContext());
            final TableRow tableRow2 = new TableRow(getApplicationContext());


            final TextView tvArticleName = new TextView(getApplicationContext());
            tvArticleName.setText((counter+1) + " : " +lignePanier.getArticle().getDenomination());
            tvArticleName.setTextSize(25);
            tvArticleName.setPadding(16, 0, 16, 0);


            TextView tvArticlePrice = new TextView(getApplicationContext());
            tvArticlePrice.setText(String.valueOf(lignePanier.getArticle().getPrixUnitaire()) + " X " + lignePanier.getQuantite());
            tvArticlePrice.setTextSize(25);
            tvArticlePrice.setPadding(16, 0, 16, 0);

            TextView tvArticleTotalPrice = new TextView(getApplicationContext());
            tvArticleTotalPrice.setText(String.valueOf(lignePanier.getMontant()));
            tvArticleTotalPrice.setTextSize(25);
            tvArticleTotalPrice.setPadding(16, 0, 16, 0);



            final EditText etChangeNbArticle = new EditText(getApplicationContext());
            etChangeNbArticle.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_VARIATION_NORMAL);
            etChangeNbArticle.setWidth(150);


            Button btnChangeArticle = new Button(getApplicationContext());
            btnChangeArticle.setText("Change");
            btnChangeArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nbArticle = etChangeNbArticle.getText().toString();
                    int nbArticleInt = Integer.parseInt(nbArticle);
                    String text =tvArticleName.getText().toString();
                    panier.getLignes().get(Integer.valueOf(text.substring(0, text.indexOf(" ")))-1).setQuantite(nbArticleInt);
                    panier.recalculer();
                    tableRow.removeAllViews();
                    counter=0;
                    setUpTable();
                }
            });

            btnChangeArticle.setWidth(150);
            btnChangeArticle.setHeight(75);


            tableRow.addView(tvArticleName);
            tableRow.addView(tvArticlePrice);
            tableRow.addView(tvArticleTotalPrice);
            tableRow2.addView(etChangeNbArticle);
            tableRow2.addView(btnChangeArticle);

            tableRow.setMinimumHeight(100);

            tableLayout.addView(tableRow);
            tableLayout.addView(tableRow2);

            counter++;
        }
    }
}
