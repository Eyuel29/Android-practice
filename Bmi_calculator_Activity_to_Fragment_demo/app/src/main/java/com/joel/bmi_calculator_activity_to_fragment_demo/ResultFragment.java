package com.joel.bmi_calculator_activity_to_fragment_demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultFragment extends Fragment {

    public ResultFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Double calculateBmi(Double height, Double weight){
        Double meterSquare = (height/100) * (height/100);
        return weight / meterSquare;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView viewBmiNumber = view.findViewById(R.id.userBmiNumberView);
        TextView viewBmiRange = view.findViewById(R.id.userBmiRangeView);

        Double height = getArguments().getDouble("height");
        Double weight = getArguments().getDouble("weight");
        Double result = calculateBmi(height,weight);
        String range = getBmiRange(result);

        viewBmiNumber.setText(result.toString());
        viewBmiRange.setText(range);

        return view;
    }

    private String getBmiRange(Double result) {
        if (result < 18.5){
            return "UNDERWEIGHT";
        }else if(result > 18.5 && result < 24.5){
            return "NORMAL";
        }else if(result > 24.5 && result < 30){
            return "OVERWEIGHT";
        }else if(result > 30 && result < 34){
            return "OBESE";
        }else{
            return "EXTREME OBESE";
        }
    }
}