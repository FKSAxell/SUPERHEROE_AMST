package com.example.superheroe_amst;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Iterator;

public class BusquedaActivity extends AppCompatActivity {
    String url;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        queue = Volley.newRequestQueue(this);
        url = getIntent().getExtras().getString("url");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {

            @SuppressLint("WrongViewCast")
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray arreglo = response.getJSONArray("results");
                    int cantidad=  arreglo.length();
                    LinearLayout capa = (LinearLayout)findViewById(R.id.Busqueda);
                    for(int i = 0; i < cantidad; i++){

                        JSONObject registro = (JSONObject) arreglo.get(i);

                        String nombre = registro.getString("name");
                        JSONObject stats = registro.getJSONObject("powerstats");
                        JSONObject bio = registro.getJSONObject("biography");
                        String nomCompleto=bio.getString("full-name");

                        TextView textHeroe = new TextView(getApplicationContext());
                        textHeroe.setText(nombre);
                        textHeroe.setClickable(true);
                        textHeroe.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                //try {

                                /*} catch (JSONException e) {
                                    //e.printStackTrace();
                                    i.putIntegerArrayListExtra("valueStats", new ArrayList<Integer>());
                                    i.putStringArrayListExtra("nameStats", new ArrayList<String>());
                                }*/

                                ArrayList<String> tags = new ArrayList<>();
                                ArrayList<Integer> valores = new ArrayList<>();
                                Iterator<String> iter1 = stats.keys();
                                while(iter1.hasNext()){
                                    String v= iter1.next();
                                    tags.add(v);
                                    try {
                                        valores.add(stats.getInt(v));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                                /*Intent grafico_activity = new Intent(getApplicationContext(), GraficoActivity.class);
                                grafico_activity.putIntegerArrayListExtra("valores", valores);
                                grafico_activity.putStringArrayListExtra("tags", tags);
                                grafico_activity.putExtra("nombre", nombre);
                                grafico_activity.putExtra("nombreCompleto", nomCompleto);

                                startActivity(grafico_activity);*/

                            }
                        });

                        textHeroe.setTextColor(Color.BLACK);
                        capa.addView(textHeroe);
                    }
                    ((TextView)findViewById(R.id.Contador)).setText("Resultado: " + cantidad );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                Toast.makeText(getApplicationContext(), "Nada que mostrar", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);
    }


}
