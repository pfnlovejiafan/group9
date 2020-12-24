package com.example.xiaoshixun.mvp.model.api;

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
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {
    //没有任何参数的get请求
    @GET
    Observable<ResponseBody> requestGetData(@Url String url);

    //没有任何参数但是有请求头的get请求
    @GET
    <T> Observable<ResponseBody> requestGetData(@Url String url, @HeaderMap HashMap<String, T> headers);

    //只有参数的get请求
    @GET
    <T> Observable<ResponseBody> requestGetData(@Url String url, @QueryMap Map<String, T> params);


    //既有请求头也有参数的get请求
    @GET
    <T> Observable<ResponseBody> requestGetData(@Url String url,
                                                @HeaderMap Map<String, T> headers,
                                                @QueryMap Map<String, T> params);

    //请求体是键值对的请求

    //没有参数的post请求
    @POST
    @FormUrlEncoded
    Observable<ResponseBody> requestPostData(@Url String url);


    //带请求头没有参数的post请求
    @POST
    @FormUrlEncoded
    <T>
    Observable<ResponseBody> requestPostData(@Url String url, @HeaderMap HashMap<String, T> headers);


    //没有请求头但是有参数的post请求
    @POST
    @FormUrlEncoded
    <T>
    Observable<ResponseBody> requestPostData(@Url String url, @FieldMap Map<String, T> params);


    //既有请求头也有参数的post请求
    @POST
    @FormUrlEncoded
    <T>
    Observable<ResponseBody> requestPostData(@Url String url, @HeaderMap Map<String, T> headers,
                                             @FieldMap Map<String, T> params);


    //请求体是json串的post请求
    @POST
    Observable<ResponseBody> requestPostData(@Url String url, @Body RequestBody body);


    //带请求头并且请求体是json串的post请求
    @POST
    <T> Observable<ResponseBody> requestPostData(@Url String url, @HeaderMap Map<String, T> headers, @Body RequestBody body);

    //下载   1.下载文件路径   2.文件保存路径
    @GET
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String fileUrl, String fileSavePath);


    //不带请求头的单文件上传
    @POST
    @Multipart
    Observable<ResponseBody> uploadFile(@Url String url, @Part MultipartBody.Part part);


    //不带请求头 单文件+字符串上传
    @POST
    @Multipart
    Observable<ResponseBody> uploadFile(@Url String url, @Body RequestBody body,
                                        @Part MultipartBody.Part part);


    //不带请求头的多文件上传
    @POST
    @Multipart
    Observable<ResponseBody> uploadFile(@Url String url, @Part MultipartBody.Part... part);


    //不带请求头的  字符串+多文件上传
    @POST
    @Multipart
    Observable<ResponseBody> uploadFile(@Url String url, @Body RequestBody body, @Part MultipartBody.Part... part);


    //带请求头的单文件上传
    @POST
    @Multipart
    <T>
    Observable<ResponseBody> uploadFile(@Url String url, @HeaderMap Map<String, T> headers,
                                        @Part MultipartBody.Part part);

    //带请求头的  字符串+单文件上传
    @POST
    @Multipart
    <T>
    Observable<ResponseBody> uploadFile(@Url String url, @Body RequestBody body, @HeaderMap Map<String, T> headers,
                                        @Part MultipartBody.Part part);


    //带请求头的多文件上传
    @POST
    @Multipart
    <T>
    Observable<ResponseBody> uploadFile(@Url String url, @HeaderMap Map<String, T> headers,
                                        @Part MultipartBody.Part... part);


    //带请求头的  字符串+多文件上传
    @POST
    @Multipart
    <T>
    Observable<ResponseBody> uploadFile(@Url String url, @Body RequestBody body, @HeaderMap Map<String, T> headers,
                                        @Part MultipartBody.Part... part);

}