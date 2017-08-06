package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;

public class EmployerHomeActivity extends AppCompatActivity implements CursorRecyclerCompanyHomeViewAdapter.OnCompanyHomeClickListener {

    private static final String TAG = "EmployerHomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_home);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public void onCompDetailsClicked(BusinessTypeDto businessTypeDto) {
        Log.d(TAG, "onCompDetailsClicked: businessTypeDto >> " + businessTypeDto.getBusinessTypeName());

        Intent detailIntent = new Intent(this, EmployeeListActivity.class);
        if (businessTypeDto != null) {

            detailIntent.putExtra(BusinessTypeDto.class.getSimpleName(), businessTypeDto);
            startActivity(detailIntent);
        } else {
            startActivity(detailIntent);
        }
    }
}
