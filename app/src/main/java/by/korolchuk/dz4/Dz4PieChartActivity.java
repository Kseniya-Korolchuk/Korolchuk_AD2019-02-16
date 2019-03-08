package by.korolchuk.dz4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import by.korolchuk.R;

public class Dz4PieChartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz4_pie_chart);
        setTitle("Interactive Pie Chart");
        PieChartView pieChart = findViewById(R.id.PieChartView);
        float[] data = {450, 630, 300, 200, 400};
        pieChart.setData(data);
    }
}

