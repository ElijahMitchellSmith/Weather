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

import static com.example.elijahsmith.weather.MainActivity.PLACE;
import static com.example.elijahsmith.weather.MainActivity.WEATHER;

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
        weather = getArguments().getParcelable(WEATHER);
        place = getArguments().getString(PLACE);
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

        precipText.setText(getString(R.string.precip_chance_format, 100 * Math.ceil(weather
                .getDailyProperties()
                .getDailyDataList().get(0)
                .getPrecipProbability())));

        setWeatherIcon();


    }

    private void setWeatherIcon() {
        switch (weather.getCurrentProperties().getIcon()) {

            case "clear-day":
                weatherIcon.setImageResource(R.drawable.clear_day);
                mLayout.setBackgroundResource(R.color.sunColor);
                break;
            case "clear_night":
                weatherIcon.setImageResource(R.drawable.clear_night);
                mLayout.setBackgroundResource(R.color.nightColor);
                break;
            case "rain":
                weatherIcon.setImageResource(R.drawable.rain);
                mLayout.setBackgroundResource(R.color.rainColor);
                break;
            case "snow":
                weatherIcon.setImageResource(R.drawable.snow);
                mLayout.setBackgroundResource(R.color.snowColor);
                break;
            case "sleet":
                weatherIcon.setImageResource(R.drawable.sleet);
                mLayout.setBackgroundResource(R.color.sleetColor);
                break;
            case "wind":
                weatherIcon.setImageResource(R.drawable.wind);
                mLayout.setBackgroundResource(R.color.windColor);
                break;
            case "fog":
                weatherIcon.setImageResource(R.drawable.fog);
                mLayout.setBackgroundResource(R.color.fogColor);
                break;
            case "cloudy":
                weatherIcon.setImageResource(R.drawable.cloudy);
                mLayout.setBackgroundResource(R.color.cloudyColor);
                break;
            case "partly-cloudy-day":
                weatherIcon.setImageResource(R.drawable.partly_cloudy_day);
                mLayout.setBackgroundResource(R.color.cloudyColor);
                break;
            case "partly-cloudy-night":
                weatherIcon.setImageResource(R.drawable.partly_cloudy_night);
                mLayout.setBackgroundResource(R.color.cloudyColor);
                break;
            default:
                weatherIcon.setImageResource(R.drawable.thunderstorm);
                mLayout.setBackgroundResource(R.color.stormColor);
                break;



        }


    }
}
