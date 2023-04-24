package com.aliberkaygdk.rickandmorty.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliberkaygdk.rickandmorty.R;
import com.aliberkaygdk.rickandmorty.activity.DetailsActivity;
import com.aliberkaygdk.rickandmorty.api.ApiClient;
import com.aliberkaygdk.rickandmorty.api.ApiInterface;
import com.aliberkaygdk.rickandmorty.model.Character;
import com.aliberkaygdk.rickandmorty.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.Holder> {
    List<Result> res;
    private int locationId;
    Context context;

    public CharacterAdapter(List<Result> res, int locationId,Context context) {
        this.res = res;
        this.locationId=locationId;
        this.context=context;
    }

    @NonNull
    @Override
    public CharacterAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.character,parent,false);



        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.Holder holder, int position) {

        holder.textViewName.setTextColor(Color.BLACK);

        String s=res.get(locationId-1).getResidents().get(position);
        String s1=s.substring(42);

        int id= Integer.parseInt(s1);

        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getCharsInLoc(id).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                holder.textViewName.setText(response.body().getName());
                Picasso.get().load(response.body().getImage()).into(holder.imageView);
                if (response.body().getGender().matches("Male"))
                    holder.genderIcon.setImageResource(R.drawable.ic_gender_male);
                else if (response.body().getGender().matches("Female"))
                    holder.genderIcon.setImageResource(R.drawable.ic_gender_female);
                else if (response.body().getGender().matches("Genderless"))
                    holder.genderIcon.setImageResource(R.drawable.ic_gender_genderless);
                else
                    holder.genderIcon.setImageResource(R.drawable.ic_gender_unknown);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailsActivity.class);
                        intent.putExtra("charId", id);
                        context.startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return res.get(locationId-1).getResidents().size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageView,genderIcon;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.nameText);
            imageView=itemView.findViewById(R.id.imageView);
            genderIcon=itemView.findViewById(R.id.genderIcon);


        }
    }
}
