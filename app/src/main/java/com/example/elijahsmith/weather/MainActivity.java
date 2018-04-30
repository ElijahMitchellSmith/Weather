package com.example.elijahsmith.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.elijahsmith.weather.api_calls.GoogleGeoApi;
import com.example.elijahsmith.weather.api_calls.dark_sky.DarkSkyApi;

import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private GoogleGeoApi mGoogleGeoApi;
    private String mGoogleBaseUrl;
    private String mGoogleApiKey;
    private Retrofit mGoogleRetrofit;
    private DarkSkyApi mDarkSkyApi;
    private String mDarkSkyBaseUrl;
    private String mDarkSkyApiKey;
    private Retrofit mDarkSkyRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
