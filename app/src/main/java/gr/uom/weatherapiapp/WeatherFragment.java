package gr.uom.weatherapiapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WeatherFragment extends Fragment {

    private static final String TAG = "WeatherFragment";
    private String weatherString = "Current Weather";
    TextView textWeather;
    public WeatherFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        if (getArguments() != null) weatherString = getArguments().getString("location");
        else weatherString = "";
        textWeather = rootView.findViewById(R.id.txtWeather);
        textWeather.setText(weatherString);
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        System.out.println(weatherString);
        FetchWeatherTask weatherTask = new FetchWeatherTask(weatherString);
        Thread t = new Thread(weatherTask);
        try {
            t.start();
            t.join();
            textWeather.setText(weatherTask.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setWeatherString(String weatherString) {
        TextView textWeather = getActivity().findViewById(R.id.txtWeather);
        textWeather.setText(weatherString);
    }

}