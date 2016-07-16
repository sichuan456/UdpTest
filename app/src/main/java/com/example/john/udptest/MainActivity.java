package com.example.john.udptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

//    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("s199222222222","12222222222");
        Log.e("000000009","000000008");

        execPostMethod();

    }


    public interface IUserBiz {
        @POST("PostMethod")
        @FormUrlEncoded
        Call<SystemConfigJson> login(@Field("MethodName") String methodname, @Field("Pras") String params);
    }



    private void execPostMethod(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.100:9500/Base/Base_ApiService/Api/Base/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IUserBiz userBiz = retrofit.create(IUserBiz.class);


        List<String> list = new ArrayList<String>();
        list.add("ResourcePath");
        list.add("AndroidVersion");
        list.add("ClassRoomGBPort");
        list.add("IsUseWeb_HDTL");
        Type type = new TypeToken<List<String>>() {}.getType();
        String jsonStr = null;
        try {
            jsonStr = GsonTools.getJson(list, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonStr);
        Call<SystemConfigJson> call = userBiz.login("FindMore_SystemConfig",jsonArray.toString());
        call.enqueue(new Callback<SystemConfigJson>() {
            @Override
            public void onResponse(Call<SystemConfigJson> call, retrofit2.Response<SystemConfigJson> response) {
                Log.e("response",response.toString());
                Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SystemConfigJson> call, Throwable throwable) {
                Log.e("onFailure","onFailure"+throwable.toString());
                Toast.makeText(MainActivity.this,"onFailure"+throwable.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }





}
