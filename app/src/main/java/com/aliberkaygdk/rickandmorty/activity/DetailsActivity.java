package com.aliberkaygdk.rickandmorty.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliberkaygdk.rickandmorty.R;
import com.aliberkaygdk.rickandmorty.api.ApiClient;
import com.aliberkaygdk.rickandmorty.api.ApiInterface;
import com.aliberkaygdk.rickandmorty.model.Character;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    Intent intent;
    TextView name,status,specy,gender,origin,location,episodes,createdAt;
    ImageView imageView,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        intent=getIntent();
        int id=intent.getIntExtra("charId",1);
        name=findViewById(R.id.nameText);
        status=findViewById(R.id.statusText);
        specy=findViewById(R.id.specyText);
        gender=findViewById(R.id.genderText);
        origin=findViewById(R.id.originText);
        location=findViewById(R.id.locationText);
        episodes=findViewById(R.id.episodesText);
        createdAt=findViewById(R.id.createdText);
        imageView=findViewById(R.id.image);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this,MainActivity.class));
                finish();
            }
        });



        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getCharsInLoc(id).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                name.setText(response.body().getName());
                status.setText(response.body().getStatus());
                specy.setText(response.body().getSpecies());
                gender.setText(response.body().getGender());
                createdAt.setText(response.body().getCreated());
                Picasso.get().load(response.body().getImage()).into(imageView);
                origin.setText(response.body().getOrigin().getName());
                System.out.println(response.body().getEpisode().size());
                location.setText(response.body().getLocation().getName());
                for (int i=0;i<response.body().getEpisode().size();i++){
                    String s=response.body().getEpisode().get(i).substring(40);
                    episodes.append(s);
                    if (i<response.body().getEpisode().size()-1){
                        episodes.append(",");
                    }


                }

            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {

            }
        });


    }
}