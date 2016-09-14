package be.daniel.naturalcornerandroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import be.daniel.naturalcornerandroid.model.Article;
import be.daniel.naturalcornerandroid.model.LignePanier;
import be.daniel.naturalcornerandroid.naturalcornerapplication.NaturalCornerApplication;

public class ShowArticleActivity extends Activity {
    private TextView tvDenomination, tvCommentaire, tvPrice;
    private Button btnAdd;
    private EditText nbArticle;
    private  Article article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_article);
        tvDenomination = (TextView)findViewById(R.id.textViewDenomination);
        tvCommentaire = (TextView)findViewById(R.id.textViewCommentaire);
        tvPrice = (TextView)findViewById(R.id.textViewPrice);
        btnAdd = (Button)findViewById(R.id.buttonAdd);
        nbArticle = (EditText)findViewById(R.id.editTextSetNbArticle);
        Bundle extra = getIntent().getExtras();
        if(extra!=null){
            article = (Article) extra.get("article");
            tvDenomination.setText(article.getDenomination());
            tvCommentaire.setText(article.getCommentaire());
            tvPrice.setText(String.valueOf(article.getPrixUnitaire()) + " EUR");
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NaturalCornerApplication app = (NaturalCornerApplication) getApplication();
                    LignePanier lignePanier = new LignePanier();
                    lignePanier.setArticle(article);
                    lignePanier.setQuantite(Integer.parseInt(nbArticle.getText().toString()));
                    boolean addOK =app.getPanier().ajouterLigne(lignePanier);
                    if(addOK)
                        Toast.makeText(ShowArticleActivity.this, "Added to your Basket", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(ShowArticleActivity.this, "Not Added to your Basket", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
