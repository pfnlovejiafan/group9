package com.example.project.mvp.model.api;

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

    //没有任何参数的get请求
    @GET
    Observable<ResponseBody> requestGetData(@Url String url);

    //没有参数但有请求头的get请求
    @GET
    <T> Observable<ResponseBody> requestGetData(@Url String url, @HeaderMap HashMap<String, T> headers);

    //有参数的get请求
    @GET
    <T> Observable<ResponseBody> requestGetData(@Url String url, @QueryMap Map<String, T> params);

    //既有请求头又有参数的get请求
    @GET
    <T> Observable<ResponseBody> requestGetData(@Url String url, @HeaderMap Map<String, T> headers, @QueryMap Map<String, T> params);



    //请求体是键值对的post请求

    //没有参数的post请求
    @POST
    @FormUrlEncoded
    Observable<ResponseBody> requestPostData(@Url String url);

    //带请求头但是没有参数post请求
    @POST
    @FormUrlEncoded
    <T> Observable<ResponseBody> requestPostData(@Url String url, @HeaderMap HashMap<String, T> headers);

    //带参数的post请求
    @POST
    @FormUrlEncoded
    <T> Observable<ResponseBody> requestPostData(@Url String url, @FieldMap Map<String, T> params);

    //既有请求头又有参数的post请求
    @POST
    @FormUrlEncoded
    <T> Observable<ResponseBody> requestPostData(@Url String url, @HeaderMap Map<String, T> headers, @FieldMap Map<String, T> params);


    //请求体是json串的post请求
    @POST
    Observable<ResponseBody> requestPostData(@Url String url, @Body RequestBody body);


    //带请求头并且请求体是json串的post请求
    @POST
    <T> Observable<ResponseBody> requestPostData(@Url String url, @HeaderMap Map<String, T> headers, @Body RequestBody body);



    //下载       fileUrl:下载文件的路径  fileSavePath:文件保存的路径
    @GET
    @Streaming
    Observable<ResponseBody> downLoadFile(@Url String fileUrl, String fileSavePath);




    //不带请求头的单文件上传
    @POST
    @Multipart
    Observable<ResponseBody> upLoadFile(@Url String url, @Part MultipartBody.Part part);

    //不带请求头的 字符串+单文件上传
    @POST
    @Multipart
    Observable<ResponseBody> upLoadFile(@Url String url, @Body RequestBody body, @Part MultipartBody.Part part);

    //带请求头的单文件上传
    @POST
    @Multipart
    <T> Observable<ResponseBody> upLoadFile(@Url String url, @HeaderMap Map<String, T> headers, @Part MultipartBody.Part part);

    //带请求头的 字符串+单文件上传
    @POST
    @Multipart
    <T> Observable<ResponseBody> upLoadFile(@Url String url, @HeaderMap Map<String, T> headers, @Body RequestBody body, @Part MultipartBody.Part part);


    //不带请求头的多文件上传
    @POST
    @Multipart
    Observable<ResponseBody> upLoadFile(@Url String url, @Part MultipartBody.Part... part);

    //不带请求头的 字符串+多文件上传
    @POST
    @Multipart
    Observable<ResponseBody> upLoadFile(@Url String url, @Body RequestBody body, @Part MultipartBody.Part... part);


    //带请求头的多文件上传
    @POST
    @Multipart
    <T> Observable<ResponseBody> upLoadFile(@Url String url, @HeaderMap Map<String, T> headers, @Part MultipartBody.Part... part);

    //带请求头的 字符串+多文件上传
    @POST
    @Multipart
    <T> Observable<ResponseBody> upLoadFile(@Url String url, @HeaderMap Map<String, T> headers, @Body RequestBody body, @Part MultipartBody.Part... part);
}
