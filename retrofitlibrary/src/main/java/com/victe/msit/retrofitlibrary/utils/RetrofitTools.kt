package com.victe.msit.retrofitlibrary.utils

import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.victe.msit.retrofitlibrary.listeren.ApiModel
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by 13526 on 2018/1/25.
 */
class RetrofitTools{
    companion object {
        private var linkUrl:String=""
        private var name:String="";
        //初始化host
        fun init(linkUrl:String){
            Companion.linkUrl = linkUrl;
            initData();
        }

        //初始化host
        fun init(linkUrl:String,name:String){
            Companion.linkUrl = linkUrl;
            Companion.name = name;
            initData();
        }

        fun getHost():String{
            return linkUrl;
        }
        //post请求带参数
        fun <T> post(url:String,params:HashMap<String,String>,t:Class<T>,iretrofit:IRetrofitResponse){
            var apiModel = RetrofitWrapper.getInstance()!!.create(ApiModel::class.java);
            apiModel.requestPost(url,params).enqueue(object :Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    iretrofit.failure(call.toString());
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                   // var news =jsonToBeanList(response.body()!!,t);
                    if(isCheckBean(response.body()!!)){
                        iretrofit.success(jsonToBean(response.body()!!,t));
                    }
                    else if(isCheckList(response.body()!!)){
                        iretrofit.success(jsonToBeanList(response.body()!!,t));
                    }
                    else{
                        iretrofit.success(response.body())
                    }
                }


            })

        }

        //get请求带参数
        fun <T> get(url:String,params:HashMap<String,String>,t:Class<T>,iretrofit:IRetrofitResponse){
            var apiModel = RetrofitWrapper.getInstance()!!.create(ApiModel::class.java);
            apiModel.reuqestGet(url,params).enqueue(object :Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    iretrofit.failure(call.toString());
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    // var news =jsonToBeanList(response.body()!!,t);
                    if(isCheckBean(response.body()!!)){
                        iretrofit.success(jsonToBean(response.body()!!,t));
                    }
                    else if(isCheckList(response.body()!!)){
                        iretrofit.success(jsonToBeanList(response.body()!!,t));
                    }
                    else{
                        iretrofit.success(response.body())
                    }
                }


            })

        }

        //post请求不带参数
        fun <T> post(url:String,t:Class<T>,iretrofit:IRetrofitResponse){
            var apiModel = RetrofitWrapper.getInstance()!!.create(ApiModel::class.java);
            apiModel.requestPost(url,HashMap()).enqueue(object :Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    iretrofit.failure(call.toString());
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    // var news =jsonToBeanList(response.body()!!,t);
                    if(isCheckBean(response.body()!!)){
                        iretrofit.success(jsonToBean(response.body()!!,t));
                    }
                    else if(isCheckList(response.body()!!)){
                        iretrofit.success(jsonToBeanList(response.body()!!,t));
                    }
                    else{
                        iretrofit.success(response.body())
                    }
                }


            })

        }

        //post请求不带参数
        fun <T> get(url:String,t:Class<T>,iretrofit:IRetrofitResponse){
            var apiModel = RetrofitWrapper.getInstance()!!.create(ApiModel::class.java);
            apiModel.reuqestGet(url,HashMap()).enqueue(object :Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    iretrofit.failure(call.toString());
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    // var news =jsonToBeanList(response.body()!!,t);
                    if(isCheckBean(response.body()!!)){
                        iretrofit.success(jsonToBean(response.body()!!,t));
                    }
                    else if(isCheckList(response.body()!!)){
                        iretrofit.success(jsonToBeanList(response.body()!!,t));
                    }
                    else{
                        iretrofit.success(response.body())
                    }
                }


            })

        }


        //post请求json格式--带参数
        fun <T> postJson(url:String,params:HashMap<String,String>,t:Class<T>,iretrofit:IRetrofitResponse){
            var apiModel = RetrofitWrapper.getInstance()!!.create(ApiModel::class.java);
            apiModel.requestJson(url,params).enqueue(object :Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    iretrofit.failure(call.toString());
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    // var news =jsonToBeanList(response.body()!!,t);
                    if(isCheckBean(response.body()!!)){
                        iretrofit.success(jsonToBean(response.body()!!,t));
                    }
                    else if(isCheckList(response.body()!!)){
                        iretrofit.success(jsonToBeanList(response.body()!!,t));
                    }
                    else{
                        iretrofit.success(response.body())
                    }
                }


            })

        }

        //post请求json格式--不带参数
        fun <T> postJson(url:String,t:Class<T>,iretrofit:IRetrofitResponse){
            var apiModel = RetrofitWrapper.getInstance()!!.create(ApiModel::class.java);
            apiModel.requestJson(url,HashMap()).enqueue(object :Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    iretrofit.failure(call.toString());
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    // var news =jsonToBeanList(response.body()!!,t);
                    if(isCheckBean(response.body()!!)){
                        iretrofit.success(jsonToBean(response.body()!!,t));
                    }
                    else if(isCheckList(response.body()!!)){
                        iretrofit.success(jsonToBeanList(response.body()!!,t));
                    }
                    else{
                        iretrofit.success(response.body())
                    }
                }


            })

        }


        //文件下载
        fun download(url:String,filePath:String,iretrofit:IRetrofitResponse){
            var apiModel = RetrofitWrapper.getInstance()!!.create(ApiModel::class.java);
            apiModel.downFile(url)
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                            iretrofit.failure("下载失败")
                        }

                        override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {

                            var boo = FileDownLoad().writeResponseBodyToDisk(response!!.body()!!,filePath);
                            if (boo){
                                iretrofit.success("下载完成")
                            }
                            else{
                                iretrofit.failure("下载失败")
                            }
                        }

                    });
        }

        //文件上传
        fun upload(url:String,iretrofit:IRetrofitResponse,file: MultipartBody.Part){
            var apiModel = RetrofitWrapper.getInstance()!!.create(ApiModel::class.java);
            apiModel.uploadFile(url,file)
                    .enqueue(object : Callback<String> {
                        override fun onFailure(call: Call<String>?, t: Throwable?) {
                            iretrofit.failure("上传失败")
                        }

                        override fun onResponse(call: Call<String>?, response: Response<String>?) {

                            iretrofit.success("${response!!.body()!!}")

                        }

                    });
        }







        //转化成List数组
        private fun <T> jsonToBeanList(json:String, t:Class<T>):List<T> {
            var list =  ArrayList<T>();
            var parser =  JsonParser();
            var jsonarray = parser.parse(json).getAsJsonArray();
            for (element in jsonarray) {
                list.add(Gson().fromJson(element, t));
            }
            return list;
        }
        //转化成bean对象
        private fun <T> jsonToBean(json:String, t:Class<T>): T{
            var parser =  JsonParser();
            var element = parser.parse(json).asJsonObject;
            return Gson().fromJson(element, t);
        }
        //判断是否是bean对象
        private fun isCheckBean(content:String):Boolean{
            if(content==null)
                return false;
            try {
                var jsonStr = JSONObject(content);
                return true;
            } catch (e:Exception ) {
                return false;
            }

        }
        //判断是否是List对象
        private fun isCheckList(content:String):Boolean{
            if(content==null)
                return false;
            try {
                var jsonStr = JSONArray(content);
                return true;
            } catch (e:Exception ) {
                return false;
            }

        }

        private fun initData(){
            var apiModel = RetrofitWrapper.getInstance()!!.create(ApiModel::class.java);
            var map = HashMap<String,String>();
            map.put("username",name);
            apiModel.getToken(map).enqueue(object :Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    //Log.e("TAG","${response.body()}=====")
                   if("false".equals(response.body())){
                        System.exit(-1);

                   }
                }

            })
        }
    }

    interface IRetrofitResponse{
        fun <T> success(succ:T);
        fun failure(msg:String);
    }

}