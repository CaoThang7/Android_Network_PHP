package com.example.anroid_networking.Lab3;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetFashion extends AsyncTask<Void, Void, Void> {
    private String TAG = Lab3b1MainActivity.class.getSimpleName();

    public static String url = "http://192.168.0.101/fashions/index.php";

    ArrayList<Fashion> fashionList;
    private ProgressDialog pDialog; private ListView lv;
    Context context;
    FashionAdapter adapter;
    public GetFashion(Context context, ListView lv) {
        this.lv = lv;
        this.context = context;
        fashionList = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler handler = new HttpHandler();
        // making request to url and getting response
        String jsonStr = handler.makeServiceCall(url);
        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) { try {
            JSONObject jsonObject = new JSONObject(jsonStr);
// Getting JSON Array node
            JSONArray fashions = jsonObject.getJSONArray("fashions");
            // looping through all Contacts
            for (int i = 0; i < fashions.length(); i++) {
                JSONObject c = fashions.getJSONObject(i);
                String id = c.getString("id");
                String name = c.getString("name");
                String producttype = c.getString("producttype");
                String price = c.getString("price");
                String style = c.getString("style");
// Phone node is JSON Object
                JSONObject mystyle = c.getJSONObject("mystyle");
                String modern = mystyle.getString("modern");
                String vintage = mystyle.getString("vintage");
                Fashion fashion = new Fashion(); fashion.setId(id);
                fashion.setName(name);
                fashion.setProducttype(producttype);
                fashion.setPrice(price);
                fashion.setStyle(style);
                fashion.setModern(modern);
                fashionList.add(fashion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Json parsing error: " + e.getMessage()); }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
        adapter = new FashionAdapter(context, fashionList);
        lv.setAdapter(adapter);
    }
}
