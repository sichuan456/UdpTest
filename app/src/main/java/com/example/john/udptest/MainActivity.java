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
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

//    OkHttpClient client = new OkHttpClient();

    private static final String BASE_URL = "http://apis.baidu.com";
    private static final String API_KEY = "9be1d688122fb69554a98eb3b44f6149";

    private void query(){
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl(BASE_URL)//主机地址
                .build();

        //2.创建访问API的请求
        PhoneService service = retrofit.create(PhoneService.class);
        Call<PhoneResult> call = service.getResult(API_KEY, "15624055438");

        //3.发送请求
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                //4.处理结果
//                if (response.isSuccess()){
//                    PhoneResult result = response.body();
//                    if (result != null){
//                        PhoneResult.RetDataEntity entity = result.getRetData();
//                    }
//                }
                if(response.isSuccessful()){
                    PhoneResult result = response.body();
                    if (result != null){
                        PhoneResult.RetDataEntity entity = result.getRetData();
                        Log.e("&&&&&&&&&&",result.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {
                Log.e("&qqqqqqqqqqqqq",t.toString());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        execPostMethod();
        query();
    }

    public interface PhoneService {
        @GET("/apistore/mobilenumber/mobilenumber")
        Call<PhoneResult> getResult(@Header("apikey") String apikey, @Query("phone") String phone);
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
