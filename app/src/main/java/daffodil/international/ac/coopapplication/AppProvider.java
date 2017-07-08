package daffodil.international.ac.coopapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.ContactInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UserInformation;

import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation.getCompanyInformationId;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation.getUniversityInformationId;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UserInformation.getUserInformationId;

/**
 * Created by Pranto on 08-Jun-17.
 */

public class AppProvider extends ContentProvider {
    private static final String TAG = "AppProvider";


    private AppDatabaseHelper mOpenHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static final String CONTENT_AUTHORITY = "daffodil.international.ac.coopapplication.provider";
    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final int USER_INFORMATION = 1000;
    private static final int USER_INFORMATION_ID = 1001;

    private static final int UNIVERSITY_INFORMATION = 100;
    private static final int UNIVERSITY_INFORMATION_ID = 101;

    private static final int CONTRACT_INFORMATION = 200;
    private static final int CONTRACT_INFORMATION_ID = 201;

    private static final int COMPANY_INFORMATION = 400;
    private static final int COMPANY_INFORMATION_ID = 401;


    long userInfoId;
    long universityInfoId;
    long contactInfoId;
    long companyInfoId;


    private static UriMatcher buildUriMatcher() {

        Log.d(TAG, "buildUriMatcher: starts");
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        //User Info
        matcher.addURI(CONTENT_AUTHORITY, UserInformation.TABLE_NAME, USER_INFORMATION);
        matcher.addURI(CONTENT_AUTHORITY, UserInformation.TABLE_NAME + "/#", USER_INFORMATION_ID);
        //company info
        matcher.addURI(CONTENT_AUTHORITY, CompanyInformation.TABLE_NAME, COMPANY_INFORMATION);
        matcher.addURI(CONTENT_AUTHORITY, CompanyInformation.TABLE_NAME + "/#", COMPANY_INFORMATION_ID);

        //  eg. content://daffodil.international.ac.coopapplication.provider/UniversityInformation
        matcher.addURI(CONTENT_AUTHORITY, UniversityInformation.TABLE_NAME, UNIVERSITY_INFORMATION);
        // e.g. content://daffodil.international.ac.coopapplication.provider/UniversityInformation/8
        matcher.addURI(CONTENT_AUTHORITY, UniversityInformation.TABLE_NAME + "/#", UNIVERSITY_INFORMATION_ID);

        //  eg. content://daffodil.international.ac.coopapplication.provider/ContactInformation
        matcher.addURI(CONTENT_AUTHORITY, ContactInformation.TABLE_NAME, CONTRACT_INFORMATION);
        // e.g. content://daffodil.international.ac.coopapplication.provider/ContactInformation/8
        matcher.addURI(CONTENT_AUTHORITY, ContactInformation.TABLE_NAME + "/#", CONTRACT_INFORMATION_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: starts");
        mOpenHelper = AppDatabaseHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query: called with URI " + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "query: match is " + match);

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (match) {
            case USER_INFORMATION:
                queryBuilder.setTables(UserInformation.TABLE_NAME);
                break;

            case USER_INFORMATION_ID:
                queryBuilder.setTables(UserInformation.TABLE_NAME);
                long userInformationId = getUserInformationId(uri);
                queryBuilder.appendWhere(UserInformation.Columns._ID + " = " + userInformationId);
                break;

            case CONTRACT_INFORMATION:
                queryBuilder.setTables(ContactInformation.TABLE_NAME);
                break;

            case CONTRACT_INFORMATION_ID:
                queryBuilder.setTables(ContactInformation.TABLE_NAME);
                long contractInformationId = ContactInformation.getContactInformationId(uri);
                queryBuilder.appendWhere(ContactInformation.Columns._ID + " = " + contractInformationId);
                break;

            case UNIVERSITY_INFORMATION:
                queryBuilder.setTables(UniversityInformation.TABLE_NAME);
                break;

            case UNIVERSITY_INFORMATION_ID:
                queryBuilder.setTables(UniversityInformation.TABLE_NAME);
                long universityInformationId = getUniversityInformationId(uri);
                queryBuilder.appendWhere(UniversityInformation.Columns._ID + " = " + universityInformationId);
                break;

            case COMPANY_INFORMATION:
                queryBuilder.setTables(CompanyInformation.TABLE_NAME);
                break;

            case COMPANY_INFORMATION_ID:
                queryBuilder.setTables(CompanyInformation.TABLE_NAME);
                long companyInformationId = getCompanyInformationId(uri);
                queryBuilder.appendWhere(CompanyInformation.Columns._ID + " = " + companyInformationId);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        //    return queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "getType: Starts");
        final int match = sUriMatcher.match(uri);
        switch (match) {

            case USER_INFORMATION:
                return UserInformation.CONTENT_TYPE;
            case USER_INFORMATION_ID:
                return UserInformation.CONTENT_ITEM_TYPE;

            case UNIVERSITY_INFORMATION:
                return UniversityInformation.CONTENT_TYPE;
            case UNIVERSITY_INFORMATION_ID:
                return UniversityInformation.CONTENT_ITEM_TYPE;

            case COMPANY_INFORMATION:
                return CompanyInformation.CONTENT_TYPE;
            case COMPANY_INFORMATION_ID:
                return CompanyInformation.CONTENT_ITEM_TYPE;

            case CONTRACT_INFORMATION:
                return ContactInformation.CONTENT_TYPE;
            case CONTRACT_INFORMATION_ID:
                return ContactInformation.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("unknown Uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;

        Uri returnUri;
        /*
        Primary key field value declared globally
        long = universityInfoId ;
        long = contactInfoId ;*/

        switch (match) {

            case USER_INFORMATION:
                Log.d(TAG, "Entering insert, called with uri:" + uri);
                db = mOpenHelper.getWritableDatabase();
                userInfoId = db.insert(UserInformation.TABLE_NAME, null, values);
                if (userInfoId >= 0) {
                    returnUri = ContactInformation.buildContactInformationUri(userInfoId);

                } else {
                    throw new android.database.SQLException("Failed to insert into :" + uri.toString());
                }
                break;

            case CONTRACT_INFORMATION:
                Log.d(TAG, "Entering insert, called with uri:" + uri);
                db = mOpenHelper.getWritableDatabase();
                contactInfoId = db.insert(ContactInformation.TABLE_NAME, null, values);
                if (contactInfoId >= 0) {
                    returnUri = ContactInformation.buildContactInformationUri(contactInfoId);

                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case UNIVERSITY_INFORMATION:
                Log.d(TAG, "Entering insert, called with uri:" + uri);

                db = mOpenHelper.getWritableDatabase();
                Log.d(TAG, "insert: userInfoId : " + userInfoId + ", contactInfoId : " + contactInfoId);

                values.put(UniversityInformation.Columns.CONTRACTS_ID, contactInfoId);
                values.put(UniversityInformation.Columns.USER_ID, userInfoId);

                universityInfoId = db.insert(UniversityInformation.TABLE_NAME, null, values);
                if (universityInfoId >= 0) {
                    returnUri = UniversityInformation.buildUniversityInformationUri(universityInfoId);

                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;
            case COMPANY_INFORMATION:
                Log.d(TAG, "Entering insert, called with uri:" + uri);

                db = mOpenHelper.getWritableDatabase();
                values.put(CompanyInformation.Columns.CONTRACTS_ID, contactInfoId);
                values.put(CompanyInformation.Columns.USER_ID, userInfoId);
                companyInfoId = db.insert(CompanyInformation.TABLE_NAME, null, values);
                if (companyInfoId >= 0) {
                    returnUri = CompanyInformation.buildCompanyInformationUri(companyInfoId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }

        if (userInfoId >= 0 || contactInfoId >= 0 || companyInfoId >= 0 || universityInfoId >= 0) {
            Log.d(TAG, "insert: Setting Notify With Uri _ " + uri);
            getContext().getContentResolver().notifyChange(uri, null);
        } else {
            Log.d(TAG, "insert: Nothing Inserted");
        }
        //  Log.d(TAG, "insert: universityInfoId : " + universityInfoId + ", contactInfoId : " + contactInfoId);
        Log.d(TAG, "Exiting insert, returning " + returnUri);

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "update called with uri " + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;
        int count;

        String selectionCriteria;

        switch (match) {
            case UNIVERSITY_INFORMATION:
                db = mOpenHelper.getWritableDatabase();
                count = db.delete(UniversityInformation.TABLE_NAME, selection, selectionArgs);
                break;

            case UNIVERSITY_INFORMATION_ID:
                db = mOpenHelper.getWritableDatabase();
                long universityInformationId = getUniversityInformationId(uri);
                selectionCriteria = UniversityInformation.Columns._ID + " = " + universityInformationId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(UniversityInformation.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case COMPANY_INFORMATION:
                db = mOpenHelper.getWritableDatabase();
                count = db.delete(CompanyInformation.TABLE_NAME, selection, selectionArgs);
                break;

            case COMPANY_INFORMATION_ID:
                db = mOpenHelper.getWritableDatabase();
                long companyInformationId = getCompanyInformationId(uri);
                selectionCriteria = CompanyInformation.Columns._ID + " = " + companyInformationId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(CompanyInformation.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case CONTRACT_INFORMATION:
                db = mOpenHelper.getWritableDatabase();
                count = db.delete(ContactInformation.TABLE_NAME, selection, selectionArgs);
                break;

            case CONTRACT_INFORMATION_ID:
                db = mOpenHelper.getWritableDatabase();
                long contractInformationId = ContactInformation.getContactInformationId(uri);
                selectionCriteria = ContactInformation.Columns._ID + " = " + contractInformationId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(ContactInformation.TABLE_NAME, selectionCriteria, selectionArgs);
                break;


            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        if (universityInfoId >= 0 || userInfoId >= 0 || contactInfoId >= 0 || companyInfoId >= 0) {
            Log.d(TAG, "Delete: Setting Notify With Uri _ " + uri);
            getContext().getContentResolver().notifyChange(uri, null);
        } else {
            Log.d(TAG, "Delete: Nothing Deleted");
        }

        Log.d(TAG, "Exiting update, returning " + count);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG, "update called with uri " + uri);
        final int match = sUriMatcher.match(uri);
        Log.d(TAG, "match is " + match);

        final SQLiteDatabase db;
        int count;

        String selectionCriteria;

        switch (match) {
            case UNIVERSITY_INFORMATION:
                db = mOpenHelper.getWritableDatabase();
                count = db.update(UniversityInformation.TABLE_NAME, values, selection, selectionArgs);
                break;

            case UNIVERSITY_INFORMATION_ID:
                db = mOpenHelper.getWritableDatabase();
                long universityInformationId = UniversityInformation.getUniversityInformationId(uri);
                selectionCriteria = UniversityInformation.Columns._ID + " = " + universityInformationId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(UniversityInformation.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;
            case COMPANY_INFORMATION:
                db = mOpenHelper.getWritableDatabase();
                count = db.update(CompanyInformation.TABLE_NAME, values, selection, selectionArgs);
                break;

            case COMPANY_INFORMATION_ID:
                db = mOpenHelper.getWritableDatabase();
                long companyInformationId = CompanyInformation.getCompanyInformationId(uri);
                selectionCriteria = CompanyInformation.Columns._ID + " = " + companyInformationId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(CompanyInformation.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case CONTRACT_INFORMATION:
                db = mOpenHelper.getWritableDatabase();
                count = db.update(ContactInformation.TABLE_NAME, values, selection, selectionArgs);
                break;

            case CONTRACT_INFORMATION_ID:
                db = mOpenHelper.getWritableDatabase();
                long contractInformationId = ContactInformation.getContactInformationId(uri);
                selectionCriteria = ContactInformation.Columns._ID + " = " + contractInformationId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(ContactInformation.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        if (universityInfoId >= 0 || userInfoId >= 0 || contactInfoId >= 0 || companyInfoId >= 0) {
            Log.d(TAG, "Update: Setting Notify With Uri _ " + uri);
            getContext().getContentResolver().notifyChange(uri, null);
        } else {
            Log.d(TAG, "Update: Nothing Updated");
        }

        Log.d(TAG, "Exiting update, returning " + count);
        return count;
    }

}
