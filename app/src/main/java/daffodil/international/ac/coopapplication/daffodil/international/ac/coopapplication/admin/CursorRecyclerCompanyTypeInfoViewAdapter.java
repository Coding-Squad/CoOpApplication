package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.admin;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;

/**
 * Created by Pranto on 14-Jun-17.
 */

class CursorRecyclerCompanyTypeInfoViewAdapter extends RecyclerView.Adapter<CursorRecyclerCompanyTypeInfoViewAdapter.CursorRecyclerCompanyTypeHolder> {
    private static final String TAG = "CurRecyCompanyTypeInfo";

    private Cursor mCursor;
    private OnCompanyTypeClickListner mListner;

    interface OnCompanyTypeClickListner {
        void oncComTypeApplyClicked(BusinessTypeDto businessTypeDto);

        void onCompTypeDeleteClicked(BusinessTypeDto businessTypeDto);
    }

    public CursorRecyclerCompanyTypeInfoViewAdapter(Cursor cursor, OnCompanyTypeClickListner listener) {
        Log.d(TAG, "CursorRecyclerUniversityInfoViewAdapter: Constrictor called");
        mCursor = cursor;
        mListner = listener;
    }

    @Override
    public CursorRecyclerCompanyTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.approve_items_list, parent, false);
        return new CursorRecyclerCompanyTypeHolder(view);

    }

    @Override
    public void onBindViewHolder(CursorRecyclerCompanyTypeHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Starts");
        if ((mCursor == null) || (mCursor.getCount() == 0)) {
            Log.d(TAG, "onBindViewHolder: Providing Instraction");
            holder.mUniversityName.setText("No Business Type Registered !");
            holder.mUniversityAddress.setVisibility(View.GONE);
            holder.mUniversityWebUrl.setVisibility(View.GONE);

            holder.mApproveButton.setVisibility(View.GONE);
            holder.mDeleteButton.setVisibility(View.GONE);
        } else {
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("Cant not Move Cursor To Position " + position);
            }

            final BusinessTypeDto businessType = new BusinessTypeDto(mCursor.getLong(mCursor.getColumnIndex(BusinessType.Columns._ID))
                    , mCursor.getString(mCursor.getColumnIndex(BusinessType.Columns.BUSINESS_TYPE_NAME)));


            Log.d(TAG, "onBindViewHolder: " + businessType.getId() + " , " + businessType.getBusinessTypeName());

            holder.mUniversityName.setText(businessType.getBusinessTypeName());

            holder.mApproveButton.setVisibility(View.VISIBLE);
            holder.mDeleteButton.setVisibility(View.VISIBLE);

            View.OnClickListener buttonClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: Starts");
                    switch (view.getId()) {

                        case R.id.ali_approved:
                            if (mListner != null) {
                                mListner.oncComTypeApplyClicked(businessType);
                            }
                            break;
                        case R.id.ali_delete:
                            if (mListner != null) {
                                mListner.onCompTypeDeleteClicked(businessType);
                            }
                            break;
                        default:
                            Log.d(TAG, "onClick: Found Unexpected Button Id ");
                    }
                    Log.d(TAG, "onClick: " + view.getId() + " , " + businessType.getBusinessTypeName() + " Clicked .");
                }
            };

            holder.mApproveButton.setOnClickListener(buttonClickListener);
            holder.mDeleteButton.setOnClickListener(buttonClickListener);

        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: Starts");
        if ((mCursor == null) || (mCursor.getCount() == 0)) {
            return 1;
        } else {
            return mCursor.getCount();
        }
    }

    /**
     * Swap in a new Cursor, returning the old Cursor.
     * The returned old Cursor is <em>not</em> closed.
     *
     * @param newCursor The new cursor to be used
     * @return Returns the previously set Cursor, or null if there wasn't one.
     * If the given new Cursor is the same instance as the previously set
     * Cursor, null is also returned.
     */

    Cursor swapCursor(Cursor newCursor) {
        if (newCursor == mCursor) {
            return null;
        }
        final Cursor oldCursor = mCursor;
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeRemoved(0, getItemCount());
        }
        return oldCursor;
    }


    static class CursorRecyclerCompanyTypeHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "CursorRecyclerCompanyTy";

        TextView mUniversityName = null;
        TextView mUniversityAddress = null;
        TextView mUniversityWebUrl = null;

        ImageButton mApproveButton = null;
        ImageButton mDeleteButton = null;


        public CursorRecyclerCompanyTypeHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "CursorRecyclerCompanyTypeHolder: Starts");
            this.mUniversityName = (TextView) itemView.findViewById(R.id.ali_name);
            this.mUniversityAddress = (TextView) itemView.findViewById(R.id.ali_address);
            this.mUniversityWebUrl = (TextView) itemView.findViewById(R.id.ali_web_link);
            //    this.mContractId = (TextView) itemView.findViewById(R.id.ali_contract_id);

            this.mApproveButton = (ImageButton) itemView.findViewById(R.id.ali_approved);
            this.mDeleteButton = (ImageButton) itemView.findViewById(R.id.ali_delete);
        }
    }
}
