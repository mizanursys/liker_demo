package com.example.liker_demo;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class FavListAdapter extends RecyclerView.Adapter<FavListAdapter.AllDataViewHolder> {

    public static Context mCtx;
    private List<String> all_list =new ArrayList<>();


    public FavListAdapter(Context mCtx, List<String> all_list) {
        this.mCtx = mCtx;
        this.all_list = all_list;
//        int size=all_list.size();
  //      String total_data= String.valueOf(size);
    }

    @Override
    public AllDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //ViewHolder For RecyclerView
        View view = inflater.inflate(R.layout.fav_item_list, parent, false);
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
              final String list_jsonobject = all_list.get(position);


              Log.e("ITEMFAV",list_jsonobject);

            holder.category.setText(list_jsonobject);
            holder.category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e("ItemClicked","Trrue");
                    holder.list_all_recyclerview.setVisibility(View.VISIBLE);

                }
            });



    }




    @Override
    public int getItemCount() {

//        try {
//            return all_list.size();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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


