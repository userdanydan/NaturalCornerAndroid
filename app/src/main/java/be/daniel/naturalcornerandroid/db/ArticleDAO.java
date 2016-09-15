package be.daniel.naturalcornerandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import be.daniel.naturalcornerandroid.model.Article;

/**
 * Created by ivymike on 09/09/16.
 */
public class ArticleDAO {
    //TABLES DE LA BDD
    public static final String TABLE_ARTICLE="ARTICLE";


    //COLONNES TABLE T_ARTICLES
    public static final String COLUMN_ID="ID";
    public static final String COLUMN_DENOMINATION="DENOMINATION";
    public static final String COLUMN_COMMENTAIRE="COMMENTAIRE";
    public static final String COLUMN_PRIX_UNITAIRE="PRIX_UNITAIRE";
    public static final String COLUMN_CATEGORIE="CATEGORIE";


    public static final String CREATE_REQUEST ="CREATE TABLE " + ArticleDAO.TABLE_ARTICLE
            +" (" + ArticleDAO.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +ArticleDAO.COLUMN_DENOMINATION + " TEXT, "
            +ArticleDAO.COLUMN_COMMENTAIRE + " TEXT, "
            +ArticleDAO.COLUMN_CATEGORIE + " TEXT,"
            +ArticleDAO.COLUMN_PRIX_UNITAIRE + " FLOAT"
            + ");";
    public static final String UPGRADE_REQUEST = "DROP TABLE " + ArticleDAO.TABLE_ARTICLE;

    private DBHelper dbHelpder;
    private Context context;
    private SQLiteDatabase db;

    public ArticleDAO(Context context) {
        this.context = context;
    }

    public ArticleDAO openWritable(){
        dbHelpder = new DBHelper(context);
        db = dbHelpder.getWritableDatabase();
        return this;
    }
    public ArticleDAO openReadable(){
        dbHelpder = new DBHelper(context);
        db = dbHelpder.getReadableDatabase();
        return this;
    }
    public void close(){
        db.close();
        dbHelpder.close();
    }

    public boolean create(Article article){
        ContentValues values = new ContentValues();
        values.put(COLUMN_DENOMINATION, article.getDenomination());
        values.put(COLUMN_COMMENTAIRE, article.getCommentaire());
        values.put(COLUMN_PRIX_UNITAIRE, String.valueOf(article.getPrixUnitaire()));
        values.put(COLUMN_CATEGORIE, article.getCategorie());
        long id = db.insert(TABLE_ARTICLE, null, values);
        if(id == -1){
            return false;
        } else{
            article.setId((int)id);
            return true;
        }
    }
    public static Article cursorToArticle(Cursor c){
        Article article = new Article();
        article.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
        article.setDenomination(c.getString(c.getColumnIndex(COLUMN_DENOMINATION)));
        article.setCommentaire(c.getString(c.getColumnIndex(COLUMN_COMMENTAIRE)));
        article.setPrixUnitaire(BigDecimal.valueOf(c.getDouble(c.getColumnIndex(COLUMN_PRIX_UNITAIRE))));
        article.setCategorie(c.getString(c.getColumnIndex(COLUMN_CATEGORIE)));
        return article;
    }
    public Cursor readCursorById(int id){
        Cursor c = db.query(TABLE_ARTICLE, null, COLUMN_ID + " = " + id, null, null, null, null);
        if(c.getCount()>0){
            c.moveToFirst();
            return c;
        }else{
            c.close();
            return null;
        }
    }

    public Article readById(int id){
        Cursor c = readCursorById(id);
        if(c != null){
            return  cursorToArticle(c);
        }else
        return null;
    }
    public List<Article> readAll(){
        ArrayList<Article> articles = new ArrayList<>();
        Cursor c = readAllCursor();
        if(c !=null){
            do{
                articles.add(cursorToArticle(c));
            }while(c.moveToNext());
            c.close();
        }
        return articles;
    }
    public Cursor readAllCursor(){
        Cursor c = db.query(TABLE_ARTICLE, null, null,  null, null, null, COLUMN_CATEGORIE);
        if(c.getCount()>0){
            c.moveToFirst();
            return c;
        }else {
            c.close();
            return null;
        }
    }
}
