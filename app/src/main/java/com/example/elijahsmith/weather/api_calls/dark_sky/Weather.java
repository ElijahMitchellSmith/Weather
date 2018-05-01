package com.example.elijahsmith.weather.api_calls.dark_sky;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class Weather implements Parcelable  {

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("currently")
    private CurrentProperties mCurrentProperties;

    @SerializedName("daily")
    private DailyProperties mDailyProperties;

    protected Weather(Parcel in) {
        longitude = in.readDouble();
        latitude = in.readDouble();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);

    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public CurrentProperties getCurrentProperties() {
        return mCurrentProperties;
    }

    public DailyProperties getDailyProperties() {
        return mDailyProperties;
    }
    public class CurrentProperties  {
        @SerializedName("icon")
        private String icon;
        @SerializedName("summary")
        private String summary;
        @SerializedName("temperature")
        private double temperature;

        public String getIcon() {
            return icon;
        }

        public String getSummary() {
            return summary;
        }

        public double getTemperature() {
            return temperature;
        }
    }
    public class DailyProperties {
        @SerializedName("data")
        private List<DailyData> dailyData;

        public List<DailyData> getDailyDataList() {
            return dailyData;
        }
    }
          public class DailyData {
            @SerializedName("temperatureHigh")
            private double temperatureHigh;
            @SerializedName("temperatureLow")
            private double temperatureLow;
            @SerializedName("precipProbability")
            private double precipProbability;

              public double getTemperatureHigh() {
                  return temperatureHigh;
              }

              public double getTemperatureLow() {
                  return temperatureLow;
              }

              public double getPrecipProbability() {
                  return precipProbability;
              }
          }


    }


