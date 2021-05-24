package com.example.moviejson;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InfoActivity extends AppCompatActivity {
    private static final String TAG = "infoActivity";
    private TextView title;
    private TextView description;
    private ImageView img;
    private TextView tickets;
    private TextView premiere;
    private RequestQueue mQueue;
    private String newTickets;

    private MovieModelClass mInitialNote;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(TAG, "onCreate: called");

        setContentView(R.layout.movie_info);
        title=findViewById(R.id.movieTitle);
        description=findViewById(R.id.movieDescription);
        img=findViewById(R.id.moviePoster);
        premiere=findViewById(R.id.moviePremiere);
        tickets=findViewById(R.id.movieTickets);
        Button reserve = findViewById(R.id.button);

        mQueue = Volley.newRequestQueue(this);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserveTickets();
            }
        });

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

    private void reserveTickets() {
        String url = "http://10.0.2.2:8080/api/v1/employees/1/4";
        Log.i(TAG, "reserveTickets: "+url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONArray jsonArray = response.getJSONArray("employees");
                            newTickets = response.getString("tickets");
                            tickets.setText(newTickets);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
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
        if(newTickets==null){
            tickets.setText(mInitialNote.getTickets());
        }
        else{
            tickets.setText(newTickets);
        }
        premiere.setText(mInitialNote.getPremiere());
    }
}
