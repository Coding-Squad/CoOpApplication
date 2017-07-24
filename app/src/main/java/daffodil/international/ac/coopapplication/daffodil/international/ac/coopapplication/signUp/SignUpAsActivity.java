package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company.EmployerHomeActivity;
import stanford.androidlib.SimpleActivity;

public class SignUpAsActivity extends SimpleActivity {
    private static final String TAG = "SignUpAsActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_as);
    }

    public void goToUniversitySignUpPage(View view) {
        Intent universitySignUpIntent = new Intent(this, UniversitySignUpActivity.class);
        startActivity(universitySignUpIntent);

    }

    public void goToStudentSignUpPage(View view) {
        Intent studentSignUpIntent = new Intent(this, StudentSignUpActivity.class);
        startActivity(studentSignUpIntent);

    }

    public void goToCompanySignUpPage(View view) {
        Intent companySignUpIntent = new Intent(this, EmployerSignUpActivity.class);
        startActivity(companySignUpIntent);
    }

    // TODO : Delete this Code later along with @{link goToAdminHomePage} and Button on signUpAs

    public void goToCompanyHomePage(View view) {
        Intent companyHomeIntent = new Intent(this, EmployerHomeActivity.class);
        startActivity(companyHomeIntent);
    }

}
