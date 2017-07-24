package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kosalgeek.android.photoutil.GalleryPhoto;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UploadFileDto;

/**
 * A placeholder fragment containing a simple view.
 */
public class EmployerHomeActivityFragment extends Fragment {
    private static final String TAG = "EmployerHomeActivityFra";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public enum FragmentEditMode {EDIT_EMPLOYEE, ADD_EMPLOYEE}

    private FragmentEditMode mMode;

    private ImageView mCompanyCoverImage, mCompanyCoverImageHolder;
    GalleryPhoto mGalleryPhoto;
    final int GALLERY_REQUEST = 20018;
    static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 99;
    String photoPath = "";

    UploadFileDto mUploadFileDto;


    public EmployerHomeActivityFragment() {
        Log.d(TAG, "EmployerHomeActivityFragment: Constructor called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

/* if their is more then 2 fragment then it might usefull
  PackageManager packageManager = getActivity().getPackageManager();

        ActivityInfo info = null;
        try {
            info = packageManager.getActivityInfo(getActivity().getComponentName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onCreateView: Activity name:" + info.name);

        String employerHome = "daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company.EmployerHomeActivity";*/


        View view = inflater.inflate(R.layout.fragment_employer_home, container, false);


        mGalleryPhoto = new GalleryPhoto(getContext().getApplicationContext());

        mCompanyCoverImage = (ImageView) view.findViewById(R.id.btn_ImageView);
        mCompanyCoverImageHolder = (ImageView) view.findViewById(R.id.company_cover_photo_holder);

        mCompanyCoverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(mGalleryPhoto.openGalleryIntent(), GALLERY_REQUEST);

            }
        });

        Bundle arguments = getActivity().getIntent().getExtras();

        if (arguments != null) {
            Log.d(TAG, "onCreateView: retrieving Photo Info");
            mUploadFileDto = (UploadFileDto) arguments.getSerializable(UploadFileDto.class.getSimpleName());
            if (mUploadFileDto != null) {
                Log.d(TAG, "onCreateView: Details found Editing ");

                byte[] byteArray = mUploadFileDto.getUploadImage();
                Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                mCompanyCoverImageHolder.setImageBitmap(bm);

                mMode = FragmentEditMode.EDIT_EMPLOYEE;
            } else {
                mMode = FragmentEditMode.ADD_EMPLOYEE;
            }
        } else {
            mUploadFileDto = null;
            Log.d(TAG, "onCreateView : no Image argument Found Adding new");
            mMode = FragmentEditMode.ADD_EMPLOYEE;
        }


        return view;
    }


}
