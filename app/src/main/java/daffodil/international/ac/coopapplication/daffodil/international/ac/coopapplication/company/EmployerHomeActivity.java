package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;

public class EmployerHomeActivity extends AppCompatActivity implements CursorRecyclerCompanyHomeViewAdapter.OnCompanyHomeClickListener {

    private static final String TAG = "EmployerHomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_home);

    }

    @Override
    public void onCompDetailsClicked(BusinessTypeDto businessTypeDto) {
        Log.d(TAG, "onCompDetailsClicked: businessTypeDto >> " + businessTypeDto);
    }
}
