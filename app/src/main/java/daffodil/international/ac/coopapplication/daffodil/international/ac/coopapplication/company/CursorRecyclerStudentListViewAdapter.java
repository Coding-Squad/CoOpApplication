package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.StudentInformationDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.StudentInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UploadFiles;

/**
 * Created by Pranto on 02-Aug-17.
 */

class CursorRecyclerStudentListViewAdapter extends RecyclerView.Adapter<CursorRecyclerStudentListViewAdapter.StudentListViewHolder> {
    private static final String TAG = "CursorRecyclerStudentLi";

    private Cursor mCursor;
    private OnStudentListClickListener mListener;

    interface OnStudentListClickListener {
        void onStudentListDetailsClicked(StudentInformationDto studentInformationDto);

        void onStudentListHireClicked(StudentInformationDto studentInformationDto);

        void onStudentListWishListClicked(StudentInformationDto studentInformationDto);
    }

    public CursorRecyclerStudentListViewAdapter(Cursor cursor, OnStudentListClickListener listener) {
        Log.d(TAG, "CursorRecyclerStudentListViewAdapter: Constrictor called");
        mCursor = cursor;
        mListener = listener;
    }

    static class StudentListViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "StudentListViewHolder";

        ImageView mStudentImage = null;

        TextView mStudentName = null;
        TextView mStudentAddress = null;
        TextView mStudentPhoneNo = null;


        ImageButton mDetailsButton = null;
        ImageButton mHireButton = null;
        ImageButton mWishListButton = null;


        StudentListViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "StudentListViewHolder: Starts");

            this.mStudentImage = (ImageView) itemView.findViewById(R.id.Image_holder);
            this.mStudentName = (TextView) itemView.findViewById(R.id.student_name);
            this.mStudentAddress = (TextView) itemView.findViewById(R.id.student_address);
            this.mStudentPhoneNo = (TextView) itemView.findViewById(R.id.student_phone_no);

            this.mDetailsButton = (ImageButton) itemView.findViewById(R.id.btn_student_details);
            this.mHireButton = (ImageButton) itemView.findViewById(R.id.btn_student_hire);
            this.mWishListButton = (ImageButton) itemView.findViewById(R.id.btn_wish_list);

        }
    }

    @Override
    public StudentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list, parent, false);
        return new StudentListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(StudentListViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Starts");
        if ((mCursor == null) || (mCursor.getCount() == 0)) {
            Log.d(TAG, "onBindViewHolder: Providing Instraction");
            holder.mStudentImage.setVisibility(View.GONE);
            holder.mStudentName.setText("No Student Registered !");
            holder.mStudentAddress.setVisibility(View.GONE);
            holder.mStudentPhoneNo.setVisibility(View.GONE);
            holder.mDetailsButton.setVisibility(View.GONE);
            holder.mHireButton.setVisibility(View.GONE);
            holder.mWishListButton.setVisibility(View.GONE);
        } else {
            Log.d(TAG, "onBindViewHolder: Getting data from database");
            if (!mCursor.moveToPosition(position)) {
                throw new IllegalStateException("Cant not Move Cursor To Position " + position);
            }
            //get
            final StudentInformationDto informationDto = new StudentInformationDto(mCursor.getLong(mCursor.getColumnIndex(StudentInformation.Columns._ID))
                    , mCursor.getString(mCursor.getColumnIndex(StudentInformation.Columns.FIRST_NAME))
                    , mCursor.getString(mCursor.getColumnIndex(StudentInformation.Columns.LAST_NAME))
                    , mCursor.getString(mCursor.getColumnIndex(StudentInformation.Columns.MOBILE_NUMBER))
                    , mCursor.getString(mCursor.getColumnIndex(StudentInformation.Columns.ADDRESS))
                    , mCursor.getBlob(mCursor.getColumnIndex(UploadFiles.Columns.FILE_))
            );

          /*  //test
            final UploadFileDto uploadFileDto= new UploadFileDto(mCursor.getLong(mCursor.getColumnIndex(UploadFiles.Columns._ID))
                    , mCursor.getBlob(mCursor.getColumnIndex(UploadFiles.Columns.FILE_))
                    , mCursor.getLong(mCursor.getColumnIndex(UploadFiles.Columns.USER_ID))
            );*/

            byte[] byteArray = informationDto.getStudentImage();
            if (byteArray != null) {
                Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                holder.mStudentImage.setImageBitmap(bm);
            }

            holder.mStudentName.setText(informationDto.getM_firstName() + " " + informationDto.getM_lastName());
            holder.mStudentAddress.setText(informationDto.getM_address());
            holder.mStudentPhoneNo.setText(informationDto.getM_mobile_number());

            holder.mStudentName.setEnabled(false);
            holder.mStudentAddress.setEnabled(false);
            holder.mStudentPhoneNo.setEnabled(false);

            holder.mStudentName.setVisibility(View.VISIBLE);
            holder.mStudentAddress.setVisibility(View.VISIBLE);
            holder.mStudentPhoneNo.setVisibility(View.VISIBLE);
            holder.mStudentImage.setVisibility(View.VISIBLE);

            holder.mDetailsButton.setVisibility(View.VISIBLE);
            holder.mHireButton.setVisibility(View.VISIBLE);
            holder.mWishListButton.setVisibility(View.VISIBLE);

            View.OnClickListener buttonClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: Starts");
                    switch (view.getId()) {

                        case R.id.btn_student_details:
                            if (mListener != null) {
                                mListener.onStudentListDetailsClicked(informationDto);
                            }
                            break;
                        case R.id.btn_student_hire:
                            if (mListener != null) {
                                mListener.onStudentListHireClicked(informationDto);
                            }
                            break;
                        case R.id.btn_wish_list:
                            if (mListener != null) {
                                mListener.onStudentListWishListClicked(informationDto);
                            }
                            break;

                        default:
                            Log.d(TAG, "onClick: Found Unexpected Button Id ");
                    }
                    Log.d(TAG, "onClick: " + view.getId() + " , " + informationDto + " Clicked .");
                }
            };

            holder.mDetailsButton.setOnClickListener(buttonClickListener);
            holder.mHireButton.setOnClickListener(buttonClickListener);
            holder.mWishListButton.setOnClickListener(buttonClickListener);


        }
    }

    @Override
    public int getItemCount() {
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


}
