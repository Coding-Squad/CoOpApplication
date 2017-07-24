package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UploadFileDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UploadFiles;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class EmployerHomeActivityFragment extends Fragment {
    private static final String TAG = "EmployerHomeActivityFra";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public enum FragmentEditMode {EDIT_EMPLOYEE, ADD_EMPLOYEE}

    private FragmentEditMode mMode;

    private ImageView mCompanyCoverImage, mCompanyCoverImageHolder;
    private RatingBar mRattingBar;
    private TextView mHiredEmployeeAmount;
    private TextView mTotalHourByCompany;

    GalleryPhoto mOpenGalleryPhoto;
    final int GALLERY_REQUEST = 20019;
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


        mOpenGalleryPhoto = new GalleryPhoto(getContext().getApplicationContext());

        mCompanyCoverImage = (ImageView) view.findViewById(R.id.btn_ImageView);
        mCompanyCoverImageHolder = (ImageView) view.findViewById(R.id.company_cover_photo_holder);

        mRattingBar = (RatingBar) view.findViewById(R.id.company_ratting);
        mHiredEmployeeAmount = (TextView) view.findViewById(R.id.hired_employee_amount);
        mTotalHourByCompany = (TextView) view.findViewById(R.id.total_hour_by_company);


        //set text
        mHiredEmployeeAmount.setText("Employee Hired 150 taka");
        mTotalHourByCompany.setText("Total Service 500 hour");

        mCompanyCoverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Uploading....");
                // Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                }

                startActivityForResult(mOpenGalleryPhoto.openGalleryIntent(), GALLERY_REQUEST);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Update uploadFile to the database from here

        ContentResolver contentResolver = getActivity().getContentResolver();
        ContentValues values = new ContentValues();

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {
                mOpenGalleryPhoto.setPhotoUri(data.getData());
                photoPath = mOpenGalleryPhoto.getPath();
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();

                    mCompanyCoverImageHolder.setImageBitmap(bitmap);

                    //get from image view
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                    byte[] img = bos.toByteArray();
                    Log.d(TAG, "onClick: img" + img);

                    switch (mMode) {
                        case EDIT_EMPLOYEE:

                            values.put(UploadFiles.Columns.FILE_, img);
                            values.put(BusinessType.Columns.MODIFIED_DATE, sdf.format(new Date()));

                            if (values.size() != 0) {
                                //TODO: Need to Add User Id of his Who is Approving this business Type  !!
                                long id = mUploadFileDto.getId();
                                String where = UploadFiles.Columns._ID + " = " + id;
                                contentResolver.update(UploadFiles.buildUploadFilesUri(id), values, where, null);
                            }
                            Log.d(TAG, "onClick 2 : Done Editing Company Type Information By Date" + sdf.format(new Date()));
                            break;

                        case ADD_EMPLOYEE:
                            if (img != null) {
                                Log.d(TAG, "onClick: Add new Image info in table");
                                values.put(UploadFiles.Columns.FILE_, img);
                                //TODO: Need to Add User Id of his Who is Creating this business Type !!
                                values.put(BusinessType.Columns.CREATE_DATE, sdf.format(new Date()));
                                values.put(BusinessType.Columns.MODIFIED_DATE, sdf.format(new Date()));

                                contentResolver.insert(UploadFiles.CONTENT_URI, values);
                                Log.d(TAG, "onClick 2 : Add  By Date" + sdf.format(new Date()));
                            }
                            break;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Something Missing on Choosing Photo !", Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "onActivityResult: photoPath " + photoPath);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: 111111111111");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Log.d(TAG, "onRequestPermissionsResult: 222222222222");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
                //     return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
