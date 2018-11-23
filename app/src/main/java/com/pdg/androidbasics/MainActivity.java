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
import com.pdg.androidbasics.model.Item;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView listView;
    RequestQueue requestQueue;

    String TAG = "ANDROIDBASICS";

    ArrayList<Item> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        requestQueue = Volley.newRequestQueue(this);

        fetchItemsFromURL();

        ListAdapter adapter = new com.pdg.androidbasics.viewcontroller.ListAdapter(this, values);
        listView.setAdapter(adapter);
    }

    private void fetchItemsFromURL() {
        Log.i(TAG, "Fetching from URL");

        values = new ArrayList<Item>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://my.api.mockaroo.com/items.json?key=f2324050",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.i(TAG, "onResponse: Response received: " + response.length());
//                    Log.i(TAG, "Response received: " + response.toString(4));
                try {
                    for (int i = 0; i < response.length(); i++) {
                        Item mItem = new Item(response.getJSONObject(i).getInt("id"),
                                response.getJSONObject(i).getString("name"),
                                response.getJSONObject(i).getString("subtitle"),
                                response.getJSONObject(i).getString("image"),
                                (float) response.getJSONObject(i).getDouble("price"),
                                response.getJSONObject(i).getString("description"));

                        values.add(mItem);
//                        Log.i(TAG, i+":"+response.getJSONObject(i).getString("name") + " " + response.getJSONObject(i).getString("subtitle"));
                    }
                } catch (JSONException jsonError) {
                    Log.e(TAG, "Error parsing JSON: " + jsonError.getMessage());
                    jsonError.printStackTrace();
                }
                Log.d(TAG, "onResponse: array size:"+values.size());
                ((com.pdg.androidbasics.viewcontroller.ListAdapter)listView.getAdapter()).notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error retrieving JSON from URL: " + error.getMessage());
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonArrayRequest);
        Log.i(TAG, "Request sent.");
    }

}
