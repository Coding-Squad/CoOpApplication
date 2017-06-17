package daffodil.international.ac.coopapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UniversityInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;

public class AdminHomeActivity extends AppCompatActivity implements CursorRecyclerViewAdapter.OnUniversityInfoClickListner {
    private static final String TAG = "AdminHomeActivity";

    private static final String ADD_EDIT_FRAGMENT_UNIVERSITY = "AddEditUniversityFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.university_permi:
                Log.d(TAG, "onOptionsItemSelected: University Clicked");
                //     universityInfoEditRequest(null);
                //     setContentView(R.layout.activity_admin_home);
                break;
            case R.id.company_permi:
                Log.d(TAG, "onOptionsItemSelected: Company Clicked");
                //Add new fragment To open On manu item click
                //    setContentView(R.layout.activity_add_edit_university);
                break;
            case R.id.student_permi:
                Log.d(TAG, "onOptionsItemSelected: Student Clicked");
                break;
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onAppalyClicked(UniversityInfoDto universityInfoDto) {
        universityInfoEditRequest(universityInfoDto);
    }

    @Override
    public void onDeleteClicked(UniversityInfoDto universityInfoDto) {
        getContentResolver().delete(UniversityInformation.buildUniversityInformationUri(universityInfoDto.getid()), null, null);
    }

    private void universityInfoEditRequest(UniversityInfoDto universityInfo) {
        Log.d(TAG, "universityInfoEditRequest: Starts");
        Intent detailIntent = new Intent(this, AddEditUniversityActivity.class);
        if (universityInfo != null) {
            detailIntent.putExtra(UniversityInfoDto.class.getSimpleName(), universityInfo);
            startActivity(detailIntent);
        } else {
            startActivity(detailIntent);
        }
    }

}
