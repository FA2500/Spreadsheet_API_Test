package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataModalArrayList;
    private DataRVAdapter dataRVAdapter;
    private RecyclerView dataRV;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataModalArrayList = new ArrayList<>();
        dataRV = findViewById(R.id.idRVUsers);
        loadingPB = findViewById(R.id.idPBLoading);
        getDataFromAPI();
    }

    private void getDataFromAPI() {

        // creating a string variable for URL.
        // URL = ""https://sheets.googleapis.com/v4/spreadsheets/{SpreadSheet_ID}/values/{Nama_Tab}?alt=json&key={key_dari_Google_cloud_Console}";
        String url = "https://sheets.googleapis.com/v4/spreadsheets/1re03gNefcTI3eot1kt5uSXpiDHEjR5iz366TCOsvfZs/values/Sheet1?alt=json&key=AIzaSyBsoqWYUNS0opFVncXm8_kKHb3pfw1DKz4";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingPB.setVisibility(View.GONE);
                try {
                    JSONArray feedObj = response.getJSONArray("values");
                    JSONArray row = feedObj.getJSONArray(feedObj.length()-1);
                    String extra = "";
                    String datetime = row.getString(0);
                    String data = row.getString(1);
                    Log.d("SIZE", String.valueOf(row.length()));
                    if(row.length() > 2 && row.getString(2) != null)
                    {
                        extra = row.getString(2);
                    }
                    dataModalArrayList.add(new DataModel(datetime, data, extra));
                    dataRVAdapter = new DataRVAdapter(dataModalArrayList, MainActivity.this);
                    dataRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    dataRV.setAdapter(dataRVAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handling on error listener method.
                Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}