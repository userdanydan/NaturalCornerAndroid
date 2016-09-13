package be.daniel.naturalcornerandroid.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import be.daniel.naturalcornerandroid.R;
import be.daniel.naturalcornerandroid.db.ArticleDAO;
import be.daniel.naturalcornerandroid.model.Article;

/**
 * Created by ivymike on 09/09/16.
 */
public class ArticleCursorAdapter extends CursorAdapter {

    private LayoutInflater inflater;

    public ArticleCursorAdapter(Context context, Cursor c) {
        super(context, c);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.article_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tviId = (TextView) view.findViewById(R.id.album_list_item_tv_id);
        TextView tvDenomination = (TextView) view.findViewById(R.id.album_list_item_tv_denomination);
        TextView tvCommentaire = (TextView) view.findViewById(R.id.album_list_item_tv_commentaire);
        TextView tvPrixUnitaire = (TextView) view.findViewById(R.id.album_list_item_tv_prix_unitaire);

        Article article = ArticleDAO.cursorToArticle(cursor);

        tviId.setText(String.valueOf(article.getId()));
        tvDenomination.setText(article.getDenomination());
        tvCommentaire.setText(article.getCommentaire());
        tvPrixUnitaire.setText(String.valueOf(article.getPrixUnitaire()));
    }
}
