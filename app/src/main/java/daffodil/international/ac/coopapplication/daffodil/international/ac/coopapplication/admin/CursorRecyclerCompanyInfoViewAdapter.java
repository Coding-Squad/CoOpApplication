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
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.CompanyInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation;

/**
 * Created by Pranto on 05-Jul-17.
 */

class CursorRecyclerCompanyInfoViewAdapter extends RecyclerView.Adapter<CursorRecyclerCompanyInfoViewAdapter.CompanyInfoViewHolder> {
    private static final String TAG = "CursorRecyclerCompanyIn";

    private Cursor mCursor;
    private OnCompanyInfoClickListner mListner;

    interface OnCompanyInfoClickListner {
        void onCompApplyClicked(CompanyInfoDto companyInfoDto);

        void onCompDeleteClicked(CompanyInfoDto companyInfoDto);
    }

    public CursorRecyclerCompanyInfoViewAdapter(Cursor cursor, OnCompanyInfoClickListner listener) {
        Log.d(TAG, "CursorRecyclerCompanyInfoViewAdapter: Constrictor called");
        mCursor = cursor;
        mListner = listener;
    }

    @Override
    public CompanyInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requested");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.approve_items_list, parent, false);
        return new CompanyInfoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CompanyInfoViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Starts");
        if ((mCursor == null) || (mCursor.getCount() == 0)) {
            Log.d(TAG, "onBindViewHolder: Providing Instraction");
            holder.mCompanyName.setText("No Company Registered !");
            holder.mCompanyAddress.setVisibility(View.GONE);
            holder.mCompanyWebUrl.setVisibility(View.GONE);

            holder.mApproveButton.setVisibility(View.GONE);
            holder.mDeleteButton.setVisibility(View.GONE);
        } else {
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("Cant not Move Cursor To Position " + position);
            }

            final CompanyInfoDto companyInfoDto = new CompanyInfoDto(mCursor.getLong(mCursor.getColumnIndex(CompanyInformation.Columns._ID))
                    , mCursor.getString(mCursor.getColumnIndex(CompanyInformation.Columns.COMPANY_NAME))
                    , mCursor.getString(mCursor.getColumnIndex(CompanyInformation.Columns.COMPANY_ADDRESS))
                    , mCursor.getString(mCursor.getColumnIndex(CompanyInformation.Columns.COMPANY_WEB_URL))
                    , mCursor.getInt(mCursor.getColumnIndex(CompanyInformation.Columns.CONTRACTS_ID)));

            Log.d(TAG, "onBindViewHolder: " + companyInfoDto.getCompanyAddress() + " , " + companyInfoDto.getCompanyWebURL());

            holder.mCompanyName.setText(companyInfoDto.getCompanyName());
            holder.mCompanyAddress.setText(companyInfoDto.getCompanyAddress());
            holder.mCompanyWebUrl.setText(companyInfoDto.getCompanyWebURL());

            holder.mCompanyAddress.setVisibility(View.VISIBLE);
            holder.mCompanyWebUrl.setVisibility(View.VISIBLE);
            holder.mApproveButton.setVisibility(View.VISIBLE);
            holder.mDeleteButton.setVisibility(View.VISIBLE);

            View.OnClickListener buttonClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: Starts");
                    switch (view.getId()) {

                        case R.id.ali_approved:
                            if (mListner != null) {
                                mListner.onCompApplyClicked(companyInfoDto);
                            }
                            break;
                        case R.id.ali_delete:
                            if (mListner != null) {
                                mListner.onCompDeleteClicked(companyInfoDto);
                            }
                            break;
                        default:
                            Log.d(TAG, "onClick: Found Unexpected Button Id ");
                    }
                    Log.d(TAG, "onClick: " + view.getId() + " , " + companyInfoDto.getCompanyName() + " Clicked .");
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


    static class CompanyInfoViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "CompanyInfoViewHolder";

        TextView mCompanyName = null;
        TextView mCompanyAddress = null;
        TextView mCompanyWebUrl = null;

        ImageButton mApproveButton = null;
        ImageButton mDeleteButton = null;


        public CompanyInfoViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "CompanyInfoViewHolder: Starts");
            this.mCompanyName = (TextView) itemView.findViewById(R.id.ali_name);
            this.mCompanyAddress = (TextView) itemView.findViewById(R.id.ali_address);
            this.mCompanyWebUrl = (TextView) itemView.findViewById(R.id.ali_web_link);
            //    this.mContractId = (TextView) itemView.findViewById(R.id.ali_contract_id);

            this.mApproveButton = (ImageButton) itemView.findViewById(R.id.ali_approved);
            this.mDeleteButton = (ImageButton) itemView.findViewById(R.id.ali_delete);
        }
    }
}
