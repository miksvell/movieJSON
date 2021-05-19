package com.example.moviejson;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class InfoActivity extends AppCompatActivity {
    private static final String TAG = "infoActivity";
    private TextView title;
    private TextView description;
    private ImageView img;
    private TextView tickets;
    private TextView premiere;

    private MovieModelClass mInitialNote;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called");

        setContentView(R.layout.movie_info);
        title=findViewById(R.id.movieTitle);
        description=findViewById(R.id.movieDescription);
        img=findViewById(R.id.moviePoster);
        premiere=findViewById(R.id.moviePremiere);
        tickets=findViewById(R.id.movieTickets);

        if(getIncomingIntent()){
            setNoteProperties();
        }
        else{
            Log.d(TAG, "onCreate: FAILURE");
        }


        /*if(getIntent().hasExtra("selected_note")){
            MovieModelClass movieModelClass = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: "+ movieModelClass.toString());
        }*/
        
    }

    private boolean getIncomingIntent(){
        if(getIntent().hasExtra("selected_note")){
            mInitialNote = getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: "+ mInitialNote.toString());
            return true;
        }
        Log.d(TAG, "getIncomingIntent: FAILURE");
            return false;
    }

    private void setNoteProperties(){
        title.setText(mInitialNote.getName());
        Glide.with(this).load(mInitialNote.getImg()).into(img);
        description.setText(mInitialNote.getDescription());
        tickets.setText(mInitialNote.getTickets());
        premiere.setText(mInitialNote.getPremiere());
    }
}
