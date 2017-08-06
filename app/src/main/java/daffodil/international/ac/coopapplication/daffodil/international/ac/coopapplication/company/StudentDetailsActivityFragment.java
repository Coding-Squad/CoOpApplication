package daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.company;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import daffodil.international.ac.coopapplication.R;
import daffodil.international.ac.coopapplication.daffodil.international.ac.coopapplication.dto.StudentInformationDto;

/**
 * A placeholder fragment containing a simple view.
 */
public class StudentDetailsActivityFragment extends Fragment {
    private static final String TAG = "StudentDetailsActivityF";

    private TextView mStudentName;
    private TextView mTotalService;

    StudentInformationDto mStudentInformationDto;

    public StudentDetailsActivityFragment() {
        Log.d(TAG, "StudentDetailsActivityFragment: Constrictor called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Starts");

        View view = inflater.inflate(R.layout.fragment_student_details, container, false);

        mStudentName = (TextView) view.findViewById(R.id.details_stuudent_name);
        mTotalService = (TextView) view.findViewById(R.id.details_total_hour_service);

        Bundle arguments = getActivity().getIntent().getExtras();

        if (arguments != null) {
            Log.d(TAG, "onCreateView: retrieving Photo Info");
            mStudentInformationDto = (StudentInformationDto) arguments.getSerializable(StudentInformationDto.class.getSimpleName());
            if (mStudentInformationDto != null) {
                Log.d(TAG, "onCreateView: Details found Editing " + mStudentInformationDto.getM_id());
                String studentName = mStudentInformationDto.getM_firstName() + " " + mStudentInformationDto.getM_lastName();

                mStudentName.setText(studentName);

            } else {
                Log.d(TAG, "onCreateView: mBusinessTypeDto is null. ");
            }
        } else {
            Log.d(TAG, "onCreateView : no argument Found .");
        }


        return view;
    }


}
