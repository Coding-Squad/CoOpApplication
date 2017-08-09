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

import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.ContactInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.FeedBack;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.StudentInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UploadFiles;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UserInformation;

import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType.buildBusinessTypeUri;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType.getBusinessTypeId;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation.getCompanyInformationId;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.ContactInformation.getContactInformationId;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.FeedBack.buildFeedBackUri;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.FeedBack.getFeedBackId;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation.getUniversityInformationId;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UploadFiles.buildUploadFilesUri;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UploadFiles.getUploadFilesId;
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

    private static final int STUDENT_INFORMATION = 300;
    private static final int STUDENT_INFORMATION_ID = 301;
    private static final int STUDENT_DTAILS_WITH_PHOTO = 302;

    private static final int COMPANY_INFORMATION = 400;
    private static final int COMPANY_INFORMATION_ID = 401;

    private static final int BUSINESS_TYPE = 500;
    private static final int BUSINESS_TYPE_ID = 501;

    private static final int UPLOAD_FILE = 600;
    private static final int UPLOAD_FILE_ID = 601;

    private static final int FEED_BACK = 700;
    private static final int FEED_BACK_ID = 701;


    long userInfoId;
    long universityInfoId;
    long contactInfoId;

    long studentInformtaionId;
    long companyInfoId;
    long businessTypeId;
    long uploadFilesId;
    long mFeedBackId;


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

        //  eg. content://daffodil.international.ac.coopapplication.provider/StudentInformation
        matcher.addURI(CONTENT_AUTHORITY, StudentInformation.TABLE_NAME, STUDENT_INFORMATION);
        // e.g. content://daffodil.international.ac.coopapplication.provider/StudentInformation/8
        matcher.addURI(CONTENT_AUTHORITY, StudentInformation.TABLE_NAME + "/#", STUDENT_INFORMATION_ID);

        matcher.addURI(CONTENT_AUTHORITY, StudentInformation.STUDENT_INFO_PHOTO, STUDENT_DTAILS_WITH_PHOTO);

        //  eg. content://daffodil.international.ac.coopapplication.provider/ContactInformation
        matcher.addURI(CONTENT_AUTHORITY, ContactInformation.TABLE_NAME, CONTRACT_INFORMATION);
        // e.g. content://daffodil.international.ac.coopapplication.provider/ContactInformation/8
        matcher.addURI(CONTENT_AUTHORITY, ContactInformation.TABLE_NAME + "/#", CONTRACT_INFORMATION_ID);

        //company info
        matcher.addURI(CONTENT_AUTHORITY, BusinessType.TABLE_NAME, BUSINESS_TYPE);
        matcher.addURI(CONTENT_AUTHORITY, BusinessType.TABLE_NAME + "/#", BUSINESS_TYPE_ID);

        //UPLOAD FILE info
        matcher.addURI(CONTENT_AUTHORITY, UploadFiles.TABLE_NAME, UPLOAD_FILE);
        matcher.addURI(CONTENT_AUTHORITY, UploadFiles.TABLE_NAME + "/#", UPLOAD_FILE_ID);

        //FEED BACK info
        matcher.addURI(CONTENT_AUTHORITY, FeedBack.TABLE_NAME, FEED_BACK);
        matcher.addURI(CONTENT_AUTHORITY, FeedBack.TABLE_NAME + "/#", FEED_BACK_ID);

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
                long contractInformationId = getContactInformationId(uri);
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

            case STUDENT_INFORMATION:
                queryBuilder.setTables(StudentInformation.TABLE_NAME);
                break;
            case STUDENT_INFORMATION_ID:
                queryBuilder.setTables(StudentInformation.TABLE_NAME);
                long studentInformationId = StudentInformation.getStudentInformationId(uri);
                queryBuilder.appendWhere(StudentInformation.Columns._ID + " = " + studentInformationId);
                break;
            case STUDENT_DTAILS_WITH_PHOTO:
                queryBuilder.setTables(StudentInformation.STUDENT_INFO_PHOTO);
                break;

            case BUSINESS_TYPE:
                queryBuilder.setTables(BusinessType.TABLE_NAME);
                break;
            case BUSINESS_TYPE_ID:
                queryBuilder.setTables(BusinessType.TABLE_NAME);
                long businessTypeId = getBusinessTypeId(uri);
                queryBuilder.appendWhere(BusinessType.Columns._ID + " = " + businessTypeId);
                break;

            case UPLOAD_FILE:
                queryBuilder.setTables(UploadFiles.TABLE_NAME);
                break;
            case UPLOAD_FILE_ID:
                queryBuilder.setTables(UploadFiles.TABLE_NAME);
                long uploadFileId = getUploadFilesId(uri);
                queryBuilder.appendWhere(UploadFiles.Columns._ID + " = " + uploadFileId);
                break;

            case FEED_BACK:
                queryBuilder.setTables(FeedBack.TABLE_NAME);
                break;
            case FEED_BACK_ID:
                queryBuilder.setTables(FeedBack.TABLE_NAME);
                long feedBackId = getFeedBackId(uri);
                queryBuilder.appendWhere(FeedBack.Columns._ID + " = " + feedBackId);
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

            case STUDENT_INFORMATION:
                return StudentInformation.CONTENT_TYPE;
            case STUDENT_INFORMATION_ID:
                return StudentInformation.CONTENT_ITEM_TYPE;

            case BUSINESS_TYPE:
                return BusinessType.CONTENT_TYPE;
            case BUSINESS_TYPE_ID:
                return BusinessType.CONTENT_ITEM_TYPE;

            case UPLOAD_FILE:
                return UploadFiles.CONTENT_TYPE;
            case UPLOAD_FILE_ID:
                return UploadFiles.CONTENT_ITEM_TYPE;

            case FEED_BACK:
                return FeedBack.CONTENT_TYPE;
            case FEED_BACK_ID:
                return FeedBack.CONTENT_ITEM_TYPE;

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

            case STUDENT_INFORMATION:
                Log.d(TAG, "Entering insert, called with uri:" + uri);

                db = mOpenHelper.getWritableDatabase();
                values.put(StudentInformation.Columns.USER_ID, userInfoId);
                studentInformtaionId = db.insert(StudentInformation.TABLE_NAME, null, values);
                if (studentInformtaionId >= 0) {
                    returnUri = StudentInformation.buildStudentInformationUri(studentInformtaionId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
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
            case BUSINESS_TYPE:
                Log.d(TAG, "Entering insert, called with uri:" + uri);
                db = mOpenHelper.getWritableDatabase();
                businessTypeId = db.insert(BusinessType.TABLE_NAME, null, values);
                if (businessTypeId >= 0) {
                    returnUri = buildBusinessTypeUri(businessTypeId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case UPLOAD_FILE:
                Log.d(TAG, "Entering insert, called with uri:" + uri);
                db = mOpenHelper.getWritableDatabase();
                uploadFilesId = db.insert(UploadFiles.TABLE_NAME, null, values);
                if (uploadFilesId >= 0) {
                    returnUri = buildUploadFilesUri(uploadFilesId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            case FEED_BACK:
                Log.d(TAG, "Entering insert, called with uri:" + uri);
                db = mOpenHelper.getWritableDatabase();
                mFeedBackId = db.insert(FeedBack.TABLE_NAME, null, values);
                if (mFeedBackId >= 0) {
                    returnUri = buildFeedBackUri(mFeedBackId);
                } else {
                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }

        if (userInfoId >= 0 || contactInfoId >= 0 || companyInfoId >= 0 || universityInfoId >= 0 || businessTypeId >= 0 || uploadFilesId >= 0 || mFeedBackId >= 0) {
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
                long contractInformationId = getContactInformationId(uri);
                selectionCriteria = ContactInformation.Columns._ID + " = " + contractInformationId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(ContactInformation.TABLE_NAME, selectionCriteria, selectionArgs);
                break;
            case BUSINESS_TYPE:
                db = mOpenHelper.getWritableDatabase();
                count = db.delete(BusinessType.TABLE_NAME, selection, selectionArgs);
                break;

            case BUSINESS_TYPE_ID:
                db = mOpenHelper.getWritableDatabase();
                long businessTypeInfoId = getBusinessTypeId(uri);
                selectionCriteria = BusinessType.Columns._ID + " = " + businessTypeInfoId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(BusinessType.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case UPLOAD_FILE:
                db = mOpenHelper.getWritableDatabase();
                count = db.delete(UploadFiles.TABLE_NAME, selection, selectionArgs);
                break;

            case UPLOAD_FILE_ID:
                db = mOpenHelper.getWritableDatabase();
                long uploadFileInfoId = getUploadFilesId(uri);
                selectionCriteria = UploadFiles.Columns._ID + " = " + uploadFileInfoId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(UploadFiles.TABLE_NAME, selectionCriteria, selectionArgs);
                break;

            case FEED_BACK:
                db = mOpenHelper.getWritableDatabase();
                count = db.delete(FeedBack.TABLE_NAME, selection, selectionArgs);
                break;

            case FEED_BACK_ID:
                db = mOpenHelper.getWritableDatabase();
                long feedBackId = getFeedBackId(uri);
                selectionCriteria = FeedBack.Columns._ID + " = " + feedBackId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.delete(UploadFiles.TABLE_NAME, selectionCriteria, selectionArgs);
                break;



            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        if (universityInfoId >= 0 || userInfoId >= 0 || contactInfoId >= 0 || companyInfoId >= 0 || businessTypeId >= 0 || uploadFilesId >= 0 || mFeedBackId >= 0) {
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
                long contractInformationId = getContactInformationId(uri);
                selectionCriteria = ContactInformation.Columns._ID + " = " + contractInformationId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(ContactInformation.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case BUSINESS_TYPE:
                db = mOpenHelper.getWritableDatabase();
                count = db.update(BusinessType.TABLE_NAME, values, selection, selectionArgs);
                break;

            case BUSINESS_TYPE_ID:
                db = mOpenHelper.getWritableDatabase();
                long businessTypeInfoId = getBusinessTypeId(uri);
                selectionCriteria = BusinessType.Columns._ID + " = " + businessTypeInfoId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(BusinessType.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case UPLOAD_FILE:
                db = mOpenHelper.getWritableDatabase();
                count = db.update(UploadFiles.TABLE_NAME, values, selection, selectionArgs);
                break;

            case UPLOAD_FILE_ID:
                db = mOpenHelper.getWritableDatabase();
                long uploadFileInfoId = getUploadFilesId(uri);
                selectionCriteria = UploadFiles.Columns._ID + " = " + uploadFileInfoId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(UploadFiles.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            case FEED_BACK:
                db = mOpenHelper.getWritableDatabase();
                count = db.update(FeedBack.TABLE_NAME, values, selection, selectionArgs);
                break;

            case FEED_BACK_ID:
                db = mOpenHelper.getWritableDatabase();
                long feedBackId = getFeedBackId(uri);
                selectionCriteria = FeedBack.Columns._ID + " = " + feedBackId;

                if ((selection != null) && (selection.length() > 0)) {
                    selectionCriteria += " AND (" + selection + ")";
                }
                count = db.update(FeedBack.TABLE_NAME, values, selectionCriteria, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
        if (universityInfoId >= 0 || userInfoId >= 0 || contactInfoId >= 0 || companyInfoId >= 0 || businessTypeId >= 0 || uploadFilesId >= 0 || mFeedBackId >= 0) {
            Log.d(TAG, "Update: Setting Notify With Uri _ " + uri);
            getContext().getContentResolver().notifyChange(uri, null);
        } else {
            Log.d(TAG, "Update: Nothing Updated");
        }

        Log.d(TAG, "Exiting update, returning " + count);
        return count;
    }

}
