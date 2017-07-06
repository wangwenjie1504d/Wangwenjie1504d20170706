package com.bawei.wangwenjie.wangwenjie1504d20170706;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
     Handler hanlder=new Handler();
         Runnable runnable=new Runnable() {
             @Override
             public void run() {
                 Gson gson=new Gson();
                 nNews nNews = gson.fromJson(s, nNews.class);
                 arrayList.addAll(nNews.getResult().getData());
                 mybase.notifyDataSetChanged();
             }
         };
    private String s;
    private ArrayList<nNews.ResultBean.DataBean> arrayList;
    private ListView listView;
    private MainActivity.mybase mybase;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv);
        arrayList = new ArrayList<>();
        addOkhttp();
        mybase = new mybase();
        listView.setAdapter(mybase);
    }

    private void addOkhttp() {
        //创建okhttp
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个request
        Request request = new Request.Builder().url("http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4").build();
        //new call
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                s = response.body().string();
                Log.e("aaa",s);
                hanlder.post(runnable);
            }
        });
    }
    class mybase extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView==null){
                viewHolder=new ViewHolder();
                convertView=View.inflate(MainActivity.this,R.layout.mybase,null);
                viewHolder.texttitle= (TextView) convertView.findViewById(R.id.text1);
                viewHolder.imageView=(ImageView)convertView.findViewById(R.id.image1);
                convertView.setTag(viewHolder);
            }else{
               viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.texttitle.setText(arrayList.get(position).getTitle());
            Glide.with(MainActivity.this).load(arrayList.get(position).getThumbnail_pic_s()).into(viewHolder.imageView);
            return convertView;
        }
        class ViewHolder{
            TextView texttitle;
            ImageView imageView;
        }
    }
}
