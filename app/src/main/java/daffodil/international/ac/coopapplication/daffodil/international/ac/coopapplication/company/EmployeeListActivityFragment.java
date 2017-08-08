package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.InvalidParameterException;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.StudentInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UploadFiles;

/**
 * A placeholder fragment containing a simple view.
 */
public class EmployeeListActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "EmployeeListActivityFra";

    public static final int LOADER_ID_STUDENT_LIST = 22100;

    private TextView mCompanyTypeName;
    BusinessTypeDto mBusinessTypeDto;
    //  UploadFileDto mUploadFileDto;


    private CursorRecyclerStudentListViewAdapter mCursorRecyclerStudentListViewAdapter;


    public EmployeeListActivityFragment() {
        Log.d(TAG, "EmployeeListActivityFragment: Constrictor called");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: Starts");
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID_STUDENT_LIST, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starts");
        View view = inflater.inflate(R.layout.fragment_employee_list, container, false);

        mCompanyTypeName = (TextView) view.findViewById(R.id.company_type_name);

        Bundle arguments = getActivity().getIntent().getExtras();

        if (arguments != null) {
            Log.d(TAG, "onCreateView: retrieving Photo Info");
            mBusinessTypeDto = (BusinessTypeDto) arguments.getSerializable(BusinessTypeDto.class.getSimpleName());
            if (mBusinessTypeDto != null) {
                Log.d(TAG, "onCreateView: Details found Editing " + mBusinessTypeDto.getBusinessTypeName());

                mCompanyTypeName.setText(mBusinessTypeDto.getBusinessTypeName());

            } else {
                Log.d(TAG, "onCreateView: mBusinessTypeDto is null. ");
            }
        } else {
            Log.d(TAG, "onCreateView : no argument Found .");
        }


        // TODO: list view added
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.student_details_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mCursorRecyclerStudentListViewAdapter = new CursorRecyclerStudentListViewAdapter(
                null,
                (CursorRecyclerStudentListViewAdapter.OnStudentListClickListener) getActivity()
        );
        recyclerView.setAdapter(mCursorRecyclerStudentListViewAdapter);

        Log.d(TAG, "onCreateView: Rerunning");
        return view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: Starts with Id :" + id);

        String[] projection_Type = {"a." + StudentInformation.Columns._ID,
                "a." + StudentInformation.Columns.FIRST_NAME,
                "a." + StudentInformation.Columns.LAST_NAME,
                "a." + StudentInformation.Columns.ADDRESS,
                "a." + StudentInformation.Columns.MOBILE_NUMBER,
                "b." + UploadFiles.Columns.FILE_
        };
        //TODO : where Statement if Photo not upload stop show student .
        //  String selection = "b." + UploadFiles.Columns.FILE_TYPE + " = 2"; //id = 2 for profile picture

        String sortOrder_type = "a." + StudentInformation.Columns._ID + " COLLATE NOCASE ";



        switch (id) {

            case LOADER_ID_STUDENT_LIST:
                return new CursorLoader(getActivity(),
                        StudentInformation.CONTENT_URI_STUDENT_INFO_PHOTO,
                        projection_Type,
                        null, //selection
                        null,
                        sortOrder_type);

            default:
                throw new InvalidParameterException(TAG + ".onCreateLoader called with invalid loader id - " + id);
        }

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished: Starts");
        mCursorRecyclerStudentListViewAdapter.swapCursor(data);
        int countStudentAdapter = mCursorRecyclerStudentListViewAdapter.getItemCount();
        Log.d(TAG, "onLoadFinished: countCompanyTypeAdapter is :++++++++++++++++++++ " + countStudentAdapter);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset: Starts");
        mCursorRecyclerStudentListViewAdapter.swapCursor(null);

    }
}
