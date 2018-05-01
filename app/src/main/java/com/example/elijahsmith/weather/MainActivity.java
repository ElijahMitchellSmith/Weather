package com.example.elijahsmith.weather;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.elijahsmith.weather.api_calls.GoogleGeoApi;
import com.example.elijahsmith.weather.api_calls.dark_sky.DarkSkyApi;
import com.example.elijahsmith.weather.api_calls.dark_sky.Weather;
import com.example.elijahsmith.weather.api_calls.google.GoogleAddress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private GoogleGeoApi mGoogleGeoApi;
    private String mGoogleBaseUrl;
    private String mGoogleApiKey;
    private Retrofit mGoogleRetrofit;
    private DarkSkyApi mDarkSkyApi;
    private String mDarkSkyBaseUrl;
    private String mDarkSkyApiKey;
    private Retrofit mDarkSkyRetrofit;
    private GoogleGeoApi googleGeoApi;
    @BindView(R.id.location_edittext)
    protected TextInputEditText location;
    private Bundle bundle;
    private WeatherFragment weatherFragment;
    public static final String PLACE = "place";
    public static final String WEATHER = "weather";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        weatherFragment = WeatherFragment.newInstance();
        bundle = new Bundle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleBaseUrl = getString(R.string.google_base_url);
        mDarkSkyBaseUrl = getString(R.string.dark_sky_base_url);
        mGoogleApiKey = getString(R.string.google_api_key);
        mDarkSkyApiKey = getString(R.string.dark_sky_api_key);
        mGoogleGeoApi = getGoogleRetrofit().create(GoogleGeoApi.class);
        mDarkSkyApi = getDarkSkyRetrofit().create(DarkSkyApi.class);
    }

    private Retrofit getGoogleRetrofit() {
        if (mGoogleRetrofit == null) {
            mGoogleRetrofit = new Retrofit.Builder()
                    .baseUrl(mGoogleBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

            return mGoogleRetrofit;

        }
        private Retrofit getDarkSkyRetrofit () {
        if (mDarkSkyRetrofit == null) {
            mDarkSkyRetrofit = new Retrofit.Builder()
                    .baseUrl(mDarkSkyBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

            return mDarkSkyRetrofit;

        }
        @OnClick(R.id.submit_button)
    protected void searchClicked() {

        getLocation(location.getText().toString());
        location.setText("");

        }

    private void getLocation(String address) {
        mGoogleGeoApi.getAddress(address, mGoogleApiKey).enqueue(new Callback<GoogleAddress>() {
            @Override
            public void onResponse(Call<GoogleAddress> call, Response<GoogleAddress> response) {
                try {
                    if (response.isSuccessful()) {

                        bundle.putString(PLACE, response.body().getResultsList().get(0).getFormattedAddress());
                        getWeather(response.body().getResultsList().get(0).getGeometry().getGoogleLocation().getLatitude(),
                                response.body().getResultsList().get(0).getGeometry().getGoogleLocation().getLongitude());


                    } else {
                        Toast.makeText(MainActivity.this, "Call made, unsuccessful", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<GoogleAddress> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Failed", Toast.LENGTH_LONG).show();

            }
        });

    }
    private void getWeather(double lat, double lng) {
        mDarkSkyApi.getWeather(mDarkSkyApiKey ,lat, lng).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                try {
                    if (response.isSuccessful()) {
                        bundle.putParcelable(WEATHER, response.body());
                        weatherFragment.setArguments(bundle);
                        transitionToWeatherFragment();


                    } else {
                        Toast.makeText(MainActivity.this, "Call made, unsuccessful", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Weather Call Failed", Toast.LENGTH_LONG).show();


            }
        });

    }
    private void transitionToWeatherFragment() {


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, weatherFragment).commit();
    }
}

