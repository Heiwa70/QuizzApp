package com.example.quizzapp.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuizzDataService {
    @GET("?")
    Call<List<QuizzData>> getFollowing(@Query("limit") String number);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://the-trivia-api.com/api/questions/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
