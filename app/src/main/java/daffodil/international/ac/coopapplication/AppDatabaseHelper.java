package daffodil.international.ac.coopapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Pranto on 08-Jun-17.
 * <p>
 * Basic database class for the application.
 * <p>
 * The only class that should use this is
 */

class AppDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "AppDatabaseHelper";

    public static final String DATABASE_NAME = "co_op.db";
    public static final int DATABASE_VERSION = 1;

    // Implement AppDatabase as a Singleton
    private static AppDatabaseHelper instance = null;


    private AppDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "AppDatabase: constructor called");
    }

    /**
     * Get an instance of the app's singleton database helper object
     *
     * @param context the content providers context.
     * @return a SQLite database helper object
     */
    static AppDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            Log.d(TAG, "getInstance: creating new instance");
            instance = new AppDatabaseHelper(context);
        }
        return instance;
    }

    //UniversityInformation Table
    public static final String CREATE_UNIVERSITY_INFORMATION_TABLE = "CREATE TABLE " + UniversityInformation.TABLE_NAME + " ("
            + UniversityInformation.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
            + UniversityInformation.Columns.UNIVERSITY_NAME + " TEXT NOT NULL, "
            + UniversityInformation.Columns.UNIVERSITY_ADDRESS + " TEXT, "
            + UniversityInformation.Columns.UNIVERSITY_URL + " TEXT, "
            + UniversityInformation.Columns.CONTRACTS_ID + " INTEGER);";

    //ContractInformation Table
    public static final String CREATE_CONTRACT_INFORMATION_TABLE = "CREATE TABLE " + ContactInformation.TABLE_NAME + " ("
            + ContactInformation.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
            + ContactInformation.Columns.CONTRACT_PERSON_NAME + " TEXT NOT NULL, "
            + ContactInformation.Columns.CONTRACT_PERSON_EMAIL + " TEXT, "
            + ContactInformation.Columns.CONTRACT_PERSON_PHONE + " INTEGER);";


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: starts");

        Log.d(TAG, " 1 : " + CREATE_UNIVERSITY_INFORMATION_TABLE);
        db.execSQL(CREATE_UNIVERSITY_INFORMATION_TABLE);

        Log.d(TAG, " 2 : " + CREATE_CONTRACT_INFORMATION_TABLE);
        db.execSQL(CREATE_CONTRACT_INFORMATION_TABLE);

        Log.d(TAG, "onCreate: ends");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(TAG, "onUpgrade: starts");
        switch (oldVersion) {
            case 1:
                // upgrade logic from version 1
                break;
            default:
                throw new IllegalStateException("onUpgrade() with unknown newVersion: " + newVersion);
        }
        Log.d(TAG, "onUpgrade: ends");

    }
}
