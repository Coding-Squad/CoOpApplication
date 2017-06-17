package daffodil.international.ac.coopapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.ContactInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UserInformation;
import stanford.androidlib.SimpleActivity;

public class EmployerSignUpActivity extends SimpleActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "EmployerSignUpActivity";

    private EditText mUserEmailTextView;
    private EditText mUserPasswordTextView;

    private EditText mCompanyNameTextView;
    private EditText mCompanyWebLinkTextView;
    private EditText mCompanyAddressTextView;
    // private EditText mCompanyBusinessTypeTextView;
    private Spinner spinner;

    private EditText mContractPersonNameTextView;
    private EditText mContractPersonEmailTextView;
    private EditText mContractPersonPhoneTextView;

    List<BusinessTypeDto> businessTypeDtos = null;
    long businessTypeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_sign_up);

        spinner = (Spinner) findViewById(R.id.company_business_type_spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();

    }

    public void goToSignUpFeedback(View view) {
        Log.d(TAG, "goToSignUpFeedback: Starts");

        mUserEmailTextView = (EditText) findViewById(R.id.company_email_txt);
        mUserPasswordTextView = (EditText) findViewById(R.id.company_password);

        mCompanyNameTextView = (EditText) findViewById(R.id.company_name_txt);
        mCompanyWebLinkTextView = (EditText) findViewById(R.id.company_website_link);
        mCompanyAddressTextView = (EditText) findViewById(R.id.company_address);

        //  mCompanyBusinessTypeTextView = (EditText) findViewById(R.id.company_business_type_spinner);


        mContractPersonNameTextView = (EditText) findViewById(R.id.company_contract_person_name);
        mContractPersonEmailTextView = (EditText) findViewById(R.id.company_contract_person_email);
        mContractPersonPhoneTextView = (EditText) findViewById(R.id.company_contract_person_phone);

        Bundle arguments = getIntent().getExtras();

        //   final CompanyInfoDto infoDto;
        if (arguments != null) {
            Log.d(TAG, "goToSignUpFeedback: arguments is not null");
        } else {
            Log.d(TAG, "goToSignUpFeedback: No arguments, adding new record");
            //    infoDto = null;
        }

        ContentResolver contentResolver = this.getContentResolver();

        ContentValues userInfoValues = new ContentValues();
        ContentValues contractInfoValues = new ContentValues();
        ContentValues companyInfoValues = new ContentValues();

        if (mUserEmailTextView.length() > 1) {
            Log.d(TAG, "onClick: adding new task");
            userInfoValues.put(UserInformation.Columns.USER_EMAIL, mUserEmailTextView.getText().toString());
            userInfoValues.put(UserInformation.Columns.USER_PASSWORD, mUserPasswordTextView.getText().toString());
            userInfoValues.put(UserInformation.Columns.USER_ACOUNT_STATUS, 1);
            userInfoValues.put(UserInformation.Columns.USER_ROLE_ID, 2);

            contentResolver.insert(UserInformation.CONTENT_URI, userInfoValues);
        } else {
            return;
        }

        if (mContractPersonNameTextView.length() > 1) {
            contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_NAME, mContractPersonNameTextView.getText().toString());
            contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_EMAIL, mContractPersonEmailTextView.getText().toString());
            contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_PHONE, mContractPersonPhoneTextView.getText().toString());
            contractInfoValues.put(ContactInformation.Columns.CONTACT_PERSON_PHONE, mContractPersonPhoneTextView.getText().toString());
            contentResolver.insert(ContactInformation.CONTENT_URI_CONTRACTS, contractInfoValues);
        } else {
            return;
        }

        if (mCompanyNameTextView.length() > 1) {
            Log.d(TAG, "onClick: adding new task");
            companyInfoValues.put(CompanyInformation.Columns.COMPANY_NAME, mCompanyNameTextView.getText().toString());
            companyInfoValues.put(CompanyInformation.Columns.COMPANY_WEB_URL, mCompanyWebLinkTextView.getText().toString());
            companyInfoValues.put(CompanyInformation.Columns.COMPANY_ADDRESS, mCompanyAddressTextView.getText().toString());
            companyInfoValues.put(CompanyInformation.Columns.COMPANY_BUSI_TYPE_ID, businessTypeId);
            contentResolver.insert(CompanyInformation.CONTENT_URI, companyInfoValues);
        } else {
            return;
        }

        //Open new Activity
        Intent signUpFeedbackIntent = new Intent(this, SignUpFeedback.class);
        startActivity(signUpFeedbackIntent);
    }

    private void loadSpinnerData() {
        AppDatabaseHelper db = new AppDatabaseHelper(getApplicationContext());
        // Spinner Drop down elements
        businessTypeDtos = db.getAllBusinesstype();
        // Creating adapter for spinner
        ArrayAdapter<BusinessTypeDto> dataAdapter = new ArrayAdapter<BusinessTypeDto>(this, android.R.layout.simple_spinner_item, businessTypeDtos);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        super.onItemSelected(parent, view, position, id);

        String label = parent.getItemAtPosition(position).toString();
        if (businessTypeDtos.size() > 0 && businessTypeDtos != null) {
            for (BusinessTypeDto dto : businessTypeDtos) {
                if (label.equalsIgnoreCase(dto.getBusinessTypeName())) {
                    Log.d(TAG, "onItemSelected: Id : " + dto.getId() + " , Name : " + dto.getBusinessTypeName());
                    businessTypeId = dto.getId();
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}
