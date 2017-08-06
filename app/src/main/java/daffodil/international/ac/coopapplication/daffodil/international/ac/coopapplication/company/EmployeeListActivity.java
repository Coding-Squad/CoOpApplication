package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.StudentInformationDto;

public class EmployeeListActivity extends AppCompatActivity implements CursorRecyclerStudentListViewAdapter.OnStudentListClickListener {
    private static final String TAG = "EmployeeListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onStudentListDetailsClicked(StudentInformationDto studentInformationDto) {
        Log.d(TAG, "onStudentListDetailsClicked: " + studentInformationDto);
    }

    @Override
    public void onStudentListHireClicked(StudentInformationDto studentInformationDto) {
        Log.d(TAG, "onStudentListHireClicked: " + studentInformationDto);

    }

    @Override
    public void onStudentListWishListClicked(StudentInformationDto studentInformationDto) {
        Log.d(TAG, "onStudentListWishListClicked: " + studentInformationDto);

    }
}
