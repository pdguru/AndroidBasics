package com.pdg.androidbasics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView listView;
    RequestQueue requestQueue;

    String TAG = "TABLETRY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        requestQueue = Volley.newRequestQueue(this);

        ArrayList<String> values = new ArrayList<String>();
//        values.add("a"); values.add("b"); values.add("c"); values.add("d"); values.add("e");
//        values.add("f"); values.add("g"); values.add("h"); values.add("i"); values.add("j");

        fetchItemsFromURL(values);

        ListAdapter adapter = new com.pdg.tabletry.viewcontroller.ListAdapter(this, values);
        listView.setAdapter(adapter);
    }

    private void fetchItemsFromURL(final ArrayList<String> values) {
        Log.i(TAG, "Fetching from URL");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://my.api.mockaroo.com/items.json?key=f2324050",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, "onResponse: Response received.");
                for(int i=0; i<response.length(); i++) {
                    try {
                        values.add(response.getJSONObject(i).getString("name") + response.getJSONObject(i).getString("subtitle"));
                    }catch (JSONException jsonError){
                        Log.e(TAG, "Error parsing JSON: "+jsonError.getMessage());
                        jsonError.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error retrieving JSON from URL: "+error.getMessage());
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonArrayRequest);
        Log.i(TAG, "Request sent.");
    }


}
