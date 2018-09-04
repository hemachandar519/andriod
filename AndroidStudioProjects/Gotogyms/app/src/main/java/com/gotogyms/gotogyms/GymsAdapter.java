package com.gotogyms.gotogyms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jyothsna on 7/4/2017.
 */

public class GymsAdapter extends RecyclerView.Adapter<GymsAdapter.MyViewHolder> {
    ArrayList<Gympartners> gymList=new ArrayList<>();
    public GymsAdapter(ArrayList<Gympartners> arrayList)
    {
        this.gymList=arrayList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gym_ameerpeta,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.g_pic.setImageResource(gymList.get(position).getGym_id());
        holder.g_name.setText(gymList.get(position).getName());
    }

    @Override
    public int getItemCount() {

        return gymList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView g_pic;
        TextView g_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            g_pic=(ImageView)itemView.findViewById(R.id.gympic);
            g_name=(TextView)itemView.findViewById(R.id.gymname);
        }
    }

    public void setFilter(ArrayList<Gympartners> newList){
        gymList=new ArrayList<>();
        gymList.addAll(newList);
        notifyDataSetChanged();
    }
}
