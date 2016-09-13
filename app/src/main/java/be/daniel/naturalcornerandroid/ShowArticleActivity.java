package be.daniel.naturalcornerandroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import be.daniel.naturalcornerandroid.model.Article;

public class ShowArticleActivity extends Activity {
    private TextView tvDenomination, tvCommentaire, tvPrice;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_article);
        tvDenomination = (TextView)findViewById(R.id.textViewDenomination);
        tvCommentaire = (TextView)findViewById(R.id.textViewCommentaire);
        tvPrice = (TextView)findViewById(R.id.textViewPrice);

        Bundle extra = getIntent().getExtras();
        if(extra!=null){
            Article article = (Article) extra.get("article");
            tvDenomination.setText(article.getDenomination());
            tvCommentaire.setText(article.getCommentaire());
            tvPrice.setText(String.valueOf(article.getPrixUnitaire()) + " EUR");
            tvCommentaire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvCommentaire.setHeight(tvCommentaire.getHeight() * 4);
                }
            });
        }
    }
}
