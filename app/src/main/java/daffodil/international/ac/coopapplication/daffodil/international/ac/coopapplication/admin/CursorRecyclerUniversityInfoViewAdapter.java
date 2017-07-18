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
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UniversityInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;

/**
 * Created by Pranto on 14-Jun-17.
 */

class CursorRecyclerUniversityInfoViewAdapter extends RecyclerView.Adapter<CursorRecyclerUniversityInfoViewAdapter.UniversityInfoViewHolder> {
    private static final String TAG = "CursorRecyclerUniversit";
    private Cursor mCursor;
    private OnUniversityInfoClickListner mListner;

    interface OnUniversityInfoClickListner {
        void onUniApplyClicked(UniversityInfoDto universityInfoDto);

        void onUniDeleteClicked(UniversityInfoDto universityInfoDto);
    }

    public CursorRecyclerUniversityInfoViewAdapter(Cursor cursor, OnUniversityInfoClickListner listner) {
        Log.d(TAG, "CursorRecyclerUniversityInfoViewAdapter: Constrictor called");
        mCursor = cursor;
        mListner = listner;
    }

    @Override
    public UniversityInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.approve_items_list, parent, false);
        return new UniversityInfoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(UniversityInfoViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Starts");
        if ((mCursor == null) || (mCursor.getCount() == 0)) {
            Log.d(TAG, "onBindViewHolder: Providing Instraction");
            holder.mUniversityName.setText("No University Registered !");
            holder.mUniversityAddress.setVisibility(View.GONE);
            holder.mUniversityWebUrl.setVisibility(View.GONE);

            holder.mApproveButton.setVisibility(View.GONE);
            holder.mDeleteButton.setVisibility(View.GONE);
        } else {
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("Cant not Move Cursor To Position " + position);
            }

            final UniversityInfoDto universityInfo = new UniversityInfoDto(mCursor.getLong(mCursor.getColumnIndex(UniversityInformation.Columns._ID))
                    , mCursor.getString(mCursor.getColumnIndex(UniversityInformation.Columns.UNIVERSITY_NAME))
                    , mCursor.getString(mCursor.getColumnIndex(UniversityInformation.Columns.UNIVERSITY_ADDRESS))
                    , mCursor.getString(mCursor.getColumnIndex(UniversityInformation.Columns.UNIVERSITY_URL))
                    , mCursor.getInt(mCursor.getColumnIndex(UniversityInformation.Columns.CONTRACTS_ID)));

            Log.d(TAG, "onBindViewHolder: " + universityInfo.getUniversityAddress() + " , " + universityInfo.getUniversityWebURL());

            holder.mUniversityName.setText(universityInfo.getUniversityName());
            holder.mUniversityAddress.setText(universityInfo.getUniversityAddress());
            holder.mUniversityWebUrl.setText(universityInfo.getUniversityWebURL());
            holder.mUniversityAddress.setVisibility(View.VISIBLE);
            holder.mUniversityWebUrl.setVisibility(View.VISIBLE);
            holder.mApproveButton.setVisibility(View.VISIBLE);
            holder.mDeleteButton.setVisibility(View.VISIBLE);

            View.OnClickListener buttonClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: Starts");
                    switch (view.getId()) {

                        case R.id.ali_approved:
                            if (mListner != null) {
                                mListner.onUniApplyClicked(universityInfo);
                            }
                            break;
                        case R.id.ali_delete:
                            if (mListner != null) {
                                mListner.onUniDeleteClicked(universityInfo);
                            }
                            break;
                        default:
                            Log.d(TAG, "onClick: Found Unexpected Button Id ");
                    }
                    Log.d(TAG, "onClick: " + view.getId() + " , " + universityInfo.getUniversityName() + " Clicked .");
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


    static class UniversityInfoViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "UniversityInfoViewAdapt";

        TextView mUniversityName = null;
        TextView mUniversityAddress = null;
        TextView mUniversityWebUrl = null;

        ImageButton mApproveButton = null;
        ImageButton mDeleteButton = null;


        public UniversityInfoViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "UniversityInfoVievHolder: Starts");
            this.mUniversityName = (TextView) itemView.findViewById(R.id.ali_name);
            this.mUniversityAddress = (TextView) itemView.findViewById(R.id.ali_address);
            this.mUniversityWebUrl = (TextView) itemView.findViewById(R.id.ali_web_link);
            //    this.mContractId = (TextView) itemView.findViewById(R.id.ali_contract_id);

            this.mApproveButton = (ImageButton) itemView.findViewById(R.id.ali_approved);
            this.mDeleteButton = (ImageButton) itemView.findViewById(R.id.ali_delete);
        }
    }
}
