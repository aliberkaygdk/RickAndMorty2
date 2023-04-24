package com.aliberkaygdk.rickandmorty.adapter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliberkaygdk.rickandmorty.R;
import com.aliberkaygdk.rickandmorty.activity.MainActivity;
import com.aliberkaygdk.rickandmorty.model.Result;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.Holder> {


    SharedPreferences sharedPreferences;
    private int selectedPosition=0;
    private int lastItemSelectedPos=0;

    public List<Result> locationList;

    public LocationAdapter(List<Result> locationList) {
        this.locationList = locationList;


    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.locations,parent,false);
        Holder holder=new Holder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder,int position) {

        holder.button.setBackgroundColor(Color.LTGRAY);
        holder.button.setTextColor(Color.BLACK);
        holder.button.setText(locationList.get(position).getName());


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedPosition=holder.getBindingAdapterPosition();
                if(lastItemSelectedPos != selectedPosition){
                    notifyItemChanged(lastItemSelectedPos);
                }
                lastItemSelectedPos = selectedPosition;
                notifyItemChanged(lastItemSelectedPos);

                sharedPreferences=holder.itemView.getContext().getSharedPreferences("com.aliberkaygdk.rickandmorty.adapter",v.getContext().MODE_PRIVATE);
                sharedPreferences.edit().putInt("locid",locationList.get(holder.getPosition()).getId()).apply();

                ((MainActivity)holder.itemView.getContext()).onClick();

            }


        });

        if(holder.getBindingAdapterPosition() == selectedPosition) {
            holder.button.setBackgroundColor(Color.WHITE);
            holder.button.setTextColor(Color.BLACK);

        }

    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        Button button;

        public Holder(@NonNull View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.locationButton);


        }
    }
}
