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

import java.security.InvalidParameterException;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;


public class CompanyHomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "CompanyHomeFragment";

    public static final int LOADER_ID_COMPANY_HOME = 11000;

    private CursorRecyclerCompanyHomeViewAdapter mCompanyHomeViewAdapter;

    public CompanyHomeFragment() {
        Log.d(TAG, "CompanyHomeFragment: Constrictor called ");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(LOADER_ID_COMPANY_HOME, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starts fragment_company__home_");
        View view = inflater.inflate(R.layout.fragment_company__home_, container, false);

        // TODO: list view added
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.company_type_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mCompanyHomeViewAdapter = new CursorRecyclerCompanyHomeViewAdapter(null, (CursorRecyclerCompanyHomeViewAdapter.OnCompanyHomeClickListener) getActivity());
        recyclerView.setAdapter(mCompanyHomeViewAdapter);
        Log.d(TAG, "onCreateView: Rerunning");
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: Starts with Id :" + id);

        String[] projection_Type = {BusinessType.Columns._ID,
                BusinessType.Columns.BUSINESS_TYPE_NAME,
                //  BusinessType.Columns.BUSINESS_TYPE_IMAGE
        };

        String sortOrder_type = BusinessType.Columns.BUSINESS_TYPE_NAME + " COLLATE NOCASE ";

        switch (id) {

            case LOADER_ID_COMPANY_HOME:
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
        mCompanyHomeViewAdapter.swapCursor(data);
        int countCompanyTypeAdapter = mCompanyHomeViewAdapter.getItemCount();
        Log.d(TAG, "onLoadFinished: countCompanyTypeAdapter is :++++++++++++++++++++ " + countCompanyTypeAdapter);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset: Starts");
        mCompanyHomeViewAdapter.swapCursor(null);
    }

}
