package com.twinline.mvvnmretrofitexample;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepo {


    public MutableLiveData<List<PostModel>> getPostData(){

        final MutableLiveData<List<PostModel>> postModel = new MutableLiveData<>();

        ApiInterface apiInterface = RetrofitClient.getInstance().create(ApiInterface.class);

        apiInterface.getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {

                postModel.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.d("FAILURE","not data fetch",t);
            }
        });



        return postModel;
    }
}
