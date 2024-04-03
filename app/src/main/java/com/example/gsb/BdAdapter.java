package com.example.gsb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BdAdapter { static final int VERSION_BDD = 1; private static final String NOM_BDD = "gsb.db";
    private static final String TABLE_ECHANT = "echantillons"; static final String COL_ID = "_id";

    static final int NUM_COL_ID = 0;
    static final String COL_CODE = "CODE";
    static final int NUM_COL_CODE = 1;
    static final String COL_LIB = "LIB";
    static final int NUM_COL_LIB = 2;
    static final String COL_STOCK = "STOCK";
    static final int NUM_COL_STOCK = 3;
    private CreateBdEchantillon bdArticles;
    private Context context;
    private SQLiteDatabase db;
    public BdAdapter(Context context){ this.context = context; bdArticles = new CreateBdEchantillon(context, NOM_BDD, null, VERSION_BDD);

    }
    //si la base n’existe pas, l’objet SQLiteOpenHelper exécute la méthode onCreate // si la version de la base a changé, la méthode onUpgrade sera lancée, dans les 2 cas l’appel

// à getWritableDatabase permet d’ouvrir une connexion à la base en écriture
public BdAdapter open(){ db = bdArticles.getWritableDatabase();
        return this;
    }
    public BdAdapter close (){ db.close(); return null;
    }

    public long insererEchantillon(Echantillon unEchant){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_CODE, unEchant.getCode());
        values.put(COL_LIB, unEchant.getLibelle());
        values.put(COL_STOCK, unEchant.getQuantiteStock()); //on insère l'objet dans la BDD via le ContentValues
         return db.insert(TABLE_ECHANT, null, values);
    }
    private Echantillon cursorToEchant(Cursor c) {

//Cette méthode permet de convertir un cursor en un echantillon //si aucun élément n'a été retourné dans la requête, on renvoie null Echantillon unEchant=null ;

        Echantillon unEchant = null;
        if (c.getCount() != 0) {
            c.moveToFirst();
            //on se place sur le premier élément
            unEchant = new Echantillon();
            //On créé un echantillon
            // on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
            unEchant.setCode(c.getString(NUM_COL_CODE));
            unEchant.setLibelle(c.getString(NUM_COL_LIB));
            unEchant.setQuantiteStock(c.getString(NUM_COL_STOCK));
        }
        c.close(); //On ferme le cursor
        return unEchant;
    }

    public List<Echantillon> list() {
        List<Echantillon> echantillonsList = new ArrayList<>();

        // Ouvrir la base de données en lecture
        SQLiteDatabase db = bdArticles.getReadableDatabase();

        // Effectuer une requête pour récupérer tous les échantillons
        Cursor cursor = db.query(TABLE_ECHANT, null, null, null, null, null, null);

        // Parcourir le curseur pour récupérer les échantillons
        if (cursor.moveToFirst()) {
            do {
                Echantillon echantillon = new Echantillon();
                echantillon.setCode(cursor.getString(NUM_COL_CODE));
                echantillon.setLibelle(cursor.getString(NUM_COL_LIB));
                echantillon.setQuantiteStock(cursor.getString(NUM_COL_STOCK));
                echantillonsList.add(echantillon);
            } while (cursor.moveToNext());
        }

        // Fermer le curseur et la base de données
        cursor.close();
        db.close();

        // Retourner la liste des échantillons
        return echantillonsList;
    }
    //On retourne l'echantillon

     public Echantillon getEchantillonWithLib(String unCode)
     { //Récupère dans un Cursor les valeurs correspondant à un echantillon grâce à sa designation)
          Cursor c = db.query(TABLE_ECHANT, new String[] {COL_ID,COL_CODE, COL_LIB,

        COL_STOCK}, COL_CODE + " LIKE \"" + unCode +"\"", null, null, null, null); return cursorToEchant(c); }

    public int updateEchantillon(String unCode, Echantillon unEchant)
            { //La mise à jour d'un echantillon dans la BDD fonctionne plus ou moins comme une insertion,

    // il faut simple préciser quel echantillon on doit mettre à jour grâce à son code
    ContentValues values = new ContentValues();
    values.put(COL_CODE, unEchant.getCode()); values.put(COL_LIB, unEchant.getLibelle());
    values.put(COL_STOCK, unEchant.getQuantiteStock());
    return db.update(TABLE_ECHANT, values, COL_CODE + " = \"" +unCode+"\"", null);
            }
            public int removeEchantillonWithCode(String unCode) {
        //Suppression d'un echantillon de la BDD grâce à son code
                return db.delete(TABLE_ECHANT, COL_CODE + " = \"" +unCode+"\"", null);
    }
    public Cursor getData() {
        return db.rawQuery("SELECT * FROM echantillons", null);
    }
}



