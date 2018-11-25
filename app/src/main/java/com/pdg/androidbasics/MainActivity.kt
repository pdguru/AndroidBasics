package com.pdg.androidbasics

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.ListAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.pdg.androidbasics.model.Item
import org.json.JSONArray
import org.json.JSONException

import java.util.ArrayList

class MainActivity : Activity() {

    internal lateinit var listView: ListView
    internal lateinit var requestQueue: RequestQueue

    internal var TAG = "ANDROIDBASICS"

    internal lateinit var values: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        requestQueue = Volley.newRequestQueue(this)

        fetchItemsFromURL()

        val adapter = com.pdg.androidbasics.viewcontroller.ListAdapter(this, values)
        listView.adapter = adapter
    }

    private fun fetchItemsFromURL() {
        Log.i(TAG, "Fetching from URL")

        values = ArrayList()

        val jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, "https://my.api.mockaroo.com/items.json?key=f2324050",
                null, Response.Listener { response ->
                    Log.i(TAG, "onResponse: Response received: " + response.length())
                    //                    Log.i(TAG, "Response received: " + response.toString(4));
                    try {
                        for (i in 0 until response.length()) {
                            val mItem = Item(
                                response.getJSONObject(i).getInt("id"),
                                response.getJSONObject(i).getString("name"),
                                response.getJSONObject(i).getString("subtitle"),
                                response.getJSONObject(i).getString("image"),
                                response.getJSONObject(i).getDouble("price").toFloat(),
                                response.getJSONObject(i).getString("description")
                            )

                            values.add(mItem)
                            //                        Log.i(TAG, i+":"+response.getJSONObject(i).getString("name") + " " + response.getJSONObject(i).getString("subtitle"));
                        }
                    } catch (jsonError: JSONException) {
                        Log.e(TAG, "Error parsing JSON: " + jsonError.message)
                        jsonError.printStackTrace()
                    }

                    Log.d(TAG, "onResponse: array size:" + values.size)
                    (listView.adapter as com.pdg.androidbasics.viewcontroller.ListAdapter).notifyDataSetChanged()
                }, Response.ErrorListener { error ->
                    Log.e("TAG", "Error retrieving JSON from URL: " + error.message)
                    error.printStackTrace()
                })

        requestQueue.add(jsonArrayRequest)
        Log.i(TAG, "Request sent.")
    }

}
