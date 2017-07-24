package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY;
import static daffodil.international.ac.coopapplication.AppProvider.CONTENT_AUTHORITY_URI;

/**
 * Created by Pranto on 24-Jul-17.
 */

public class FeedBack {

    public static final String TABLE_NAME = "FeedBack";


    /*CREATE TABLE feedback (
            feedbackId LONG PRIMARY KEY NOT NULL DEFAULT "0",
            submissionByUserId LONG,
            ratedUserId LONG,
            review VARCHAR(75),
            rating VARCHAR
    );*/

    // User Information fields
    public static class Columns {
        public static final String _ID = BaseColumns._ID;
        public static final String SUBMISSION_BY_USER_ID = "SubmissionByUserId";
        public static final String RATED_USER_ID = "ratedUserId";
        public static final String REVIEW_COMMENTS = "reviewComments";
        public static final String REVIEW_RATTING = "reviewRating";

        public static final String CREATE_DATE = "CreateDate";
        public static final String MODIFIED_DATE = "ModifiedDate";

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
    public static Uri buildFeedBackUri(long feedBackId) {
        return ContentUris.withAppendedId(CONTENT_URI, feedBackId);
    }

    //For Query Method
    public static long getFeedBackId(Uri uri) {
        return ContentUris.parseId(uri);
    }


}
