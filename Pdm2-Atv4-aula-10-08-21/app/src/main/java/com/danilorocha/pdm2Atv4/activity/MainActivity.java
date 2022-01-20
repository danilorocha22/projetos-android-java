package com.danilorocha.pdm2Atv4.activity;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.danilorocha.pdm2Atv4.entity.Pojo;
import com.danilorocha.pdm2Atv4.R;
import com.danilorocha.pdm2Atv4.entity.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private ListView listView;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        new getContacts().execute();
    }//onCreate

    class getContacts extends AsyncTask<Void, Void, List<HashMap<String, String>>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "JSON Data is downloading", Toast.LENGTH_SHORT).show();
        }//onPreExecute

        @Override
        protected List<HashMap<String, String>> doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String url = "http://jsonplaceholder.typicode.com/posts/";
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject("{\"posts\":"+ jsonStr +"}");
                    List<HashMap<String, String>> postList = new ArrayList<>();
                    JSONArray posts = jsonObject.getJSONArray("posts");

                    for (int i = 0; i < posts.length(); i++) {
                        JSONObject p = posts.getJSONObject(i);
                        String userId = p.getString("userId");
                        String id = p.getString("id");
                        String title = p.getString("title");
                        String body = p.getString("body");

                        HashMap<String, String> post = new HashMap<>();
                        post.put("userId", userId);
                        post.put("id", id);
                        post.put("title", title);
                        post.put("body", body);
                        postList.add(post);
                    }
                    return  postList;
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: "+ e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog("Json parsing error: "+ e.getMessage());
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get Json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog("Couldn't get json from server. Check LogCat for possible errors");
                    }
                });
            }
            return null;
        }//doInBackground

        @Override
        protected void onPostExecute(List<HashMap<String, String>> result) {
            super.onPostExecute(result);

            try {
                if (result != null) {
                    for (int i = 0; i < result.size(); i++) {
                        Pojo pojo = new Pojo();
                        pojo.setUserId(result.get(i).get("userId"));
                        pojo.setId(result.get(i).get("id"));
                        pojo.setTitle(result.get(i).get("title"));
                        pojo.setBody(result.get(i).get("body"));
                        adapter.add(pojo);
                        adapter.notifyDataSetChanged();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//onPostExecute

    }//classe interna

    private void dialog(String msg) {
        new AlertDialog.Builder(this)
                .setTitle("Erro")
                .setMessage(msg)
                .setNeutralButton("Fechar", null)
                .show();
    }//alert dialog

}//classe