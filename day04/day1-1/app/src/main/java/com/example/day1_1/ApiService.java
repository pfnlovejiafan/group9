package com.example.day1_1;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    public static final String BASE_URL="";
    @POST("")
    @Multipart
    Observable<ResponseBody> uploadFile(@Part MultipartBody.Part part);

}
