package com.rxandroidsample.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rxandroidsample.data.model.Profile;
import com.rxandroidsample.data.model.Ribot;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by anhndt on 5/25/16.
 */
public interface RibotService {
    String ENDPOINT = "https://api.ribot.io/";

    @GET("ribots")
    Observable<List<Ribot>> getRibots();

    static class CREATOR {
        public static RibotService newRibotService() {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RibotService.class);
        }
    }
}
