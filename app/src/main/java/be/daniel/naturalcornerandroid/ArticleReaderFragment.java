package be.daniel.naturalcornerandroid;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import be.daniel.naturalcornerandroid.model.Article;
import be.daniel.naturalcornerandroid.model.LignePanier;
import be.daniel.naturalcornerandroid.naturalcornerapplication.NaturalCornerApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleReaderFragment extends Fragment {

    private TextView tvDenomination, tvCommentaire, tvPrice;
    private Button btnMinus, btnPlus;
    private ImageButton btnAdd;
    private EditText nbArticle;
    private Article article;
    private boolean expandedTextClicked;
    private NaturalCornerApplication nCApp;
    private boolean listDisplayed;

    public ArticleReaderFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_reader_layout, container, false);


        tvDenomination = (TextView) v.findViewById(R.id.textViewDenomination);
        tvCommentaire = (TextView) v.findViewById(R.id.textViewCommentaire);
        tvPrice = (TextView) v.findViewById(R.id.textViewPrice);
        btnAdd = (ImageButton) v.findViewById(R.id.buttonAdd);
        btnMinus =(Button) v.findViewById(R.id.buttonMinusItem);
        btnPlus =(Button) v.findViewById(R.id.buttonAddItem);
        nbArticle = (EditText) v.findViewById(R.id.editTextSetNbArticle);

        Bundle bundle = getArguments();
        article = (Article)bundle.getSerializable("article");



        Log.i("ARTICLE", article.toString());
        if(article!=null){
            tvDenomination.setText(article.getDenomination());
            tvCommentaire.setText(article.getCommentaire().substring(0,
                    article.getCommentaire().length()>100?article.getCommentaire().length()/5:article.getCommentaire().length())
                    + "\n\r"+" [...] ");
            tvCommentaire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!expandedTextClicked) {
                        tvCommentaire.setText(article.getCommentaire());
                        expandedTextClicked = true;
                    }else{
                        tvCommentaire.setText(article.getCommentaire().substring(0, article.getCommentaire().length()>100?article.getCommentaire().length()/5:article.getCommentaire().length())+
                                "\n\r"+" [...] ");
                        expandedTextClicked = false;
                    }
                }
            });
            tvPrice.setText(String.valueOf(article.getPrixUnitaire()) + " €");
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NaturalCornerApplication app = (NaturalCornerApplication) getActivity().getApplication();
                    LignePanier lignePanier = new LignePanier();
                    lignePanier.setArticle(article);
                    lignePanier.setQuantite(Integer.parseInt(nbArticle.getText().toString()));
                    boolean addOK =app.getPanier().ajouterLigne(lignePanier);
                    if(addOK) {
                        app.getPanier().recalculer();
                        String str = ((AppCompatActivity)getActivity()).getSupportActionBar().getSubtitle().toString();
                        Log.i("STR", str);
                        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(str.substring(0, str.indexOf(" : ")) + " : " + app.getPanier().getPrixTotal().toString() + " €");
                        Toast.makeText(getActivity().getApplicationContext(), R.string.added_to_basket, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity().getApplicationContext(), R.string.not_added_to_basket, Toast.LENGTH_SHORT).show();
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
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

}
