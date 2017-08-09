package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY;
import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY_URI;

/**
 * Created by Pranto on 24-Jul-17.
 */

public class UploadFiles {
    private static final String TAG = "UploadFiles";
/*

    fileType    Type name
    1           Cover Photo
    2           profile Picture
    3


);*/

    public static final String TABLE_NAME = "UploadFiles";

    // User Information fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String FILE_TYPE = "fileType";
        public static final String FILE_SIZE = "filesize";
        public static final String FILE_ = "file";

        public static final String USER_ID = "UserId";
        public static final String CREATE_DATE = "CreateDate";
        public static final String MODIFIED_DATE = "ModifiedDate";


        private Columns() {
            // private constructor to prevent instantiation
        }
    }

    public static final Uri CONTENT_URI = Uri.withAppendedPath(CONTENT_AUTHORITY_URI, TABLE_NAME);
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + "." + TABLE_NAME;

    //For Query Method
    public static Uri buildUploadFilesUri(long uploadFilesId) {
        return ContentUris.withAppendedId(CONTENT_URI, uploadFilesId);
    }


    //For Query Method
    public static long getUploadFilesId(Uri uri) {
        return ContentUris.parseId(uri);
    }


}
