package com.example.superheroe_amst;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

public class GraficoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        //parametros de la anterior actividad
        Bundle parametros = getIntent().getExtras();
        String nombre = parametros.getString("nombre");
        String nombreCompleto = parametros.getString("nombreCompleto");
        ArrayList<String> tags = parametros.getStringArrayList("tags");
        ArrayList<Integer> valores = parametros.getIntegerArrayList("valores");
        int cantidad =tags.size();
        BarChart barChart = (BarChart) findViewById(R.id.barchart);
        barChart.fitScreen();
        XAxis x = barChart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawGridLines(false);
        x.setLabelRotationAngle(90);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.animateY(1500);
        barChart.getLegend().setEnabled(false);

        if(cantidad != 0) {
            String[] texto = new String[cantidad];

            for (int i = 0; i < cantidad; i++) {
                texto[i] = tags.get(i);
            }

            IndexAxisValueFormatter format = new IndexAxisValueFormatter();
            format.setValues(texto);
            x.setValueFormatter(format);
        }
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for(int i = 0; i<cantidad; i++){
            Integer val = valores.get(i);
            barEntries.add(new BarEntry(i, val));
        }
        if(cantidad != 0){
            BarDataSet barDataSet = new BarDataSet(barEntries, "Stats");
            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            barDataSet.setDrawValues(true);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(barDataSet);
            BarData data = new BarData(dataSets);
            barChart.setData(data);
            barChart.setFitBars(true);
        }
        ((TextView)findViewById(R.id.nombre)).setText(nombre);
        ((TextView)findViewById(R.id.nombreCompleto)).setText(nombreCompleto);

    }



}















