package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY;
import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY_URI;

/**
 * Created by Pranto on 12-Jun-17.
 */

public class BusinessType {

    private static final String TAG = "BusinessType";

    public static final String TABLE_NAME = "BusinessType";


    // User Information fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String BUSINESS_TYPE_NAME = "BusinessTypeName";


        private Columns() {
            // private constructor to prevent instantiation
        }
    }

    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    //For Query Method
    public static Uri buildBusinessTypeUri(long businessTypeId) {
        return ContentUris.withAppendedId(CONTENT_URI, businessTypeId);
    }

    //For Query Method
    public static long getBusinessTypeId(Uri uri) {
        return ContentUris.parseId(uri);
    }

}
