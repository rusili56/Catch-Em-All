package nyc.c4q.rusili.catchemall.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nyc.c4q.rusili.catchemall.Network.Pokemon;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class AnimalDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pokemon.db";
    private static final int DATABASE_VERSION = 2;

    // make your database instance a singleton instance across the entire application's lifecycle.
    private static AnimalDatabaseHelper instance;

    // the static getInstance() method ensures that only one PostsDatabaseHelper will ever exist at any given time.
    // if the instance object has not been initialized, one will be created. If one has already been created then it
    // will simply be returned.

    public static synchronized AnimalDatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (instance == null) {
            instance = new AnimalDatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    private AnimalDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        // register our models
        cupboard().register(Pokemon.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // this will ensure that all tables are created
        cupboard().withDatabase(db).createTables();
        // add indexes and other database tweaks in this method if you want

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this will upgrade tables, adding columns and new tables.
        // Note that existing columns will not be converted
        cupboard().withDatabase(db).upgradeTables();
        // do migration work if you have an alteration to make to your schema here
        if (newVersion == 2) {
            ContentValues cv = new ContentValues();
            cv.put("image_url", "");
            cupboard().withDatabase(db).update(Pokemon.class, cv);
        }
    }
}
