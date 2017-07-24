package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.CompanyInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UniversityInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;

public class AdminHomeUniversityActivity extends AppCompatActivity
        implements CursorRecyclerUniversityInfoViewAdapter.OnUniversityInfoClickListner,
        CursorRecyclerCompanyInfoViewAdapter.OnCompanyInfoClickListner,
        CursorRecyclerCompanyTypeInfoViewAdapter.OnCompanyTypeClickListner {

    private static final String TAG = "AdminHomeUniversityActi";

    //  private static final String ADD_EDIT_FRAGMENT_UNIVERSITY = "AddEditUniversityFragment";

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_university);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        //TODO : Add new tab for Admin
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AdminHomeUnivesityFragment(), "University");
        adapter.addFragment(new AdminHomeCompanyFragment(), "Company");
        adapter.addFragment(new AdminHomeCompanyBusinessTypeFragment(), "Business Type");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    // Manu Item Option
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
                //     setContentView(R.layout.activity_admin_home_university);
                //    universityInfoEditRequest(null);
                break;
            case R.id.company_permi:
                Log.d(TAG, "onOptionsItemSelected: Company Clicked");
                break;
            case R.id.student_permi:
                Log.d(TAG, "onOptionsItemSelected: Student Clicked");
                break;
            case R.id.business_type:
                Log.d(TAG, "onOptionsItemSelected: Business Type clicked");
                businessTypeEditRequest(null);
                break;
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    //University Option starts
    @Override
    public void onUniApplyClicked(UniversityInfoDto universityInfoDto) {
        universityInfoEditRequest(universityInfoDto);
    }

    @Override
    public void onUniDeleteClicked(final UniversityInfoDto universityInfoDto) {
        //TODO: ADD a Alert Dialog warning to Delete
        //    getContentResolver().delete(UniversityInformation.buildUniversityInformationUri(universityInfoDto.getid()), null, null);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        // Setting Dialog Title
        builder1.setTitle("Confirm Delete...");

        // Setting Dialog Message
        builder1.setMessage("Are you sure you want delete this !!!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getContentResolver().delete(UniversityInformation.buildUniversityInformationUri(universityInfoDto.getId()), null, null);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }


    private void universityInfoEditRequest(UniversityInfoDto universityInfo) {
        Log.d(TAG, "universityInfoEditRequest: Starts");

        Intent detailIntent = new Intent(this, AddEditApprovedActivity.class);
        if (universityInfo != null) {
            detailIntent.putExtra(UniversityInfoDto.class.getSimpleName(), universityInfo);
            startActivity(detailIntent);
        } else {
            startActivity(detailIntent);
        }
    }
    //University Option end


    //Company Option Start
    @Override
    public void onCompApplyClicked(CompanyInfoDto companyInfoDto) {
        Log.d(TAG, "onCompApplyClicked: companyInfoDto " + companyInfoDto);
        companyInfoEditRequest(companyInfoDto);
    }

    @Override
    public void onCompDeleteClicked(final CompanyInfoDto companyInfoDto) {

        //TODO: ADD a Alert Dialog warning to Delete
        //     getContentResolver().delete(CompanyInformation.buildCompanyInformationUri(companyInfoDto.getId()), null, null);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        // Setting Dialog Title
        builder1.setTitle("Confirm Delete...");

        // Setting Dialog Message
        builder1.setMessage("Are you sure you want delete this !!!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getContentResolver().delete(CompanyInformation.buildCompanyInformationUri(companyInfoDto.getId()), null, null);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    private void companyInfoEditRequest(CompanyInfoDto companyInfoDto) {
        Log.d(TAG, "companyInfoEditRequest: Starts");

        Intent detailIntent = new Intent(this, AddEditApprovedActivity.class);
        if (companyInfoDto != null) {
            detailIntent.putExtra(CompanyInfoDto.class.getSimpleName(), companyInfoDto);
            startActivity(detailIntent);
        } else {
            startActivity(detailIntent);
        }
    }
    //Company Option End

    //Business Type Option starts
    @Override
    public void oncComTypeApplyClicked(BusinessTypeDto businessTypeDto) {

        businessTypeEditRequest(businessTypeDto);
    }

    @Override
    public void onCompTypeDeleteClicked(final BusinessTypeDto businessTypeDto) {
        //TODO: ADD a Alert Dialog warning to Delete
        //    getContentResolver().delete(UniversityInformation.buildUniversityInformationUri(universityInfoDto.getid()), null, null);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        // Setting Dialog Title
        builder1.setTitle("Confirm Delete...");

        // Setting Dialog Message
        builder1.setMessage("Are you sure you want delete this !!!");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getContentResolver().delete(BusinessType.buildBusinessTypeUri(businessTypeDto.getId()), null, null);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }


    private void businessTypeEditRequest(BusinessTypeDto businessTypeDto) {
        Log.d(TAG, "businessTypeEditRequest: starts");

        Intent detailIntent = new Intent(this, AddEditCompanyTypeActivity.class);
        if (businessTypeDto != null) {
            detailIntent.putExtra(BusinessTypeDto.class.getSimpleName(), businessTypeDto);
            startActivity(detailIntent);
        } else {
            startActivity(detailIntent);
        }
    }
    //Business Type Option end

}
