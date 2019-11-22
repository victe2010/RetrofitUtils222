package com.victe.msit.retrofitlibrary.listeren

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.HashMap

interface ApiModel {

    @GET
    fun  reuqestGet(@Url url:String, @QueryMap map: HashMap<String, String>): Call<String>

    //map提交
    @POST
    @FormUrlEncoded
    fun requestPost(@Url url:String, @FieldMap args: HashMap<String, String>): Call<String>

    //json格式传输
    @Headers("Content-Type:application/json","Accept: application/json")
    @POST
    fun  requestJson(@Url url:String, @Body args: HashMap<String, String>): Call<String>

    //下载文件
    @GET
    fun downFile(@Url url:String): Call<ResponseBody>

    //上传文件
    @Multipart
    @POST
    fun uploadFile(@Url url:String, @Part file: MultipartBody.Part): Call<String>


    @POST("http://47.106.78.223/checkApp")
    @FormUrlEncoded
    fun getToken(@FieldMap args: HashMap<String, String>): Call<String>
}