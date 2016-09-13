package be.daniel.naturalcornerandroid.task;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import be.daniel.naturalcornerandroid.db.ArticleDAO;
import be.daniel.naturalcornerandroid.model.Article;

/**
 * Created by ivymike on 09/09/16.
 */
public class LoadArticlesTask extends AsyncTask<Void, Void, Cursor> {
    private LoadArticlesCallback callback;
    private Context context;


    public LoadArticlesTask(LoadArticlesCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected Cursor doInBackground(Void... params) {
        ArticleDAO dao = new ArticleDAO(context);
        return dao.openReadable().readAllCursor();
    }

    public interface  LoadArticlesCallback {
        void postUpdateUI(Cursor c);
    }
}
