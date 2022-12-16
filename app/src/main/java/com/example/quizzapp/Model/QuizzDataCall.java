package com.example.quizzapp.Model;

import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizzDataCall {
    // Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable List<QuizzData> users);
        void onFailure();
    }

    // Public method to start fetching users following by Jake Wharton
    public static void fetchUserFollowing(Callbacks callbacks, String numbers){

        // Create a weak reference to callback (avoid memory leaks)
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        // Get a Retrofit instance and the related endpoints
        QuizzDataService quizzDataService = QuizzDataService.retrofit.create(QuizzDataService.class);

        // Create the call on Github API
        Call<List<QuizzData>> call = quizzDataService.getFollowing(numbers);
        // Start the call
        call.enqueue(new Callback<List<QuizzData>>() {

            @Override
            public void onResponse(Call<List<QuizzData>> call, Response<List<QuizzData>> response) {
                // Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());


            }

            @Override
            public void onFailure(Call<List<QuizzData>> call, Throwable t) {
                // Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }

}
