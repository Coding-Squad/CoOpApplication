package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;

/**
 * Created by Pranto on 05-Jul-17.
 */

class CursorRecyclerCompanyHomeViewAdapter extends RecyclerView.Adapter<CursorRecyclerCompanyHomeViewAdapter.CompanyHomeViewHolder> {
    private static final String TAG = "CursorRecyclerCompanyIn";

    private Cursor mCursor;
    private OnCompanyHomeClickListener mListener;

    interface OnCompanyHomeClickListener {
        void onCompDetailsClicked(BusinessTypeDto businessTypeDto);
    }

    public CursorRecyclerCompanyHomeViewAdapter(Cursor cursor, OnCompanyHomeClickListener listener) {
        Log.d(TAG, "CursorRecyclerCompanyHomeViewAdapter: Constrictor called");
        mCursor = cursor;
        mListener = listener;
    }

    @Override
    public CompanyHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_type_list, parent, false);
        return new CompanyHomeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CompanyHomeViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Starts");
        if ((mCursor == null) || (mCursor.getCount() == 0)) {
            Log.d(TAG, "onBindViewHolder: Providing Instraction");

            holder.mCompanyTypeImage.setVisibility(View.GONE);
            holder.mCompanyTypeName.setText("No Type Registered !");
            holder.mTotalEmployee.setVisibility(View.GONE);
            holder.mDetailsButton.setVisibility(View.GONE);

        } else {
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("Cant not Move Cursor To Position " + position);
            }
            //get
            final BusinessTypeDto businessTypeDto = new BusinessTypeDto(mCursor.getLong(mCursor.getColumnIndex(BusinessType.Columns._ID))
                    , mCursor.getString(mCursor.getColumnIndex(BusinessType.Columns.BUSINESS_TYPE_NAME))
                    , mCursor.getBlob(mCursor.getColumnIndex(BusinessType.Columns.BUSINESS_TYPE_IMAGE))
            );

          /*  byte[] byteArray = businessTypeDto.getBusinessTypeImage();
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            holder.mCompanyTypeImage.setImageBitmap(bm);*/

            holder.mCompanyTypeName.setText(businessTypeDto.getBusinessTypeName());
            holder.mTotalEmployee.setText("Total employee : 11");

            holder.mCompanyTypeImage.setVisibility(View.VISIBLE);
            holder.mTotalEmployee.setVisibility(View.VISIBLE);
            holder.mDetailsButton.setVisibility(View.VISIBLE);

            View.OnClickListener buttonClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: Starts");
                    switch (view.getId()) {

                        case R.id.btn_details:
                            if (mListener != null) {
                                mListener.onCompDetailsClicked(businessTypeDto);
                            }
                            break;

                        default:
                            Log.d(TAG, "onClick: Found Unexpected Button Id ");
                    }
                    Log.d(TAG, "onClick: " + view.getId() + " , " + businessTypeDto.getBusinessTypeName() + " Clicked .");
                }
            };

            holder.mDetailsButton.setOnClickListener(buttonClickListener);


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


    static class CompanyHomeViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "CompanyInfoViewHolder";

        ImageView mCompanyTypeImage = null;
        TextView mCompanyTypeName = null;
        TextView mTotalEmployee = null;

        ImageButton mDetailsButton = null;


        CompanyHomeViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "CompanyInfoViewHolder: Starts");
            this.mCompanyTypeImage = (ImageView) itemView.findViewById(R.id.category_type_Image);
            this.mCompanyTypeName = (TextView) itemView.findViewById(R.id.ctype_item_name);
            this.mTotalEmployee = (TextView) itemView.findViewById(R.id.ctype_total_employee);
            this.mDetailsButton = (ImageButton) itemView.findViewById(R.id.btn_details);

        }
    }
}
