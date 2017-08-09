package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.signUp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.List;

import daffodil.international.ac.coopapplication.AppDatabaseHelper;
import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.StudentInformationDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UniversityInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.StudentInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UserInformation;
import stanford.androidlib.SimpleActivity;

public class StudentSignUpActivity extends SimpleActivity {
    private static final String TAG = "StudentSignUpActivity";

    private EditText mUserEmailTextView;
    private EditText mUserPasswordTextView;

    private EditText mStudentFirstNameTextView;
    private EditText mLastNameTextView;
    private EditText mStudentAddressTextView;
    private EditText mStudentMobileTextView;
    private Spinner mStudentUniversityNameSpinner;
    private EditText mStudentIDTextView;
    private EditText mStudentDescription;
    private EditText mStudentDateOfBirth;
    private RadioGroup mRadioButtonGroup;
    private RadioButton mStudentGenderRadioButton;

    private Spinner universitySpinner;

    List<UniversityInfoDto> approvedUniversityListDto = null;

    long approvedUniversityId;

    int gender;

    private Button mSaveButton;

    public StudentSignUpActivity() {
        Log.d(TAG, "StudentSignUpActivity: constructor called");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        universitySpinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        universitySpinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerUniversityData();

    }

    public void goToSignUpFeedback(View view) {
        Log.d(TAG, "goToSignUpFeedback: Starts");

        mUserEmailTextView = (EditText) findViewById(R.id.stu_email_address);
        mUserPasswordTextView = (EditText) findViewById(R.id.stu_password);

        mStudentFirstNameTextView = (EditText) findViewById(R.id.stu_first_name);
        mLastNameTextView = (EditText) findViewById(R.id.stu_last_name);
        mStudentAddressTextView = (EditText) findViewById(R.id.stu_address);
        //mStudentMobileTextView = (EditText) findViewById(R.id.stu_mobile);
        //mStudentGenderRadioButton = (RadioButton) findViewById(mRadioButtonGroup.getCheckedRadioButtonId());
        mStudentUniversityNameSpinner = (Spinner) findViewById(R.id.spinner);
        mStudentIDTextView = (EditText) findViewById(R.id.stu_university_student_id);
        mStudentDescription = (EditText) findViewById(R.id.stu_about_me);


        //String radiovalue = ((RadioButton)findViewById(mStudentGender.getCheckedRadioButtonId())).getText().toString();

        Bundle arguments = getIntent().getExtras();

        final StudentInformationDto studentInformationDto;
        if (arguments != null) {
            Log.d(TAG, "goToSignUpFeedback: arguments is not null");
        } else {
            Log.d(TAG, "goToSignUpFeedback: No arguments, adding new record");
            studentInformationDto = null;
        }

        ContentResolver contentResolver = this.getContentResolver();
        ContentValues userInfoValues = new ContentValues();
        ContentValues studentInformationValues = new ContentValues();
        ContentValues contractInfoValues = new ContentValues();

        //  ContentValues contractInfoValues = new ContentValues();

        if (mUserEmailTextView.length() > 1) {
            Log.d(TAG, "onClick: adding new task");
            userInfoValues.put(UserInformation.Columns.USER_EMAIL, mUserEmailTextView.getText().toString());
            userInfoValues.put(UserInformation.Columns.USER_PASSWORD, mUserPasswordTextView.getText().toString());
            userInfoValues.put(UserInformation.Columns.USER_ACOUNT_STATUS, 1);
            userInfoValues.put(UserInformation.Columns.USER_ROLE_ID, 3);

            contentResolver.insert(UserInformation.CONTENT_URI, userInfoValues);
        } else {
            return;
        }


        Log.d(TAG, "goToSignUpFeedback: Ends" + mStudentFirstNameTextView.length());
        //  Toast.makeText(StudentSignUpActivity.this, "Button Clicked :"+mStudentFirstNameTextView.length(), Toast.LENGTH_SHORT).show();

        /*if (mStudentFirstNameTextView.length() > 1){*/
        Log.d(TAG, "onClick: adding new task");

        studentInformationValues.put(StudentInformation.Columns.FIRST_NAME,
                mStudentFirstNameTextView.getText().toString());
        studentInformationValues.put(StudentInformation.Columns.LAST_NAME,
                mLastNameTextView.getText().toString());
        studentInformationValues.put(StudentInformation.Columns.ADDRESS,
                mStudentAddressTextView.getText().toString());
        studentInformationValues.put(StudentInformation.Columns.STUDENT_ID,
                mStudentIDTextView.getText().toString());
        studentInformationValues.put(StudentInformation.Columns.DESCRIPTION,
                mStudentDescription.getText().toString());
        studentInformationValues.put(StudentInformation.Columns.UNIVERSITY_ID,
                approvedUniversityId);
       /* studentInformationValues.put(StudentInformation.Columns.MOBILE_NUMBER,
                mStudentMobileTextView.getText().toString());
        studentInformationValues.put(StudentInformation.Columns.DATE_OF_BIRTH,
                mStudentDateOfBirth.getText().toString());*/
        /*studentInformationValues.put(StudentInformation.Columns.GENDER,
                mStudentGenderRadioButton.getText().toString());*/

        contentResolver.insert(StudentInformation.CONTENT_URI, studentInformationValues);


        Intent signUpFeedbackIntent = new Intent(this, SignUpFeedback.class);
        startActivity(signUpFeedbackIntent);
    }

    private void loadSpinnerUniversityData() {
        // database handler
        AppDatabaseHelper db = new AppDatabaseHelper(getApplicationContext());
        // Spinner Drop down elements
        //  List<String> lables = db.getAllLabels();
        approvedUniversityListDto = db.getAllApprovedUniversity();

        // Creating adapter for spinner
        ArrayAdapter<UniversityInfoDto> universityDtoDataAdapter =
                new ArrayAdapter<UniversityInfoDto>
                        (this, android.R.layout.simple_spinner_item, approvedUniversityListDto);

        // Drop down layout style - list view with radio button
        universityDtoDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        universitySpinner.setAdapter(universityDtoDataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        super.onItemSelected(parent, view, position, id);

        String label = parent.getItemAtPosition(position).toString();
        if (approvedUniversityListDto.size() > 0 && approvedUniversityListDto != null) {
            for (UniversityInfoDto dto : approvedUniversityListDto) {
                if (label.equalsIgnoreCase(dto.getUniversityName())) {
                    Log.d(TAG, "onItemSelected: Id : " + dto.getId() + " , Name : " + dto.getUniversityName());
                    approvedUniversityId = dto.getId();
                }
            }
        }
    }

    public void onRadioButtonClicked(View view) {
        //Is that button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.stu_male:
                if (checked)
                    gender = 1;
                Log.d(TAG, "male checked : " + gender);
                break;
            case R.id.stu_female:
                if (checked)
                    gender = 2;
                Log.d(TAG, "Female checked : " + gender);
                break;
        }
    }


}
