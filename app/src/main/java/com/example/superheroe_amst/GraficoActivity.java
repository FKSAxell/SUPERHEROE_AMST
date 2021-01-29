package com.example.superheroe_amst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;

public class GraficoActivity extends AppCompatActivity {
    String name;
    String fullName;
    ArrayList<String> nameStats;
    ArrayList<Integer> valueStats;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        name = getIntent().getExtras().getString("name");
        fullName = getIntent().getExtras().getString("fullName");
        nameStats = getIntent().getExtras().getStringArrayList("nameStats");
        valueStats = getIntent().getExtras().getIntegerArrayList("valueStats");


        barChart = (BarChart) findViewById(R.id.barchart);
        initBarchart();
        crearEntries();
        ((TextView)findViewById(R.id.nombre)).setText(name);
        ((TextView)findViewById(R.id.nombreCompleto)).setText(fullName);
    }


    private void initBarchart(){
        barChart.fitScreen();
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(90);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.animateY(1500);
        barChart.getLegend().setEnabled(false);

        if(nameStats.size() != 0) {
            String labels[] = new String[nameStats.size()];

            for (int i = 0; i < nameStats.size(); i++) {
                labels[i] = nameStats.get(i);
            }

            IndexAxisValueFormatter i = new IndexAxisValueFormatter();
            i.setValues(labels);
            xAxis.setValueFormatter(i);
        }

    }



    private void crearEntries(){
        System.out.println(valueStats);
        System.out.println(nameStats);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for(int i = 0; i<valueStats.size(); i++){
            String name = nameStats.get(i);
            Integer value = valueStats.get(i);
            BarEntry barEntry = new BarEntry(i, value);
            barEntries.add(barEntry);
        }
        if(barEntries.size() != 0){
            crearDataset(barEntries);
        }

    }
    private void crearDataset(ArrayList<BarEntry> barEntries){
        BarDataSet barDataSet = new BarDataSet(barEntries, "Stats Dataset");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(true);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);
        barChart.setData(data);
        barChart.setFitBars(true);
    }
}