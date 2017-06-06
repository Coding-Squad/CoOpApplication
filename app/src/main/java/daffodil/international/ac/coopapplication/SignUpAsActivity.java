package daffodil.international.ac.coopapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import stanford.androidlib.SimpleActivity;

public class SignUpAsActivity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_as);

        // Scrollbar activation for signup as linear layout.

       /* LinearLayout signUpAsLinearLayout = (LinearLayout) findViewById(R.id.signupas_linearLayout);
        signUpAsLinearLayout.addView();*/

    }


    // Button actionlistener to go to university signup page.

    public void goToUniversitySignUpPage(View view) {
        Intent universitySignUpIntent = new Intent(this, UniversitySignUpActivity.class);
        startActivity(universitySignUpIntent);

    }


    // Button actionlistener to go to student signup page.

    public void goToStudentSignUpPage(View view) {
        Intent studentSignUpIntent = new Intent(this, StudentSignUpActivity.class);
        startActivity(studentSignUpIntent);

    }
}
