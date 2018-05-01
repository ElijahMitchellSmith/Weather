package com.example.elijahsmith.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elijahsmith.weather.api_calls.dark_sky.Weather;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherFragment extends Fragment {
    private Weather weather;
    private String place;
    @BindView(R.id.layout_background)
    protected ConstraintLayout mLayout;
    @BindView(R.id.location_textview)
    protected TextView locationText;
    @BindView(R.id.summary_textview)
    protected TextView summaryText;
    @BindView(R.id.temperature_textview)
    protected TextView temperatureText;
    @BindView(R.id.high_text)
    protected TextView highTemp;
    @BindView(R.id.low_text)
    protected TextView lowTemp;
    @BindView(R.id.precip_textview)
    protected TextView precipText;
    @BindView(R.id.weather_icon)
    protected ImageView weatherIcon;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static WeatherFragment newInstance() {

        Bundle args = new Bundle();

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        locationText.setText(place);
        temperatureText.setText(getString(R.string.temp_format, (int)Math.ceil(weather.getCurrentProperties().getTemperature())));
        summaryText.setText(weather.getCurrentProperties().getSummary());
        highTemp.setText(getString(R.string.temp_format, (int)Math.ceil(weather
                .getDailyProperties()
                .getDailyDataList().get(0)
                .getTemperatureHigh())));
        lowTemp.setText(getString(R.string.low_temp_format, (int)Math.ceil(weather
                .getDailyProperties()
                .getDailyDataList().get(0)
                .getTemperatureLow())));


    }
}
