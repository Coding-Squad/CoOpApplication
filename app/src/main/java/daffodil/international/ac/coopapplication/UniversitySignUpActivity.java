package daffodil.international.ac.coopapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UniversityInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.ContactInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;
import stanford.androidlib.SimpleActivity;

public class UniversitySignUpActivity extends SimpleActivity {
    private static final String TAG = "UniversitySignUpActivit";

    private EditText mUniversityNameTextView;
    private EditText mUniversityAddressTextView;
    private EditText mUniversityWebLinkTextView;

    private EditText mContractPersonNameTextView;
    private EditText mContractPersonEmailTextView;
    private EditText mContractPersonPhoneTextView;

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

        mUniversityNameTextView = (EditText) findViewById(R.id.university_name_txt);
        mUniversityAddressTextView = (EditText) findViewById(R.id.university_address);
        mUniversityWebLinkTextView = (EditText) findViewById(R.id.university_website_link);

        mContractPersonNameTextView = (EditText) findViewById(R.id.uni_contract_person_name);
        mContractPersonEmailTextView = (EditText) findViewById(R.id.uni_contract_person_email);
        mContractPersonPhoneTextView = (EditText) findViewById(R.id.uni_contract_person_phone);

        Bundle arguments = getIntent().getExtras();

        final UniversityInfoDto infoDto;
        if (arguments != null) {
            Log.d(TAG, "goToSignUpFeedback: arguments is not null");
        } else {
            Log.d(TAG, "goToSignUpFeedback: No arguments, adding new record");
            infoDto = null;
        }

        ContentResolver contentResolver = this.getContentResolver();
        ContentValues uniInfoValues = new ContentValues();
        ContentValues contractInfoValues = new ContentValues();

        Log.d(TAG, "goToSignUpFeedback: Ends" + mUniversityNameTextView.length());
        //  Toast.makeText(UniversitySignUpActivity.this, "Button Clicked :"+mUniversityNameTextView.length(), Toast.LENGTH_SHORT).show();

        if (mUniversityNameTextView.length() > 1) {
            Log.d(TAG, "onClick: adding new task");
            uniInfoValues.put(UniversityInformation.Columns.UNIVERSITY_NAME, mUniversityNameTextView.getText().toString());
            uniInfoValues.put(UniversityInformation.Columns.UNIVERSITY_ADDRESS, mUniversityAddressTextView.getText().toString());
            uniInfoValues.put(UniversityInformation.Columns.UNIVERSITY_URL, mUniversityWebLinkTextView.getText().toString());
            uniInfoValues.put(UniversityInformation.Columns.CONTRACTS_ID, mUniversityWebLinkTextView.getText().toString());

            if (mContractPersonNameTextView.length() > 1) {
                contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_NAME, mContractPersonNameTextView.getText().toString());
                contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_EMAIL, mContractPersonEmailTextView.getText().toString());
                contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_PHONE, mContractPersonPhoneTextView.getText().toString());
                contentResolver.insert(ContactInformation.CONTENT_URI_CONTRACTS, contractInfoValues);
            } else {
                return;
            }
            contentResolver.insert(UniversityInformation.CONTENT_URI, uniInfoValues);
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
