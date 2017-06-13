package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY;
import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY_URI;

/**
 * Created by Programmer on 10-Jun-17.
 */

public class StudentInformation {


    public static final String TABLE_NAME = "StudentInformation";


    // Student Information fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String FIRST_NAME = "FirstName";
        public static final String LAST_NAME = "LastName";
        public static final String DATE_OF_BIRTH = "DateofBirth";
        public static final String ADDRESS = "address";
        public static final String GENDER = "gender";
        public static final String STUDENT_UNIVERSITY_NAME = "UniversityName";
        public static final String STUDENT_ID = "StudentId";
        public static final String STUDENT_PHONE_NUMBER = "Phone_Number";
        public static final String DESCRIPTION = "Description";
        public static final String USER_ID = "UserID";

        private Columns() {
            // private constructor to prevent instantiation
        }
    }


    /**
     * The URI to access the UniversityInformation table
     */
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;


    //For Query Method
    public static Uri buildStudentInformationUri(long studentInformationId) {
        return ContentUris.withAppendedId(CONTENT_URI, studentInformationId);
    }

    //For Query Method
    public static long getStudentInformationId(Uri uri) {
        return ContentUris.parseId(uri);
    }


}
