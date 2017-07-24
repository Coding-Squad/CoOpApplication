package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.admin;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.BusinessTypeDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.CompanyInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.UniversityInfoDto;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.BusinessType;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.CompanyInformation;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.service.UniversityInformation;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddEditApprovedActivityFragment extends Fragment {
    private static final String TAG = "AddEditUniversityActivi";

    public enum FragmentEditMode {EDIT_UNI, ADD_UNI, EDIT_COM, ADD_COM, EDIT_TYPE, ADD_TYPE}

    private FragmentEditMode mMode;

    private EditText mUniversityName;
    private EditText mUniversityAddress;
    private EditText mUniversityWebLink;

    private EditText mCompanyName;
    private EditText mCompanyAddress;
    private EditText mCompanyWebLink;

   /* private EditText mBusinessTypeName;*/

    // Camera activity request codes
//    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
//    public static final int MEDIA_TYPE_IMAGE = 1;
    // file url to store image
//    private Uri fileUri;

    private EditText mBusinessTypeName;
    private ImageView mCompanyTypeImage, mCompanyTypeImageHolder;
    //  CameraPhoto mCameraPhoto;
    GalleryPhoto mGalleryPhoto;
    //   final int CAMERA_REQUEST = 20018;
    final int GALLERY_REQUEST = 20018;
    static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 99;
    String photoPath = "";

    private Button mSaveButton;

    private Button mSaveButton2;

    UniversityInfoDto universityInfo;
    CompanyInfoDto companyInfo;
    BusinessTypeDto businessTypeDto;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public AddEditApprovedActivityFragment() {
        Log.d(TAG, "AddEditApprovedActivityFragment: Constructor called");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: Starts :" + getActivity());

        PackageManager packageManager = getActivity().getPackageManager();

        ActivityInfo info = null;
        try {
            info = packageManager.getActivityInfo(getActivity().getComponentName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("app", "Activity name:" + info.name);
//TODO Update: find a batter solution if possible
        String approved = "daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.admin.AddEditApprovedActivity";
        // String company ="daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company.AddEditCompanyTypeActivity";

        View view;

        if (info.name.equalsIgnoreCase(approved)) {
            view = inflater.inflate(R.layout.fragment_add_edit_university, container, false);
            mUniversityName = (EditText) view.findViewById(R.id.approve_uni_name);
            mUniversityAddress = (EditText) view.findViewById(R.id.approve_uni_address);
            mUniversityWebLink = (EditText) view.findViewById(R.id.approve_uni_web_link);

            mCompanyName = (EditText) view.findViewById(R.id.approve_uni_name);
            mCompanyAddress = (EditText) view.findViewById(R.id.approve_uni_address);
            mCompanyWebLink = (EditText) view.findViewById(R.id.approve_uni_web_link);
            mSaveButton = (Button) view.findViewById(R.id.approve_uni_button);

            mSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Update the database from here
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    ContentValues values = new ContentValues();

                    String universityName = mUniversityName.getText().toString();
                    String universityAddress = mUniversityAddress.getText().toString();
                    String universityWebLink = mUniversityWebLink.getText().toString();

                    String companyName = mCompanyName.getText().toString();
                    String companyAddress = mCompanyAddress.getText().toString();
                    String companyWebLink = mCompanyWebLink.getText().toString();


                    switch (mMode) {

                        case EDIT_UNI:
                            if (universityName.equals(universityInfo.getUniversityName())) {
                                values.put(UniversityInformation.Columns.UNIVERSITY_NAME, universityName);
                            }
                            if (universityAddress.equals(universityInfo.getUniversityAddress())) {
                                values.put(UniversityInformation.Columns.UNIVERSITY_ADDRESS, universityAddress);
                            }
                            if (universityWebLink.equals(universityInfo.getUniversityWebURL())) {
                                values.put(UniversityInformation.Columns.UNIVERSITY_URL, universityWebLink);
                            }
                            if (values.size() != 0 && universityName.equals(universityInfo.getUniversityName())) {
                                Log.d(TAG, "onClick: Updating " + universityInfo.getUniversityName());
                                values.put(UniversityInformation.Columns.UNIVERSITY_IS_APPROVED, 1);
                                values.put(UniversityInformation.Columns.MODIFIED_DATE, sdf.format(new Date()));
                                //TODO: Need to Add User Id of his Who is Approving this University !!
                                contentResolver.update(UniversityInformation.buildUniversityInformationUri(universityInfo.getId()), values, null, null);
                            }
                            Log.d(TAG, "onClick: Done Editing UniversityInformation By Date" + sdf.format(new Date()));
                            break;

                        case ADD_UNI:
                            //It not Used Here !!
                            if (mUniversityName.length() > 0) {
                                Log.d(TAG, "onClick: Add new University info in table");
                                values.put(UniversityInformation.Columns.UNIVERSITY_NAME, universityName);
                                values.put(UniversityInformation.Columns.UNIVERSITY_ADDRESS, universityAddress);
                                values.put(UniversityInformation.Columns.UNIVERSITY_URL, universityWebLink);
                                values.put(UniversityInformation.Columns.UNIVERSITY_IS_APPROVED, 0);
                                values.put(UniversityInformation.Columns.CREATE_DATE, sdf.format(new Date()));
                                values.put(UniversityInformation.Columns.MODIFIED_DATE, sdf.format(new Date()));
                                contentResolver.insert(UniversityInformation.CONTENT_URI, values);
                                Log.d(TAG, "onClick: Add  By Date" + sdf.format(new Date()));
                            }
                            break;

                        case EDIT_COM:
                            if (companyName.equals(companyInfo.getCompanyName())) {
                                values.put(CompanyInformation.Columns.COMPANY_NAME, universityName);
                            }
                            if (companyAddress.equals(companyInfo.getCompanyAddress())) {
                                values.put(CompanyInformation.Columns.COMPANY_ADDRESS, universityAddress);
                            }
                            if (companyWebLink.equals(companyInfo.getCompanyWebURL())) {
                                values.put(CompanyInformation.Columns.COMPANY_WEB_URL, universityWebLink);
                            }

                            if (values.size() != 0) {
                                Log.d(TAG, "onClick: Updating " + companyInfo.getCompanyName());
                                values.put(CompanyInformation.Columns.COMPANY_IS_APPROVED, 1);
                                values.put(CompanyInformation.Columns.MODIFIED_DATE, sdf.format(new Date()));
                                //TODO: Need to Add User Id of his Who is Approving this Company !!
                                contentResolver.update(CompanyInformation.buildCompanyInformationUri(companyInfo.getId()), values, null, null);
                            }
                            Log.d(TAG, "onClick: Done Editing CompanyInformation By Date" + sdf.format(new Date()));
                            break;

                        case ADD_COM:
                            //It not Used Here !!
                            if (mCompanyName.length() > 0) {
                                Log.d(TAG, "onClick: Add new Company info in table");
                                values.put(CompanyInformation.Columns.COMPANY_NAME, companyName);
                                values.put(CompanyInformation.Columns.COMPANY_ADDRESS, companyAddress);
                                values.put(CompanyInformation.Columns.COMPANY_WEB_URL, companyWebLink);
                                values.put(CompanyInformation.Columns.COMPANY_IS_APPROVED, 0);
                                values.put(CompanyInformation.Columns.CREATE_DATE, sdf.format(new Date()));
                                values.put(CompanyInformation.Columns.MODIFIED_DATE, sdf.format(new Date()));
                                contentResolver.insert(CompanyInformation.CONTENT_URI, values);
                                Log.d(TAG, "onClick: Add  By Date" + sdf.format(new Date()));
                            }
                            break;
                        default:
                            Log.d(TAG, "onClick: Nothing To worry about");
                            break;
                    }
                    getActivity().onBackPressed();
                }
            });

        } else {
            view = inflater.inflate(R.layout.fragment_add_edit_company_business_type, container, false);
            mBusinessTypeName = (EditText) view.findViewById(R.id.ctype_item_name);
//TODO upload Image
            mGalleryPhoto = new GalleryPhoto(getContext().getApplicationContext());

            mCompanyTypeImage = (ImageView) view.findViewById(R.id.category_type_Image);
            mCompanyTypeImageHolder = (ImageView) view.findViewById(R.id.category_type_Image_holder);

            mCompanyTypeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Here, thisActivity is the current activity
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                            // Show an explanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.

                        } else {

                            // No explanation needed, we can request the permission.

                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }
                    }
                    startActivityForResult(mGalleryPhoto.openGalleryIntent(), GALLERY_REQUEST);

                }
            });


            mSaveButton2 = (Button) view.findViewById(R.id.companyTypeButton_save);


            mSaveButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Update the database from here
                    ContentResolver contentResolver = getActivity().getContentResolver();
                    ContentValues values = new ContentValues();

                    String businessTypeName = mBusinessTypeName.getText().toString();

                    //get from image view
                    Bitmap bitmap = ((BitmapDrawable) mCompanyTypeImageHolder.getDrawable()).getBitmap();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                    byte[] img = bos.toByteArray();


                    switch (mMode) {
                        case EDIT_TYPE:

                            values.put(BusinessType.Columns.BUSINESS_TYPE_NAME, businessTypeName);
                            values.put(BusinessType.Columns.BUSINESS_TYPE_IMAGE, img);
                            values.put(BusinessType.Columns.MODIFIED_DATE, sdf.format(new Date()));

                            if (values.size() != 0) {
                                //TODO: Need to Add User Id of his Who is Approving this business Type  !!
                                long id = businessTypeDto.getId();
                                String where = BusinessType.Columns._ID + " = " + id;
                                contentResolver.update(BusinessType.buildBusinessTypeUri(id), values, where, null);
                            }
                            Log.d(TAG, "onClick 2 : Done Editing Company Type Information By Date" + sdf.format(new Date()));
                            break;

                        case ADD_TYPE:
                            if (mBusinessTypeName.length() > 0) {
                                Log.d(TAG, "onClick: Add new BUSINESS_TYPE info in table");
                                values.put(BusinessType.Columns.BUSINESS_TYPE_NAME, businessTypeName);
                                values.put(BusinessType.Columns.BUSINESS_TYPE_IMAGE, img);
                                //TODO: Need to Add User Id of his Who is Creating this business Type !!
                                values.put(BusinessType.Columns.CREATE_DATE, sdf.format(new Date()));
                                values.put(BusinessType.Columns.MODIFIED_DATE, sdf.format(new Date()));

                                contentResolver.insert(BusinessType.CONTENT_URI, values);
                                Log.d(TAG, "onClick 2 : Add  By Date" + sdf.format(new Date()));
                            }
                            break;
                    }
                    getActivity().onBackPressed();
                }
            });
        }

        Bundle arguments = getActivity().getIntent().getExtras();

        if (arguments != null) {
            Log.d(TAG, "onCreateView: retrieving University Info");
            universityInfo = (UniversityInfoDto) arguments.getSerializable(UniversityInfoDto.class.getSimpleName());
            if (universityInfo != null) {
                Log.d(TAG, "onCreateView: Details found Editing ");
                mUniversityName.setText(universityInfo.getUniversityName());
                mUniversityAddress.setText(universityInfo.getUniversityAddress());
                mUniversityWebLink.setText(universityInfo.getUniversityWebURL());

                mMode = FragmentEditMode.EDIT_UNI;
            } else {
                mMode = FragmentEditMode.ADD_UNI;
            }
        } else {
            universityInfo = null;
            Log.d(TAG, "onCreateView : no universityInfo argument Found Adding new");
            mMode = FragmentEditMode.ADD_UNI;
        }

        if (arguments != null) {
            Log.d(TAG, "onCreateView: retrieving Company Info");
            companyInfo = (CompanyInfoDto) arguments.getSerializable(CompanyInfoDto.class.getSimpleName());
            if (companyInfo != null) {
                Log.d(TAG, "onCreateView: Details found Editing ");
                mCompanyName.setText(companyInfo.getCompanyName());
                mCompanyAddress.setText(companyInfo.getCompanyAddress());
                mCompanyWebLink.setText(companyInfo.getCompanyWebURL());

                mMode = FragmentEditMode.EDIT_COM;
            } else {
                mMode = FragmentEditMode.ADD_COM;
            }
        } else {
            companyInfo = null;
            Log.d(TAG, "onCreateView : no companyInfo argument Found Adding new");
            mMode = FragmentEditMode.ADD_COM;
        }

        if (arguments != null) {
            Log.d(TAG, "onCreateView: retrieving Business Type Info");
            businessTypeDto = (BusinessTypeDto) arguments.getSerializable(BusinessTypeDto.class.getSimpleName());
            if (businessTypeDto != null) {
                Log.d(TAG, "onCreateView: Details found Editing " + businessTypeDto.getBusinessTypeImage());
                mBusinessTypeName.setText(businessTypeDto.getBusinessTypeName());

                byte[] byteArray = businessTypeDto.getBusinessTypeImage();
                Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                mCompanyTypeImageHolder.setImageBitmap(bm);



                mMode = FragmentEditMode.EDIT_TYPE;
            } else {
                mMode = FragmentEditMode.ADD_TYPE;
            }
        } else {
            businessTypeDto = null;
            Log.d(TAG, "onCreateView : no BusinessType argument Found Adding new");
            mMode = FragmentEditMode.ADD_TYPE;
        }

        Log.d(TAG, "onCreateView: Exiting....");
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {
                mGalleryPhoto.setPhotoUri(data.getData());
                photoPath = mGalleryPhoto.getPath();
                Log.d(TAG, "onActivityResult: >>>>" + photoPath);
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();

                    mCompanyTypeImageHolder.setImageBitmap(bitmap);

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
