package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY;
import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY_URI;

/**
 * Created by Pranto on 11-Jun-17.
 */

public class UserInformation {

    public static final String TABLE_NAME = "UserInformation";

    // User Information fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String USER_EMAIL = "Email";
        public static final String USER_PASSWORD = "Password";
        public static final String USER_ACOUNT_STATUS = "AcountStatus";
        public static final String USER_SECRET_QUESTION = "secretQuestion";
        public static final String USER_ROLE_ID = "userRole";

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
    public static Uri buildUserInformationUri(long userInformationId) {
        return ContentUris.withAppendedId(CONTENT_URI, userInformationId);
    }

    //For Query Method
    public static long getUserInformationId(Uri uri) {
        return ContentUris.parseId(uri);
    }


}