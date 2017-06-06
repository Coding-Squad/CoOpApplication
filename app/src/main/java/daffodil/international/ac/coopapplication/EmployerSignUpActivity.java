package daffodil.international.ac.coopapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import stanford.androidlib.SimpleActivity;

public class EmployerSignUpActivity extends SimpleActivity {
    private static final String TAG = "EmployerSignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_sign_up);
    }

    public void goToSignUpFeedback(View view) {
        Log.d(TAG, "goToSignUpFeedback: Starts");
        Intent signUpFeedbackIntent = new Intent(this, SignUpFeedback.class);
        startActivity(signUpFeedbackIntent);
    }
}
