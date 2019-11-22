package com.victe.msit.retrofitutils

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.victe.msit.retrofitlibrary.listeren.ProgressResponseListener
import com.victe.msit.retrofitlibrary.utils.FileDownLoad
import com.victe.msit.retrofitlibrary.utils.RetrofitTools
import com.victe.msit.retrofitlibrary.utils.RetrofitWrapper
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var loginBtn = findViewById<Button>(R.id.login_btn);
        var getAllBtn = findViewById<Button>(R.id.get_all_btn);

        loginBtn.setOnClickListener{
            var map = HashMap<String,String>();
            map.put("username","1")
            map.put("password","1")
            RetrofitTools.post("login",map,User::class.java,object:RetrofitTools.IRetrofitResponse{
                override fun <T> success(succ: T) {
                    var user = succ as User;
                    Log.e("TAG",user.nickname)
                }

                override fun failure(msg: String) {

                }

            })

        }

        getAllBtn.setOnClickListener {

           RetrofitTools.post("getNews", News::class.java,object : RetrofitTools.IRetrofitResponse{
               override fun <T> success(succ: T) {
                   var news = succ as List<News>;
                   for (n in news){
                       Log.e("TAG","${n.title}===${n.content}")
                   }
               }

               override fun failure(msg: String) {

               }

           })

//            MyApplication.service.requestPost("getNews",HashMap()).enqueue(object :Callback<String>{
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                }
//
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    var news =jsonToBeanList(response.body()!!,News::class.java);
//                    Log.e("TAG",news.toString())
//                    Log.e("TAG",news[0].toString())
//                    Log.e("TAG","${news.size}====");
//                    for (new in news){
//                        Log.e("TAG","${new.title}===${new.content}")
//                    }
//
//                }
//                fun <T> jsonToBeanList(json:String, t:Class<T>):List<T> {
//                    var list =  ArrayList<T>();
//                    var parser =  JsonParser();
//                    var jsonarray = parser.parse(json).getAsJsonArray();
//                    for (element in jsonarray) {
//                        list.add(Gson().fromJson(element, t));
//                    }
//                    return list;
//                }
//
//            })
        }



        uploadBtn.setOnClickListener {
            var service =  RetrofitWrapper.getInstance()!!.createResponseService(Api::class.java,object : ProgressResponseListener {
                override fun onResponseProgress(bytesRead: Long, contentLength: Long, done: Boolean) {
                    Log.e("TAG","--${bytesRead}-----------${contentLength}-----------${done}")
                }

            });
            service.downFile("https://uploadcenter.16rtt.com/lottery-download?oauth_identifier=huasheng&device_type=3&tag=app_weex&version=1.0.1")
                    .enqueue(object : Callback<ResponseBody> {
                        override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {

                        }

                        override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                            var filePath = Environment.getExternalStorageDirectory().absolutePath+"/"+packageName+"/a.apk";
                            var boo = FileDownLoad().writeResponseBodyToDisk(response!!.body()!!,filePath);
                            if (boo){
                               Toast.makeText(applicationContext,"succ",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(applicationContext,"fail",Toast.LENGTH_LONG).show();
                            }
                        }

                    });
        }
        downBtn.setOnClickListener {
          //上传

        }

    }



}
