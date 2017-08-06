package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.admin;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;


public class AddEditCompanyBusinessTypeFragment extends Fragment {
    private static final String TAG = "AddEditCompanyBusinessT";

    //TODO : Use less
    public enum FragmentEditMode2 {EDIT_TYPE, ADD_TYPE}

    private FragmentEditMode2 mMode;

    private EditText mBusinessTypeName;

    private Button mSaveButton;

    BusinessTypeDto businessTypeDto;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public AddEditCompanyBusinessTypeFragment() {
        Log.d(TAG, " Test AddEditCompanyBusinessTypeFragment: Constrictor called");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starts >>>>>>>>>>>>>>>");

        View view = inflater.inflate(R.layout.fragment_add_edit_company_business_type, container, false);

        mBusinessTypeName = (EditText) view.findViewById(R.id.company_type_name);
        mSaveButton = (Button) view.findViewById(R.id.companyTypeButton_save);

        Bundle arguments = getActivity().getIntent().getExtras();

        if (arguments != null) {
            Log.d(TAG, "onCreateView: retrieving Business Type Info");
            businessTypeDto = (BusinessTypeDto) arguments.getSerializable(BusinessTypeDto.class.getSimpleName());
            if (businessTypeDto != null) {
                Log.d(TAG, "onCreateView: Details found Editing ");
                mBusinessTypeName.setText(businessTypeDto.getBusinessTypeName());

                mMode = FragmentEditMode2.EDIT_TYPE;
            } else {
                mMode = FragmentEditMode2.ADD_TYPE;
            }
        } else {
            businessTypeDto = null;
            Log.d(TAG, "onCreateView : no BusinessType argument Found Adding new");
            mMode = FragmentEditMode2.ADD_TYPE;
        }

        mSaveButton.setOnClickListener(new View.OnClickListener() {
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
                        Log.d(TAG, "onClick: Done Editing Company Type Information By Date" + sdf.format(new Date()));
                        break;

                    case ADD_TYPE:
                        if (mBusinessTypeName.length() > 0) {
                            Log.d(TAG, "onClick: Add new BUSINESS_TYPE info in table");
                            values.put(BusinessType.Columns.BUSINESS_TYPE_NAME, businessTypeName);
                            //TODO: Need to Add User Id of his Who is Creating this business Type !!
                            values.put(BusinessType.Columns.CREATE_DATE, sdf.format(new Date()));
                            values.put(BusinessType.Columns.MODIFIED_DATE, sdf.format(new Date()));

                            contentResolver.insert(BusinessType.CONTENT_URI, values);
                            Log.d(TAG, "onClick: Add  By Date" + sdf.format(new Date()));
                        }
                        break;
                }
                //get back on
                getActivity().onBackPressed();
            }
        });

        Log.d(TAG, "onCreateView: Exiting....");
        return view;
    }
}
