package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.admin;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.CompanyInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UniversityInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddEditApprovedActivityFragment extends Fragment {
    private static final String TAG = "AddEditUniversityActivi";

    public enum FragmentEditMode {EDIT_UNI, ADD_UNI, EDIT_COM, ADD_COM, EDIT_TYPE, ADD_TYPE}

    private FragmentEditMode mMode;

    private EditText mUniversityName;
    private EditText mUniversityAddress;
    private EditText mUniversityWebLink;

    private EditText mCompanyName;
    private EditText mCompanyAddress;
    private EditText mCompanyWebLink;

   /* private EditText mBusinessTypeName;*/

    private EditText mBusinessTypeName;

    private Button mSaveButton;

    private Button mSaveButton2;

    UniversityInfoDto universityInfo;
    CompanyInfoDto companyInfo;
    BusinessTypeDto businessTypeDto;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public AddEditApprovedActivityFragment() {
        Log.d(TAG, "AddEditApprovedActivityFragment: Constructor called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starts");

        View view = inflater.inflate(R.layout.fragment_add_edit_university, container, false);

        mUniversityName = (EditText) view.findViewById(R.id.approve_uni_name);
        mUniversityAddress = (EditText) view.findViewById(R.id.approve_uni_address);
        mUniversityWebLink = (EditText) view.findViewById(R.id.approve_uni_web_link);

        mCompanyName = (EditText) view.findViewById(R.id.approve_uni_name);
        mCompanyAddress = (EditText) view.findViewById(R.id.approve_uni_address);
        mCompanyWebLink = (EditText) view.findViewById(R.id.approve_uni_web_link);

     /*   mBusinessTypeName  = (EditText) view.findViewById(R.id.ctype_item_name);*/
        mBusinessTypeName = (EditText) view.findViewById(R.id.ctype_item_name);

        mSaveButton = (Button) view.findViewById(R.id.approve_uni_button);
        mSaveButton2 = (Button) view.findViewById(R.id.companyTypeButton_save);

        Bundle arguments = getActivity().getIntent().getExtras();

        if (arguments != null) {
            Log.d(TAG, "onCreateView: retrieving University Info");
            universityInfo = (UniversityInfoDto) arguments.getSerializable(UniversityInfoDto.class.getSimpleName());
            if (universityInfo != null) {
                Log.d(TAG, "onCreateView: Details found Editing ");
                mUniversityName.setText(universityInfo.getUniversityName());
                mUniversityAddress.setText(universityInfo.getUniversityAddress());
                mUniversityWebLink.setText(universityInfo.getUniversityWebURL());

                mMode = FragmentEditMode.EDIT_UNI;
            } else {
                mMode = FragmentEditMode.ADD_UNI;
            }
        } else {
            universityInfo = null;
            Log.d(TAG, "onCreateView : no universityInfo argument Found Adding new");
            mMode = FragmentEditMode.ADD_UNI;
        }

        if (arguments != null) {
            Log.d(TAG, "onCreateView: retrieving Company Info");
            companyInfo = (CompanyInfoDto) arguments.getSerializable(CompanyInfoDto.class.getSimpleName());
            if (companyInfo != null) {
                Log.d(TAG, "onCreateView: Details found Editing ");
                mCompanyName.setText(companyInfo.getCompanyName());
                mCompanyAddress.setText(companyInfo.getCompanyAddress());
                mCompanyWebLink.setText(companyInfo.getCompanyWebURL());

                mMode = FragmentEditMode.EDIT_COM;
            } else {
                mMode = FragmentEditMode.ADD_COM;
            }
        } else {
            companyInfo = null;
            Log.d(TAG, "onCreateView : no companyInfo argument Found Adding new");
            mMode = FragmentEditMode.ADD_COM;
        }

        if (arguments != null) {
            Log.d(TAG, "onCreateView: retrieving Business Type Info");
            businessTypeDto = (BusinessTypeDto) arguments.getSerializable(BusinessTypeDto.class.getSimpleName());
            if (businessTypeDto != null) {
                Log.d(TAG, "onCreateView: Details found Editing ");
                mBusinessTypeName.setText(businessTypeDto.getBusinessTypeName());

                mMode = FragmentEditMode.EDIT_TYPE;
            } else {
                mMode = FragmentEditMode.ADD_TYPE;
            }
        } else {
            businessTypeDto = null;
            Log.d(TAG, "onCreateView : no BusinessType argument Found Adding new");
            mMode = FragmentEditMode.ADD_TYPE;
        }

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update the database from here
                ContentResolver contentResolver = getActivity().getContentResolver();
                ContentValues values = new ContentValues();

                String universityName = mUniversityName.getText().toString();
                String universityAddress = mUniversityAddress.getText().toString();
                String universityWebLink = mUniversityWebLink.getText().toString();

                String companyName = mCompanyName.getText().toString();
                String companyAddress = mCompanyAddress.getText().toString();
                String companyWebLink = mCompanyWebLink.getText().toString();

                /*String businessTypeName = mBusinessTypeName.getText().toString();*/

                switch (mMode) {

                    case EDIT_UNI:
                        if (universityName.equals(universityInfo.getUniversityName())) {
                            values.put(UniversityInformation.Columns.UNIVERSITY_NAME, universityName);
                        }
                        if (universityAddress.equals(universityInfo.getUniversityAddress())) {
                            values.put(UniversityInformation.Columns.UNIVERSITY_ADDRESS, universityAddress);
                        }
                        if (universityWebLink.equals(universityInfo.getUniversityWebURL())) {
                            values.put(UniversityInformation.Columns.UNIVERSITY_URL, universityWebLink);
                        }
                        if (values.size() != 0 && universityName.equals(universityInfo.getUniversityName())) {
                            Log.d(TAG, "onClick: Updating " + universityInfo.getUniversityName());
                            values.put(UniversityInformation.Columns.UNIVERSITY_IS_APPROVED, 1);
                            values.put(UniversityInformation.Columns.MODIFIED_DATE, sdf.format(new Date()));
                            //TODO: Need to Add User Id of his Who is Approving this University !!
                            contentResolver.update(UniversityInformation.buildUniversityInformationUri(universityInfo.getId()), values, null, null);
                        }
                        Log.d(TAG, "onClick: Done Editing UniversityInformation By Date" + sdf.format(new Date()));
                        break;

                    case ADD_UNI:
                        //It not Used Here !!
                        if (mUniversityName.length() > 0) {
                            Log.d(TAG, "onClick: Add new University info in table");
                            values.put(UniversityInformation.Columns.UNIVERSITY_NAME, universityName);
                            values.put(UniversityInformation.Columns.UNIVERSITY_ADDRESS, universityAddress);
                            values.put(UniversityInformation.Columns.UNIVERSITY_URL, universityWebLink);
                            values.put(UniversityInformation.Columns.UNIVERSITY_IS_APPROVED, 0);
                            values.put(UniversityInformation.Columns.CREATE_DATE, sdf.format(new Date()));
                            values.put(UniversityInformation.Columns.MODIFIED_DATE, sdf.format(new Date()));
                            contentResolver.insert(UniversityInformation.CONTENT_URI, values);
                            Log.d(TAG, "onClick: Add  By Date" + sdf.format(new Date()));
                        }
                        break;

                    case EDIT_COM:
                        if (companyName.equals(companyInfo.getCompanyName())) {
                            values.put(CompanyInformation.Columns.COMPANY_NAME, universityName);
                        }
                        if (companyAddress.equals(companyInfo.getCompanyAddress())) {
                            values.put(CompanyInformation.Columns.COMPANY_ADDRESS, universityAddress);
                        }
                        if (companyWebLink.equals(companyInfo.getCompanyWebURL())) {
                            values.put(CompanyInformation.Columns.COMPANY_WEB_URL, universityWebLink);
                        }

                        if (values.size() != 0) {
                            Log.d(TAG, "onClick: Updating " + companyInfo.getCompanyName());
                            values.put(CompanyInformation.Columns.COMPANY_IS_APPROVED, 1);
                            values.put(CompanyInformation.Columns.MODIFIED_DATE, sdf.format(new Date()));
                            //TODO: Need to Add User Id of his Who is Approving this Company !!
                            contentResolver.update(CompanyInformation.buildCompanyInformationUri(companyInfo.getId()), values, null, null);
                        }
                        Log.d(TAG, "onClick: Done Editing CompanyInformation By Date" + sdf.format(new Date()));
                        break;

                    case ADD_COM:
                        //It not Used Here !!
                        if (mCompanyName.length() > 0) {
                            Log.d(TAG, "onClick: Add new Company info in table");
                            values.put(CompanyInformation.Columns.COMPANY_NAME, companyName);
                            values.put(CompanyInformation.Columns.COMPANY_ADDRESS, companyAddress);
                            values.put(CompanyInformation.Columns.COMPANY_WEB_URL, companyWebLink);
                            values.put(CompanyInformation.Columns.COMPANY_IS_APPROVED, 0);
                            values.put(CompanyInformation.Columns.CREATE_DATE, sdf.format(new Date()));
                            values.put(CompanyInformation.Columns.MODIFIED_DATE, sdf.format(new Date()));
                            contentResolver.insert(CompanyInformation.CONTENT_URI, values);
                            Log.d(TAG, "onClick: Add  By Date" + sdf.format(new Date()));
                        }
                        break;

                   /* case EDIT_TYPE:

                        values.put(BusinessType.Columns.BUSINESS_TYPE_NAME, businessTypeName);
                        values.put(BusinessType.Columns.MODIFIED_DATE,sdf.format(new Date()));

                        if(values.size() != 0 ){
                            //TODO: Need to Add User Id of his Who is Approving this business Type  !!
                            long id = businessTypeDto.getId();
                            String where = BusinessType.Columns._ID+" = "+id;
                            contentResolver.update(BusinessType.buildBusinessTypeUri(id),values,where,null);
                        }
                        Log.d(TAG, "onClick: Done Editing Company Type Information By Date"+sdf.format(new Date()));
                        break;

                    case ADD_TYPE:
                        if (mBusinessTypeName.length() > 0){
                            Log.d(TAG, "onClick: Add new BUSINESS_TYPE info in table");
                            values.put(BusinessType.Columns.BUSINESS_TYPE_NAME, businessTypeName);
                            //TODO: Need to Add User Id of his Who is Creating this business Type !!
                            values.put(BusinessType.Columns.CREATE_DATE,sdf.format(new Date()));
                            values.put(BusinessType.Columns.MODIFIED_DATE,sdf.format(new Date()));

                            contentResolver.insert(BusinessType.CONTENT_URI ,values);
                            Log.d(TAG, "onClick: Add  By Date"+sdf.format(new Date()));
                        }
                        break;*/
                }

                mSaveButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Update the database from here
                        ContentResolver contentResolver = getActivity().getContentResolver();
                        ContentValues values = new ContentValues();

                        String businessTypeName = mBusinessTypeName.getText().toString();

                        switch (mMode) {
                            case EDIT_TYPE:

                                values.put(BusinessType.Columns.BUSINESS_TYPE_NAME, businessTypeName);
                                values.put(BusinessType.Columns.MODIFIED_DATE, sdf.format(new Date()));

                                if (values.size() != 0) {
                                    //TODO: Need to Add User Id of his Who is Approving this business Type  !!
                                    long id = businessTypeDto.getId();
                                    String where = BusinessType.Columns._ID + " = " + id;
                                    contentResolver.update(BusinessType.buildBusinessTypeUri(id), values, where, null);
                                }
                                Log.d(TAG, "onClick 2 : Done Editing Company Type Information By Date" + sdf.format(new Date()));
                                break;

                            case ADD_TYPE:
                                if (mBusinessTypeName.length() > 0) {
                                    Log.d(TAG, "onClick: Add new BUSINESS_TYPE info in table");
                                    values.put(BusinessType.Columns.BUSINESS_TYPE_NAME, businessTypeName);
                                    //TODO: Need to Add User Id of his Who is Creating this business Type !!
                                    values.put(BusinessType.Columns.CREATE_DATE, sdf.format(new Date()));
                                    values.put(BusinessType.Columns.MODIFIED_DATE, sdf.format(new Date()));

                                    contentResolver.insert(BusinessType.CONTENT_URI, values);
                                    Log.d(TAG, "onClick 2 : Add  By Date" + sdf.format(new Date()));
                                }
                                break;
                        }
                        //get back on
                        getActivity().onBackPressed();
                    }
                });
                //get back on

                getActivity().onBackPressed();
            }
        });

        Log.d(TAG, "onCreateView: Exiting....");
        return view;
    }
}
