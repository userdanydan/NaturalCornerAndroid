package be.daniel.naturalcornerandroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ivymike on 09/09/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="NATURAL_CORNER_DB";
    public static final int DB_VERSION=1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null , DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ArticleDAO.CREATE_REQUEST);

        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Oranges', 'Etenim si attendere diligenter, existimare vere de omni hac causa volueritis, sic constituetis, iudices, nec descensurum quemquam ad hanc accusationem fuisse, cui, utrum vellet, liceret, nec, cum descendisset, quicquam habiturum spei fuisse, nisi alicuius intolerabili libidine et nimis acerbo odio niteretur. Sed ego Atratino, humanissimo atque optimo adulescenti meo necessario, ignosco, qui habet excusationem vel pietatis vel necessitatis vel aetatis. Si voluit accusare, pietati tribuo, si iussus est, necessitati, si speravit aliquid, pueritiae. Ceteris non modo nihil ignoscendum, sed etiam acriter est resistendum.\n" +
                "\n', 1.0, 'Fruits')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Poires', 'Poires bio de Wallonie Etenim si attendere diligenter, existimare vere de omni hac causa volueritis, sic constituetis, iudices, nec descensurum quemquam ad hanc accusationem fuisse, cui, utrum vellet, liceret, nec, cum descendisset, quicquam habiturum spei fuisse, nisi alicuius intolerabili libidine et nimis acerbo odio niteretur. Sed ego Atratino, humanissimo atque optimo adulescenti meo necessario, ignosco, qui habet excusationem vel pietatis vel necessitatis vel aetatis. Si voluit accusare, pietati tribuo, si iussus est, necessitati, si speravit aliquid, pueritiae. Ceteris non modo nihil ignoscendum, sed etiam acriter est resistendum.\n" +
                "\n', 1.3, 'Fruits')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Pommes', 'Pommes bio de Wallonie Etenim si attendere diligenter, existimare vere de omni hac causa volueritis, sic constituetis, iudices, nec descensurum quemquam ad hanc accusationem fuisse, cui, utrum vellet, liceret, nec, cum descendisset, quicquam habiturum spei fuisse, nisi alicuius intolerabili libidine et nimis acerbo odio niteretur. Sed ego Atratino, humanissimo atque optimo adulescenti meo necessario, ignosco, qui habet excusationem vel pietatis vel necessitatis vel aetatis. Si voluit accusare, pietati tribuo, si iussus est, necessitati, si speravit aliquid, pueritiae. Ceteris non modo nihil ignoscendum, sed etiam acriter est resistendum.\n" +
                "\n', 2.0, 'Fruits')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Cerises', 'Cerises bio de Wallonie Etenim si attendere diligenter, existimare vere de omni hac causa volueritis, sic constituetis, iudices, nec descensurum quemquam ad hanc accusationem fuisse, cui, utrum vellet, liceret, nec, cum descendisset, quicquam habiturum spei fuisse, nisi alicuius intolerabili libidine et nimis acerbo odio niteretur. Sed ego Atratino, humanissimo atque optimo adulescenti meo necessario, ignosco, qui habet excusationem vel pietatis vel necessitatis vel aetatis. Si voluit accusare, pietati tribuo, si iussus est, necessitati, si speravit aliquid, pueritiae. Ceteris non modo nihil ignoscendum, sed etiam acriter est resistendum.\n" +
                "\n', 1.2, 'Fruits')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Tomates', 'Tomates bio de Wallonie Etenim si attendere diligenter, existimare vere de omni hac causa volueritis, sic constituetis, iudices, nec descensurum quemquam ad hanc accusationem fuisse, cui, utrum vellet, liceret, nec, cum descendisset, quicquam habiturum spei fuisse, nisi alicuius intolerabili libidine et nimis acerbo odio niteretur. Sed ego Atratino, humanissimo atque optimo adulescenti meo necessario, ignosco, qui habet excusationem vel pietatis vel necessitatis vel aetatis. Si voluit accusare, pietati tribuo, si iussus est, necessitati, si speravit aliquid, pueritiae. Ceteris non modo nihil ignoscendum, sed etiam acriter est resistendum.\n" +
                "\n', 1.0, 'Légumes')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Poireaux', 'Poireaux bio de Wallonie Etenim si attendere diligenter, existimare vere de omni hac causa volueritis, sic constituetis, iudices, nec descensurum quemquam ad hanc accusationem fuisse, cui, utrum vellet, liceret, nec, cum descendisset, quicquam habiturum spei fuisse, nisi alicuius intolerabili libidine et nimis acerbo odio niteretur. Sed ego Atratino, humanissimo atque optimo adulescenti meo necessario, ignosco, qui habet excusationem vel pietatis vel necessitatis vel aetatis. Si voluit accusare, pietati tribuo, si iussus est, necessitati, si speravit aliquid, pueritiae. Ceteris non modo nihil ignoscendum, sed etiam acriter est resistendum.\n" +
                "\n', 3.4, 'Légumes')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Persils', 'Persils bio de Wallonie Etenim si attendere diligenter, existimare vere de omni hac causa volueritis, sic constituetis, iudices, nec descensurum quemquam ad hanc accusationem fuisse, cui, utrum vellet, liceret, nec, cum descendisset, quicquam habiturum spei fuisse, nisi alicuius intolerabili libidine et nimis acerbo odio niteretur. Sed ego Atratino, humanissimo atque optimo adulescenti meo necessario, ignosco, qui habet excusationem vel pietatis vel necessitatis vel aetatis. Si voluit accusare, pietati tribuo, si iussus est, necessitati, si speravit aliquid, pueritiae. Ceteris non modo nihil ignoscendum, sed etiam acriter est resistendum.\n" +
                "\n', 1.0, 'Légumes')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Choux', 'Choux bio de Wallonie Etenim si attendere diligenter, existimare vere de omni hac causa volueritis, sic constituetis, iudices, nec descensurum quemquam ad hanc accusationem fuisse, cui, utrum vellet, liceret, nec, cum descendisset, quicquam habiturum spei fuisse, nisi alicuius intolerabili libidine et nimis acerbo odio niteretur. Sed ego Atratino, humanissimo atque optimo adulescenti meo necessario, ignosco, qui habet excusationem vel pietatis vel necessitatis vel aetatis. Si voluit accusare, pietati tribuo, si iussus est, necessitati, si speravit aliquid, pueritiae. Ceteris non modo nihil ignoscendum, sed etiam acriter est resistendum.\n" +
                "\n', 0.4, 'Légumes')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Feta', 'Feta bio de Wallonie His cognitis Gallus ut serpens adpetitus telo vel saxo iamque spes extremas opperiens et succurrens saluti suae quavis ratione colligi omnes iussit armatos et cum starent attoniti, districta dentium acie stridens adeste inquit viri fortes mihi periclitanti vobiscum.\n" +
                "\n', 3.5, 'Produits Frais')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Yaourt', 'Yaourt bio de Wallonie Paphius quin etiam et Cornelius senatores, ambo venenorum artibus pravis se polluisse confessi, eodem pronuntiante Maximino sunt interfecti. pari sorte etiam procurator monetae extinctus est. Sericum enim et Asbolium supra dictos, quoniam cum hortaretur passim nominare, quos vellent, adiecta religione firmarat, nullum igni vel ferro se puniri iussurum, plumbi validis ictibus interemit. et post hoe flammis Campensem aruspicem dedit, in negotio eius nullo sacramento constrictus.\n" +
                "\n', 0.4, 'Produits Frais')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Gouda', 'Gouda bio de Wallonie Fuerit toto in consulatu sine provincia, cui fuerit, antequam designatus est, decreta provincia. Sortietur an non? Nam et non sortiri absurdum est, et, quod sortitus sis, non habere. Proficiscetur paludatus? Quo? Quo pervenire ante certam diem non licebit. ianuario, Februario, provinciam non habebit; Kalendis ei denique Martiis nascetur repente provincia.\n" +
                "\n', 25.7, 'Produits Frais')");
        db.execSQL("INSERT INTO " + ArticleDAO.TABLE_ARTICLE + "("
                +ArticleDAO.COLUMN_DENOMINATION+", "
                +ArticleDAO.COLUMN_COMMENTAIRE+", "
                +ArticleDAO.COLUMN_PRIX_UNITAIRE+", "
                +ArticleDAO.COLUMN_CATEGORIE+") "
                +"VALUES ('Beurre', 'Beurre bio de Wallonie Rogatus ad ultimum admissusque in consistorium ambage nulla praegressa inconsiderate et leviter proficiscere inquit ut praeceptum est, Caesar sciens quod si cessaveris, et tuas et palatii tui auferri iubebo prope diem annonas. hocque solo contumaciter dicto subiratus abscessit nec in conspectum eius postea venit saepius arcessitus.\n" +
                "\n', 5.6, 'Produits Frais')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ArticleDAO.UPGRADE_REQUEST);
    }
}
