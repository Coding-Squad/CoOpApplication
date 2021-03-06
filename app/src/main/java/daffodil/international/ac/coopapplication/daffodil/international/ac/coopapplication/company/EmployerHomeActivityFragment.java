package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import daffodil.international.ac.coopapplication.AppDatabaseHelper;
import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UploadFileDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UploadFiles;

import static android.app.Activity.RESULT_OK;
import static daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company.EmployerHomeActivityFragment.FragmentEditMode.ADD_EMPLOYEE_PHOTO;

/**
 * A placeholder fragment containing a simple view.
 */
public class EmployerHomeActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "EmployerHomeActivityFra";

    public static final int LOADER_ID_COMP_HOME = 221100;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public enum FragmentEditMode {EDIT_EMPLOYEE_PHOTO, ADD_EMPLOYEE_PHOTO}

    private FragmentEditMode mMode;

    private CursorRecyclerCompanyHomeViewAdapter mCompanyHomeViewAdapter;

    private AppDatabaseHelper databaseHelper;
    long userId;

    private ImageView mCompanyCoverImage;
    private ImageView mCompanyCoverImageHolder;
    private RatingBar mRattingBar;
    private TextView mHiredEmployeeAmount;
    private TextView mTotalHourByCompany;

    GalleryPhoto mOpenGalleryPhoto;
    final int GALLERY_REQUEST = 20019;
    static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 99;
    String photoPath = "";

    UploadFileDto mUploadFileDto;

    List<UploadFileDto> mUploadFileList;
    /*private ImageButton mDetailsButton;*/


    public EmployerHomeActivityFragment() {
        Log.d(TAG, "EmployerHomeActivityFragment: Constructor called");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getActivity().getIntent().getExtras();

        String emailAddress;
        if (savedInstanceState == null) {

            if (arguments == null) {
                emailAddress = null;
            } else {
                emailAddress = arguments.getString("EMAIL");
            }
        } else {
            emailAddress = (String) savedInstanceState.getSerializable("EMAIL");
        }
        Log.d(TAG, "onCreateView: emailAddress >>>>> " + emailAddress);
        databaseHelper = new AppDatabaseHelper(getContext());
        userId = databaseHelper.getUserIdByEmail(emailAddress);
        Log.d(TAG, "onCreateView: userId >>>>> " + userId);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID_COMP_HOME, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
            Log.d(TAG, "onCreateView: retrieving Photo Info ");
            mUploadFileDto = (UploadFileDto) arguments.getSerializable(UploadFileDto.class.getSimpleName());

            mUploadFileList = databaseHelper.getImageFromUploadFilesByUserId(userId, 1);//1 for coverPhoto
            Log.d(TAG, "onCreateView: size >" + mUploadFileList.size() + " userId > " + userId);
            if (mUploadFileList != null) {
                for (UploadFileDto dto : mUploadFileList) {
                    Log.d(TAG, "onCreateView: > " + dto.getId() + " > " + dto.getUploadImage() + " > " + dto.getUserId());
                    mUploadFileDto = new UploadFileDto();
                    mUploadFileDto.setId(dto.getId());
                    mUploadFileDto.setUploadImage(dto.getUploadImage());
                    mUploadFileDto.setUserId(dto.getUserId());
                    Log.d(TAG, "onCreateView: > " + dto.getId() + " " + dto.getUploadImage() + " " + dto.getUserId());
                }
            }
            if (mUploadFileDto != null) {
                Log.d(TAG, "onCreateView: Details found Editing ");

                byte[] byteArray = mUploadFileDto.getUploadImage();
                Log.d(TAG, "onCreateView: " + byteArray);
                Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                mCompanyCoverImageHolder.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mCompanyCoverImageHolder.setImageBitmap(bm);

                mMode = FragmentEditMode.EDIT_EMPLOYEE_PHOTO;
            } else {
                mMode = ADD_EMPLOYEE_PHOTO;
            }
        } else {
            mUploadFileDto = null;
            Log.d(TAG, "onCreateView : no Image argument Found Adding new");
            mMode = ADD_EMPLOYEE_PHOTO;
        }

        // TODO: list view added
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.company_type_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mCompanyHomeViewAdapter = new CursorRecyclerCompanyHomeViewAdapter(
                null,
                (CursorRecyclerCompanyHomeViewAdapter.OnCompanyHomeClickListener) getActivity());

        recyclerView.setAdapter(mCompanyHomeViewAdapter);
        Log.d(TAG, "onCreateView: Rerunning");
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
                    //  Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    //  Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(width, persent20).getBitmap();

                    Bitmap bitmap = ImageLoader.init().from(photoPath).getBitmap();
                    mCompanyCoverImageHolder.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    mCompanyCoverImageHolder.setImageBitmap(bitmap);

                    //get from image view
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    byte[] img = bos.toByteArray();

                    switch (mMode) {
                        case EDIT_EMPLOYEE_PHOTO:

                            values.put(UploadFiles.Columns.FILE_, img);
                            values.put(UploadFiles.Columns.MODIFIED_DATE, sdf.format(new Date()));
                            values.put(UploadFiles.Columns.USER_ID, userId);

                            if (values.size() != 0) {
                                Log.d(TAG, "onActivityResult: " + userId);
                                long photoId = mUploadFileDto.getId();
                                Log.d(TAG, "EDIT_EMPLOYEE_PHOTO onActivityResult: Photo Id" + photoId + " ,User id " + userId + " , img > " + img);
                                contentResolver.update(UploadFiles.buildUploadFilesUri(photoId), values, null, null);

                            }
                            //   Log.d(TAG, "onClick 2 : Done Editing Company Type Information By Date" + sdf.format(new Date()));
                            break;

                        case ADD_EMPLOYEE_PHOTO:
                            if (img != null) {
                                Log.d(TAG, "onClick: Add new Image info in table");

                                Log.d(TAG, "ADD_EMPLOYEE_PHOTO onActivityResult: " + userId);
                                values.put(UploadFiles.Columns.FILE_, img);
                                values.put(UploadFiles.Columns.CREATE_DATE, sdf.format(new Date()));
                                values.put(UploadFiles.Columns.MODIFIED_DATE, sdf.format(new Date()));
                                values.put(UploadFiles.Columns.USER_ID, userId);//user id of company
                                values.put(UploadFiles.Columns.FILE_TYPE, 1);//1 for coverPhoto
                                Log.d(TAG, "onActivityResult: values" + values);
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

        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: Starts with Id :" + id);

        String[] projection_Type = {BusinessType.Columns._ID,
                BusinessType.Columns.BUSINESS_TYPE_NAME,
                BusinessType.Columns.BUSINESS_TYPE_IMAGE
        };

        String sortOrder_type = BusinessType.Columns.BUSINESS_TYPE_NAME + " COLLATE NOCASE ";

        switch (id) {

            case LOADER_ID_COMP_HOME:
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
