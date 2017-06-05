package daffodil.international.ac.coopapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CoOpMainActivity extends AppCompatActivity {
    private static final String TAG = "CoOpMainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_op_main);

       /* final Button button =(Button) findViewById(R.id.student_signup_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Log.d(TAG, "onClick: starts");
         //       Toast.makeText(CoOpMainActivity.this, "Sign up clicked ", Toast.LENGTH_SHORT).show();

              *//*  Intent intent = new Intent(CoOpMainActivity.this,StudentSignUpActivity.class);
                CoOpMainActivity.this.startActivity(intent);*//*

            }
        });


*/
    }

    public void goToSignUpAsPage(View view) {
        Intent signUpPageIntent = new Intent
                (this, SignUpAsActivity.class);
        startActivity(signUpPageIntent);
    }
}
