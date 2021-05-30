package com.example.moviejson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adaptery.OnNoteListener {

    private static String JSON_Url = "http://10.0.2.2:8080/api/v1/employees";
    private static final String TAG = "MainActivity";

    List<MovieModelClass> movieList;
    RecyclerView recyclerView;

    @Override //JSON LINK : https://run.mocky.io/v3/1165ca69-62fb-432b-8082-1a660b4790c8
    //JSON 2 LINK : https://run.mocky.io/v3/d31f4730-5e91-4317-a42c-5cffbdb41928
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Program kina");
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);

        GetData getData = new GetData();
        getData.execute();
    }

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG, "onNoteClick: clicked" + position);

        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("selected_note",movieList.get(position));
        startActivity(intent);
    }

    public class GetData extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {

            String current ="";

            try{
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_Url);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while(data!=-1){

                        current += (char) data;
                        data = isr.read();

                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                //JSONObject jsonObject = new JSONObject(s);
                //JSONArray jsonArray = jsonObject.getJSONArray("moviz");
                JSONArray jsonArray = new JSONArray(s);

                for(int i = 0; i<jsonArray.length();i++){

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    MovieModelClass model = new MovieModelClass();
                    model.setId(jsonObject1.getString("id"));
                    model.setYear(jsonObject1.getString("year"));
                    model.setName(jsonObject1.getString("name"));
                    model.setImg(jsonObject1.getString("image"));
                    model.setDescription(jsonObject1.getString("description"));
                    model.setPremiere(jsonObject1.getString("premiere"));
                    model.setTickets(jsonObject1.getString("tickets"));

                    movieList.add(model);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(movieList);


        }
    }

    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList){
        Adaptery adaptery = new Adaptery(this,movieList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adaptery);
    }

}