package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.signUp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.ContactInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UserInformation;
import stanford.androidlib.SimpleActivity;

public class UniversitySignUpActivity extends SimpleActivity {
    private static final String TAG = "UniversitySignUpActivit";

    private EditText mUserEmailTextView;
    private EditText mUserPasswordTextView;

    private EditText mUniversityNameTextView;
    private EditText mUniversityAddressTextView;
    private EditText mUniversityWebLinkTextView;

    private EditText mContractPersonNameTextView;
    private EditText mContractPersonEmailTextView;
    private EditText mContractPersonPhoneTextView;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private Button mSaveButton;

    public UniversitySignUpActivity() {
        Log.d(TAG, "UniversitySignUpActivity: constructor called");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_sign_up);
    }

    public void goToSignUpFeedback(View view) {
        Log.d(TAG, "goToSignUpFeedback: Starts");

        mUserEmailTextView = (EditText) findViewById(R.id.university_email_txt);
        mUserPasswordTextView = (EditText) findViewById(R.id.university_password);

        mUniversityNameTextView = (EditText) findViewById(R.id.university_name_txt);
        mUniversityAddressTextView = (EditText) findViewById(R.id.university_address);
        mUniversityWebLinkTextView = (EditText) findViewById(R.id.university_website_link);

        mContractPersonNameTextView = (EditText) findViewById(R.id.uni_contract_person_name);
        mContractPersonEmailTextView = (EditText) findViewById(R.id.uni_contract_person_email);
        mContractPersonPhoneTextView = (EditText) findViewById(R.id.uni_contract_person_phone);

        Bundle arguments = getIntent().getExtras();

        //    final UniversityInfoDto infoDto;
        if (arguments != null) {
            Log.d(TAG, "goToSignUpFeedback: arguments is not null");
        } else {
            Log.d(TAG, "goToSignUpFeedback: No arguments, adding new record");
            //    infoDto = null;
        }

        ContentResolver contentResolver = this.getContentResolver();

        ContentValues userInfoValues = new ContentValues();
        ContentValues uniInfoValues = new ContentValues();
        ContentValues contractInfoValues = new ContentValues();

        Log.d(TAG, "goToSignUpFeedback: Ends" + mUniversityNameTextView.length());
        //  Toast.makeText(UniversitySignUpActivity.this, "Button Clicked :"+mUniversityNameTextView.length(), Toast.LENGTH_SHORT).show();

        if (mUserEmailTextView.length() > 1) {
            Log.d(TAG, "onClick: adding new User Info");
            userInfoValues.put(UserInformation.Columns.USER_EMAIL, mUserEmailTextView.getText().toString());
            userInfoValues.put(UserInformation.Columns.USER_PASSWORD, mUserPasswordTextView.getText().toString());
            userInfoValues.put(UserInformation.Columns.USER_ACOUNT_STATUS, 1);
            userInfoValues.put(UserInformation.Columns.USER_ROLE_ID, 1);

            contentResolver.insert(UserInformation.CONTENT_URI, userInfoValues);
        } else {
            return;
        }

        if (mContractPersonNameTextView.length() > 1) {
            Log.d(TAG, "onClick: adding new Contact Information");
            contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_NAME, mContractPersonNameTextView.getText().toString());
            contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_EMAIL, mContractPersonEmailTextView.getText().toString());
            contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_PHONE, mContractPersonPhoneTextView.getText().toString());
            contentResolver.insert(ContactInformation.CONTENT_URI_CONTRACTS, contractInfoValues);
        } else {
            return;
        }

        if (mUniversityNameTextView.length() > 1) {
            Log.d(TAG, "onClick: adding new University Information");
            uniInfoValues.put(UniversityInformation.Columns.UNIVERSITY_NAME, mUniversityNameTextView.getText().toString());
            uniInfoValues.put(UniversityInformation.Columns.UNIVERSITY_ADDRESS, mUniversityAddressTextView.getText().toString());
            uniInfoValues.put(UniversityInformation.Columns.UNIVERSITY_URL, mUniversityWebLinkTextView.getText().toString());
            uniInfoValues.put(UniversityInformation.Columns.UNIVERSITY_IS_APPROVED, 0);
            uniInfoValues.put(UniversityInformation.Columns.CREATE_DATE, sdf.format(new Date()));
            uniInfoValues.put(UniversityInformation.Columns.MODIFIED_DATE, sdf.format(new Date()));

            contentResolver.insert(UniversityInformation.CONTENT_URI, uniInfoValues);

        } else {
            return;
        }

        if (mContractPersonNameTextView.length() > 1) {
            contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_NAME, mContractPersonNameTextView.getText().toString());
            contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_EMAIL, mContractPersonEmailTextView.getText().toString());
            contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_PHONE, mContractPersonPhoneTextView.getText().toString());
            contentResolver.insert(ContactInformation.CONTENT_URI_CONTRACTS, contractInfoValues);
        } else {
            return;
        }

        //open new Activity
        Intent signUpFeedbackIntent = new Intent(this, SignUpFeedback.class);
        startActivity(signUpFeedbackIntent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: Starts");
        super.onSaveInstanceState(outState);
    }


}