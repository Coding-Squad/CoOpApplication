package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY;
import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY_URI;

/**
 * Created by Pranto on 10-Jun-17.
 */

public class ContactInformation {
    private static final String TAG = "ContactInformation";

    public static final String TABLE_NAME = "ContactInformation";


    // University Information fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String CONTACT_PERSON_NAME = "PersonName";
        public static final String CONTACT_PERSON_EMAIL = "Email";
        public static final String CONTACT_PERSON_PHONE = "Phone";

        private Columns() {
            // private constructor to prevent instantiation
        }
    }

    /**
     * The URI to access the ContactInformation table
     */
    public static final Uri CONTENT_URI_CONTRACTS = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;


    //For Query Method
    public static Uri buildContactInformationUri(long contactInformationId) {
        return ContentUris.withAppendedId(CONTENT_URI_CONTRACTS, contactInformationId);
    }

    //For Get Query Method
    public static long getContactInformationId(Uri uri) {
        return ContentUris.parseId(uri);
    }


}
