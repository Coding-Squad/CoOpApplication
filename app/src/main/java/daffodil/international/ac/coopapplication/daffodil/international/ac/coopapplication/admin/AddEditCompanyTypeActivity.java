package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import daffodil.international.ac.coopapplication.R;

public class AddEditCompanyTypeActivity extends AppCompatActivity {
    private static final String TAG = "AddEditCompanyTypeActiv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_company_type);

    }
}
