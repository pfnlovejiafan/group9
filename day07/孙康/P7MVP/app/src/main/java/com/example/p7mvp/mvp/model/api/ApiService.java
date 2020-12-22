package com.example.p7mvp.mvp.model.api;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {

                         //TODO 普通GET请求
    @GET //无参
    Observable<ResponseBody> requestGet(@Url String url);

    @GET//有参
    <T> Observable<ResponseBody> requestGet(@Url String url, @QueryMap Map<String, T> params);

    @GET //无参,有头
    <T> Observable<ResponseBody> requestGet(@Url String url, @HeaderMap HashMap<String, T> headers);

    @GET//有参,有头
    <T> Observable<ResponseBody> requestGet(@Url String url, @HeaderMap Map<String, T> headers,
                                            @QueryMap Map<String, T> params);


                     //TODO  普通POST请求
    @POST//无参
    @FormUrlEncoded
    Observable<ResponseBody> requestPost(@Url String url);

    @POST//有参
    @FormUrlEncoded
    <T> Observable<ResponseBody> requestPost(@Url String url, @FieldMap Map<String, T> params);

    @POST//无参,有头
    @FormUrlEncoded
    <T> Observable<ResponseBody> requestPost(@Url String url, @HeaderMap HashMap<String, T> headers);

    @POST//有参,有头
    @FormUrlEncoded
    <T> Observable<ResponseBody> requestPost(@Url String url, @HeaderMap Map<String, T> headers,
                                                 @FieldMap Map<String, T> params);

    @POST//请求体是body
    Observable<ResponseBody> requestPost(@Url String url, @Body RequestBody body);

    @POST//请求体是body 有头
    <T> Observable<ResponseBody> requestPostData(@Url String url, @HeaderMap Map<String, T> headers,
                                                 @Body RequestBody body);

                    //TODO 特殊请求 上传下载
    @GET//GET下载
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String fileUrl, String fileSavePath);

    @POST//无头,上传
    @Multipart
    Observable<ResponseBody> uploadFile(@Url String url, @Part MultipartBody.Part part);

    @POST//无头 单文件+字符串上传
    @Multipart
    Observable<ResponseBody> uploadFile(@Url String url, @Body RequestBody body,
                                        @Part MultipartBody.Part part);

    @POST//不带请求头的多文件上传
    @Multipart
    Observable<ResponseBody> uploadFile(@Url String url, @Part MultipartBody.Part... part);

    @POST//不带请求头的  字符串+多文件上传
    @Multipart
    Observable<ResponseBody> uploadFile(@Url String url, @Body RequestBody body, @Part MultipartBody.Part... part);

    @POST//带请求头的单文件上传
    @Multipart
    <T> Observable<ResponseBody> uploadFile(@Url String url, @HeaderMap Map<String, T> headers,
                                            @Part MultipartBody.Part part);
    @POST//带请求头的  字符串+单文件上传
    @Multipart
    <T> Observable<ResponseBody> uploadFile(@Url String url, @Body RequestBody body, @HeaderMap Map<String, T> headers,
                                            @Part MultipartBody.Part part);

    @POST//带请求头的多文件上传
    @Multipart
    <T> Observable<ResponseBody> uploadFile(@Url String url, @HeaderMap Map<String, T> headers,
                                            @Part MultipartBody.Part... part);

    @POST//带请求头的  字符串+多文件上传
    @Multipart
    <T> Observable<ResponseBody> uploadFile(@Url String url, @Body RequestBody body, @HeaderMap Map<String, T> headers,
                                            @Part MultipartBody.Part... part);
}
