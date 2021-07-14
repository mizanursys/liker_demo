package com.example.liker_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllListAdapter extends RecyclerView.Adapter<AllListAdapter.AllDataViewHolder> {

    public static Context mCtx;
    private List<JSONObject> all_list =new ArrayList<>();


    public AllListAdapter(Context mCtx, List<JSONObject> all_list) {
        this.mCtx = mCtx;
        this.all_list = all_list;
//        int size=all_list.size();
  //      String total_data= String.valueOf(size);
    }

    @Override
    public AllDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //ViewHolder For RecyclerView
        View view = inflater.inflate(R.layout.all_category, parent, false);
        final AllDataViewHolder alldataViewHolder=new AllDataViewHolder(view);

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position=alldataViewHolder.getAdapterPosition();
                Toast.makeText(mCtx,"Item at position "+position+" deleted", Toast.LENGTH_SHORT).show();
                //  all_list.add((JSONObject) all_list);
                notifyDataSetChanged();
                //if(personModifier!=null){personModifier.onPersonDeleted(position);}
                return true;
            }
        });




        return new AllDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AllDataViewHolder holder, final int position) {
        try {
            final JSONObject list_jsonobject = all_list.get(position);

            JSONArray emailData = list_jsonobject.getJSONArray("subcatg");
            for (int i = 0; i < emailData.length(); i++) {
                //all_list.clear();
                Log.e("category_list", emailData.get(i).toString());
                JSONObject dat = (JSONObject) emailData.get(i);
                Log.e("SUbCatOF", String.valueOf(dat));
                SubAdapter adapter = new SubAdapter(mCtx, holder.all_sublist);
                holder.list_all_recyclerview.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
                //Log.e("post_title", list_jsonobject.getString(("category_name")));

                //insserting all data inside array list
                holder.all_sublist.add(dat);
            }

            holder.category.setText(list_jsonobject.getString("category_name"));
            holder.category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e("ItemClicked","Trrue");
                    holder.list_all_recyclerview.setVisibility(View.VISIBLE);

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }




    @Override
    public int getItemCount() {

        try {
            return all_list.size();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    static class AllDataViewHolder extends RecyclerView.ViewHolder{

        TextView category;
        RecyclerView list_all_recyclerview;
        List<JSONObject> all_sublist;

        Button details_button;
        LinearLayout details;
        ImageView like,dislike;
        public static String post_id;

        @SuppressLint("CutPasteId")
        public AllDataViewHolder(View itemView) {
            super(itemView);
            category =itemView.findViewById(R.id.category);
            list_all_recyclerview =itemView.findViewById(R.id.list_item);
            //swipe=findViewById(R.id.swipe);
            //cheack_list=findViewById(R.id.cheack_list);

            list_all_recyclerview.setLayoutManager(new LinearLayoutManager(mCtx, RecyclerView.VERTICAL, false));

            all_sublist = new ArrayList<>();

        }



    }

}


