package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY;
import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY_URI;

/**
 * Created by Pranto on 11-Jun-17.
 */

public class CompanyInformation {

    public static final String TABLE_NAME = "CompanyInformation";


    // Company Information fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String COMPANY_NAME = "CompanyName";
        public static final String COMPANY_WEB_URL = "CompanyWebsiteUrl";
        public static final String COMPANY_ADDRESS = "CompanyAddress";
        public static final String COMPANY_BUSI_TYPE_ID = "CompanyBusinessTypeID";

        public static final String USER_ID = "UserId";
        public static final String CONTRACTS_ID = "ContactId";


        private Columns() {
            // private constructor to prevent instantiation
        }
    }

    /**
     * The URI to access the CompanyInformation table
     */
    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;


    //For Query Method
    public static Uri buildCompanyInformationUri(long companyInformationId) {
        return ContentUris.withAppendedId(CONTENT_URI, companyInformationId);
    }

    //For Query Method
    public static long getCompanyInformationId(Uri uri) {
        return ContentUris.parseId(uri);
    }

}
