package daffodil.international.ac.coopapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignUpAsActivity extends AppCompatActivity {

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
}
