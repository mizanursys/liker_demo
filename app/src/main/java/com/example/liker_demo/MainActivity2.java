package com.example.liker_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView list_all_recyclerview;
    public static ListView fav_item;
    List<JSONObject> all_list;
    public static List<String> all_favlist;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list_all_recyclerview = findViewById(R.id.list_item);
        fav_item = findViewById(R.id.fav_item);
        //swipe=findViewById(R.id.swipe);
        //cheack_list=findViewById(R.id.cheack_list);
        context = this;
        list_all_recyclerview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        //fav_item.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        all_list = new ArrayList<>();
        all_favlist = new ArrayList<>();
        alldata();
    }

    public void alldata() {
        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.test.api.liker.com/get_categories",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        all_list.clear();
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            Log.e("JSONRESPONCE", String.valueOf(jsonObject));



                            JSONArray emailData = jsonObject.getJSONArray("categories");


                            for (int i = 0; i < emailData.length(); i++) {
                                Log.e("category_list", emailData.get(i).toString());
                                JSONObject dat = (JSONObject) emailData.get(i);
                                JSONArray subcategory = dat.getJSONArray("subcatg");
                               // Log.e("SubCategory", String.valueOf(subcategory));
                                //RecyclerView Adapter
                                AllListAdapter adapter = new AllListAdapter(MainActivity2.this, all_list);
                                list_all_recyclerview.setAdapter(adapter);

                                //insserting all data inside array list
                                all_list.add(dat);

                                adapter.notifyDataSetChanged();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERRROR>>>>>>>", error.toString());

                    }
                });

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        requestQueue.getCache().clear();
    }
    public static void addfav(String value){
        String[] values = new String[] { value };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }


//        FavListAdapter adapter = new FavListAdapter(context, all_favlist);
//        fav_item.setAdapter((ListAdapter) adapter);
        ArrayAdapter adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,
                all_favlist);
        fav_item.setAdapter(adapter);
        all_favlist.add(value);
        Log.e("StringValue",value);
        Log.e("FavLists", String.valueOf(all_favlist));
        adapter.notifyDataSetChanged();

    }
}