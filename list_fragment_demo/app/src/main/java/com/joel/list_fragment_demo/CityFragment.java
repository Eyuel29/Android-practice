package com.joel.list_fragment_demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CityFragment extends Fragment {

    int dataIndex;
    ImageView cityPhotoView;
    TextView cityNameView, cityDescView;
    int[] cityPhotos;
    String[] cityName, cityDesc;

    public CityFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cityPhotos = new int[5];
        dataIndex = getArguments().getInt("position",0);
        getPhotos();
        cityName = getResources().getStringArray(R.array.cities);
        cityDesc = getResources().getStringArray(R.array.cityDesc);

        View view = inflater.inflate(R.layout.fragment_city_photo, container, false);

        cityPhotoView = view.findViewById(R.id.cityPhoto);
        cityNameView = view.findViewById(R.id.cityName);
        cityDescView = view.findViewById(R.id.CityDescription);

        cityPhotoView.setImageResource(cityPhotos[dataIndex]);
        cityNameView.setText(cityName[dataIndex]);
        cityDescView.setText(cityDesc[dataIndex]);

//        cityPhotoView.setImageResource(R.drawable.addisababa);
//        cityNameView.setText("Addis Ababa");
//        cityDescView.setText("Normal city as always");

        return view;
    }

    public void getPhotos(){
        cityPhotos[0] = R.drawable.addisababa;
        cityPhotos[1] = R.drawable.berlin;
        cityPhotos[2] = R.drawable.abudhabi;
        cityPhotos[3] = R.drawable.frankfurt;
        cityPhotos[4] = R.drawable.tokyo;
    }
}