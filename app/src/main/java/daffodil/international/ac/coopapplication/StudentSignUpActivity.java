package daffodil.international.ac.coopapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.StudentInformationDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UniversityInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.StudentInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;
import stanford.androidlib.SimpleActivity;

public class StudentSignUpActivity extends SimpleActivity {
    private static final String TAG = "StudentSignUpActivity";

    private EditText        mStudentFirstNameTextView;
    private EditText        mLastNameTextView;
    private EditText        mStudentAddressTextView;
    private EditText        mStudentMobileTextView;
    private Spinner         mStudentUniversityNameSpinner;
    private EditText        mStudentIDTextView;
    private EditText        mStudentDescription;
    private EditText        mStudentDateOfBirth;
    private RadioGroup      mRadioButtonGroup;
    private RadioButton     mStudentGenderRadioButton;

    private Button mSaveButton;

    public StudentSignUpActivity(){
        Log.d(TAG, "StudentSignUpActivity: constructor called");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
    }

    public void goToSignUpFeedback(View view) {
        Log.d(TAG, "goToSignUpFeedback: Starts");

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
        ContentValues studentInformationValues = new ContentValues();
        ContentValues contractInfoValues = new ContentValues();


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
        /*studentInformationValues.put(StudentInformation.Columns.GENDER,
                mStudentGenderRadioButton.getText().toString());*/

        contentResolver.insert(StudentInformation.CONTENT_URI, studentInformationValues);






        Intent signUpFeedbackIntent = new Intent(this, SignUpFeedback.class);
        startActivity(signUpFeedbackIntent);
    }
}
