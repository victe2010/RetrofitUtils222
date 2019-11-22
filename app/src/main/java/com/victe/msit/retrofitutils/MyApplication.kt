package com.victe.msit.retrofitutils

import android.app.Application
import com.victe.msit.retrofitlibrary.listeren.ApiModel
import com.victe.msit.retrofitlibrary.utils.RetrofitTools
import com.victe.msit.retrofitlibrary.utils.RetrofitWrapper

class MyApplication :Application(){
   companion object {
       lateinit var service: ApiModel;
   }

    override fun onCreate() {
        super.onCreate()
        RetrofitTools.init("http://192.168.0.103:8080/","a21");
        service = RetrofitWrapper.getInstance()!!.createCookie(ApiModel::class.java);

    }
}