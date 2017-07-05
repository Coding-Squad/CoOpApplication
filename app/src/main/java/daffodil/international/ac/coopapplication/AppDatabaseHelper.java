package daffodil.international.ac.coopapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UniversityInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.ContactInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.StudentInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UserInformation;

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


    public AppDatabaseHelper(Context context) {
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
            + UniversityInformation.Columns.UNIVERSITY_IS_APPROVED + " INTEGER, "
            + UniversityInformation.Columns.CONTRACTS_ID + " INTEGER, "
            + UniversityInformation.Columns.USER_ID + " INTEGER);";

    //ContractInformation Table
    public static final String CREATE_CONTRACT_INFORMATION_TABLE = "CREATE TABLE " + ContactInformation.TABLE_NAME + " ("
            + ContactInformation.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
            + ContactInformation.Columns.CONTACT_PERSON_NAME + " TEXT NOT NULL, "
            + ContactInformation.Columns.CONTACT_PERSON_EMAIL + " TEXT, "
            + ContactInformation.Columns.CONTACT_PERSON_PHONE + " INTEGER);";

    //StudentInformation Table
    public static final String CREATE_STUDENT_INFORMATION_TABLE = "CREATE TABLE " + StudentInformation.TABLE_NAME + " ("
            + StudentInformation.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
            + StudentInformation.Columns.FIRST_NAME + " TEXT NOT NULL, "
            + StudentInformation.Columns.LAST_NAME + " TEXT, "
            + StudentInformation.Columns.MOBILE_NUMBER + " TEXT, "
            + StudentInformation.Columns.ADDRESS + " TEXT, "
            + StudentInformation.Columns.BLOOD_GROUP + " INTEGER, "
            + StudentInformation.Columns.DATE_OF_BIRTH + " DATE, "
            + StudentInformation.Columns.DESCRIPTION + " TEXT, "
            + StudentInformation.Columns.GENDER + " INTEGER, "
            + StudentInformation.Columns.STUDENT_ID + " LONG, "
            + StudentInformation.Columns.USER_ID + " LONG, "
            + StudentInformation.Columns.UNIVERSITY_ID + " INTEGER);";

    //ContractInformation Table
    public static final String CREATE_USER_INFORMATION_TABLE = "CREATE TABLE " + UserInformation.TABLE_NAME + " ("
            + UserInformation.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
            + UserInformation.Columns.USER_EMAIL + " TEXT NOT NULL, "
            + UserInformation.Columns.USER_PASSWORD + " TEXT, "
            + UserInformation.Columns.USER_ACOUNT_STATUS + " INTEGER, "
            + UserInformation.Columns.USER_SECRET_QUESTION + " TEXT, "
            + UserInformation.Columns.USER_ROLE_ID + " INTEGER);";

    //ContractInformation Table
    public static final String CREATE_COMPANY_INFORMATION_TABLE = "CREATE TABLE " + CompanyInformation.TABLE_NAME + " ("
            + CompanyInformation.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
            + CompanyInformation.Columns.COMPANY_NAME + " TEXT NOT NULL, "
            + CompanyInformation.Columns.COMPANY_WEB_URL + " TEXT, "
            + CompanyInformation.Columns.COMPANY_ADDRESS + " TEXT, "
            + CompanyInformation.Columns.COMPANY_BUSI_TYPE_ID + " INTEGER, "
            + CompanyInformation.Columns.USER_ID + " INTEGER, "
            + CompanyInformation.Columns.CONTRACTS_ID + " INTEGER);";

    //Business Type Table
    public static final String CREATE_BUSINESS_TYPE_TABLE = "CREATE TABLE " + BusinessType.TABLE_NAME + " ("
            + BusinessType.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, "
            + BusinessType.Columns.BUSINESS_TYPE_NAME + " INTEGER);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: starts");

        Log.d(TAG, " 1 : " + CREATE_UNIVERSITY_INFORMATION_TABLE);
        db.execSQL(CREATE_UNIVERSITY_INFORMATION_TABLE);

        Log.d(TAG, " 2 : " + CREATE_CONTRACT_INFORMATION_TABLE);
        db.execSQL(CREATE_CONTRACT_INFORMATION_TABLE);


        Log.d(TAG, " 3 : " + CREATE_STUDENT_INFORMATION_TABLE);
        db.execSQL(CREATE_STUDENT_INFORMATION_TABLE);

        db.execSQL(CREATE_USER_INFORMATION_TABLE);
        db.execSQL(CREATE_COMPANY_INFORMATION_TABLE);
        db.execSQL(CREATE_BUSINESS_TYPE_TABLE);

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

    /**
     * Getting all Business Type
     * returns list of Business Type
     */
    public List<BusinessTypeDto> getAllBusinesstype() {

        List<BusinessTypeDto> businessTypeDtos = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + BusinessType.TABLE_NAME;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BusinessTypeDto dto = new BusinessTypeDto(cursor.getLong(0), cursor.getString(1));
                //   Log.d(TAG, "getAllLabels: "+cursor.getLong(0)+" , "+cursor.getString(1));
                businessTypeDtos.add(dto);
            } while (cursor.moveToNext());
        }


        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return businessTypeDtos;
    }

    /**
     * Getting all Business Type
     * returns list of Business Type
     */
    public List<UniversityInfoDto> getAllApprovedUniversity() {

        List<UniversityInfoDto> approvedUniversityDtos = new ArrayList<UniversityInfoDto>();

        // Select All Query
        // UniversityApprovedId = 0 (Not Approved), UniversityApprovedId = 1 (Approved).





        String selectQuery = "SELECT  * FROM " + UniversityInformation.TABLE_NAME ;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.d(TAG, "get All Approved University : ");
                UniversityInfoDto dto = new UniversityInfoDto(cursor.getLong(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
                approvedUniversityDtos.add(dto);
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return approvedUniversityDtos;
    }


    public boolean checkUser (String email){
        // array of columns to fetch
        String[] columns = {
                UserInformation.Columns._ID
        };

        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = UserInformation.Columns.USER_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(UserInformation.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    /*
     This method to check user exist or not

     @param email
     @param password
     @return true/false
     */

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                UserInformation.Columns._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = UserInformation.Columns.USER_EMAIL + " = ?" + " AND " + UserInformation.Columns.USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(UserInformation.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


}
