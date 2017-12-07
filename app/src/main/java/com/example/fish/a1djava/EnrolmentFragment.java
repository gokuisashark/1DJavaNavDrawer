package com.example.fish.a1djava;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnrolmentFragment extends Fragment {


    public EnrolmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enrolment, container, false);


        //1st group: EnrolPillar TextView and EnrolPillar Spinner, try not to split
        TextView enrolPillar = (TextView) view.findViewById(R.id.enrolPillar);
        Spinner enrolSpinner = (Spinner) view.findViewById(R.id.pillarSpinner);
        ArrayAdapter<CharSequence> enrolSpinnerAdapter = ArrayAdapter.createFromResource(
                getActivity().getApplicationContext(), R.array.pillars_array,
                android.R.layout.simple_spinner_item);
        enrolSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enrolSpinner.setAdapter(enrolSpinnerAdapter);

        //2nd group: Course1 TextView and Course1 MultiAutoCompleteTextView, don't split
        ArrayAdapter<String> chooseCourse1Adapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                COUNTRIES);
        MultiAutoCompleteTextView chooseCourse1multiview = view.findViewById(R.id.choosecourse1multiview);
        chooseCourse1multiview.setAdapter(chooseCourse1Adapter);
        chooseCourse1multiview.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        return view;
    }

    public static final String[] COUNTRIES = new String[]{
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

}
