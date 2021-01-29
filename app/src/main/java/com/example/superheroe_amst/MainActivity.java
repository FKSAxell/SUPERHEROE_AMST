package com.example.superheroe_amst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    String facebook_token = "3805865272807080";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buscarHeroe(View v){
        EditText text_input = (EditText)findViewById(R.id.editText);
        Intent busqueda_activity = new Intent(getApplicationContext(), BusquedaActivity.class);
        busqueda_activity.putExtra("api","https://www.superheroapi.com/api.php/" + facebook_token + "/search/" + text_input.getText().toString() );
        startActivity(busqueda_activity);
    }
}