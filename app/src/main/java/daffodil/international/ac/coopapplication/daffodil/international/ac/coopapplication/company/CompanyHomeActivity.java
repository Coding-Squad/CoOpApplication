package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import daffodil.international.ac.coopapplication.R;

public class CompanyHomeActivity extends AppCompatActivity {

    private static final String TAG = "CompanyHomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_home);

    }


}
