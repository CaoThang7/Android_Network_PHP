package com.example.anroid_networking.AssignmentGD1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {
    @POST("Index.php")
    Call<ServerResponse> operation(@Body ServerRequest request);
}
