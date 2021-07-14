package com.example.liker_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.AllDataViewHolder> {

    public static Context mCtx;
    private List<JSONObject> all_list =new ArrayList<>();


    public SubAdapter(Context mCtx, List<JSONObject> all_list) {
        this.mCtx = mCtx;
        this.all_list = all_list;
//        int size=all_list.size();
  //      String total_data= String.valueOf(size);
    }

    @Override
    public AllDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //ViewHolder For RecyclerView
        View view = inflater.inflate(R.layout.sub_category, parent, false);
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


            //Log.e("post_title", list_jsonobject.getString(("sub_category_name")));
            if (list_jsonobject.isNull("sub_category_name" )){
                holder.category.setVisibility(View.GONE);
            }else {
                holder.category.setText(list_jsonobject.getString("sub_category_name"));
                holder.category.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String name = holder.category.getText().toString();


//                            JSONObject obj = new JSONObject(name);
//
//                            Log.d("My App", obj.toString());

                            MainActivity2.addfav(name);

                        Log.e(

                                "CheckedName",name
                        );

                    }
                });
            }


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

        CheckBox category;
        RecyclerView list_all_recyclerview;
        List<JSONObject> all_list;

        Button details_button;
        LinearLayout details;
        ImageView like,dislike;
        public static String post_id;

        @SuppressLint("CutPasteId")
        public AllDataViewHolder(View itemView) {
            super(itemView);
            category =itemView.findViewById(R.id.category);


        }



    }

}


