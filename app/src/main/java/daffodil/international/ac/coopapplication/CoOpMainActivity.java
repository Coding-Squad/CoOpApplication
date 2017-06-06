package daffodil.international.ac.coopapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import stanford.androidlib.SimpleActivity;

public class CoOpMainActivity extends SimpleActivity {
    private static final String TAG = "CoOpMainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_op_main);
    }

    public void goToSignUpAsPage(View view) {
        Intent signUpPageIntent = new Intent
                (this, SignUpAsActivity.class);
        startActivity(signUpPageIntent);
    }

    public void goToSignInPage(View view) {
        Intent signInPageIntent = new Intent(this, SignInActivity.class);
        startActivity(signInPageIntent);

    }
}
