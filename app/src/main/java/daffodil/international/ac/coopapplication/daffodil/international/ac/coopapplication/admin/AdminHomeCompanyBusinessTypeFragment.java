package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.admin;

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

import java.security.InvalidParameterException;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;


public class AdminHomeCompanyBusinessTypeFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "AdminHomeCompanyFragmen";

    public static final int LOADER_ID_UNIVERSITY = 1;
    public static final int LOADER_ID_COMPANY = 2;
    public static final int LOADER_ID_STUDENT = 3;
    public static final int LOADER_ID_COMPANY_BUSI_TYPE = 4;

    private CursorRecyclerCompanyTypeInfoViewAdapter mCursorCompanyBusiTypeAdapter;


    public AdminHomeCompanyBusinessTypeFragment() {
        Log.d(TAG, "AdminHomeCompanyFragment: Constructor Called");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: Starts");
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID_COMPANY_BUSI_TYPE, null, this);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: Starts");
        View view = inflater.inflate(R.layout.fragment_admin_home_university, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.university_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mCursorCompanyBusiTypeAdapter = new CursorRecyclerCompanyTypeInfoViewAdapter(
                null,
                (CursorRecyclerCompanyTypeInfoViewAdapter.OnCompanyTypeClickListner) getActivity());

        recyclerView.setAdapter(mCursorCompanyBusiTypeAdapter);

        Log.d(TAG, "onCreateView: Rerunning");
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: Starts with Id : ");

        String[] projection_university = {UniversityInformation.Columns._ID,
                UniversityInformation.Columns.UNIVERSITY_NAME,
                UniversityInformation.Columns.UNIVERSITY_ADDRESS,
                UniversityInformation.Columns.UNIVERSITY_URL,
                UniversityInformation.Columns.CONTRACTS_ID
        };
        String sortOrder_university = UniversityInformation.Columns.UNIVERSITY_IS_APPROVED +
                "," + UniversityInformation.Columns.UNIVERSITY_NAME + " COLLATE NOCASE ";

        String[] projection_company = {CompanyInformation.Columns._ID,
                CompanyInformation.Columns.COMPANY_NAME,
                CompanyInformation.Columns.COMPANY_ADDRESS,
                CompanyInformation.Columns.COMPANY_WEB_URL,
                CompanyInformation.Columns.CONTRACTS_ID
        };
        String sortOrder_company = CompanyInformation.Columns.COMPANY_IS_APPROVED +
                "," + CompanyInformation.Columns.COMPANY_NAME + " COLLATE NOCASE ";

        String[] projection_Type = {BusinessType.Columns._ID,
                BusinessType.Columns.BUSINESS_TYPE_NAME,
                BusinessType.Columns.BUSINESS_TYPE_IMAGE
        };
        String sortOrder_type = BusinessType.Columns.BUSINESS_TYPE_NAME + " COLLATE NOCASE ";


        switch (id) {
            case LOADER_ID_UNIVERSITY:
                return new CursorLoader(getActivity(),
                        UniversityInformation.CONTENT_URI,
                        projection_university,
                        null,
                        null,
                        sortOrder_university);

            case LOADER_ID_COMPANY:
                return new CursorLoader(getActivity(),
                        CompanyInformation.CONTENT_URI,
                        projection_company,
                        null,
                        null,
                        sortOrder_company);

            case LOADER_ID_STUDENT:
                return null;

            case LOADER_ID_COMPANY_BUSI_TYPE:
                return new CursorLoader(getActivity(),
                        BusinessType.CONTENT_URI,
                        projection_Type,
                        null,
                        null,
                        sortOrder_type);

            default:
                throw new InvalidParameterException(TAG + ".onCreateLoader called with invalid loader id - " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished: Starts");

        mCursorCompanyBusiTypeAdapter.swapCursor(data);
        int countCompanyAdapter = mCursorCompanyBusiTypeAdapter.getItemCount();

        Log.d(TAG, "onLoadFinished: countCompanyAdapter    is :++++++++++++++++++++ " + countCompanyAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset: Starts");
        mCursorCompanyBusiTypeAdapter.swapCursor(null);
    }
}
