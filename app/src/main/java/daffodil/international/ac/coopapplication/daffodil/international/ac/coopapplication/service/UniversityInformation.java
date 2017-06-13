package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY;
import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY_URI;

/**
 * Created by Pranto on 08-Jun-17.
 */

public class UniversityInformation {

    public static final String TABLE_NAME = "UniversityInformation";


    // University Information fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String UNIVERSITY_NAME = "UniversityName";
        public static final String UNIVERSITY_ADDRESS = "UniversityAddress";
        public static final String UNIVERSITY_URL = "UniversityWebURL";
        public static final String CONTRACTS_ID = "ContactId";
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
    public static Uri buildUniversityInformationUri(long universityInformationId) {
        return ContentUris.withAppendedId(CONTENT_URI, universityInformationId);
    }

    //For Query Method
    public static long getUniversityInformationId(Uri uri) {
        return ContentUris.parseId(uri);
    }


}
