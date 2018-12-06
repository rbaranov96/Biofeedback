package com.example.robertbaranov.biofeedback;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static com.example.robertbaranov.biofeedback.VisualizeActivity.styling;

public class ViewHistoryActivity extends AppCompatActivity{
    private Button mDoneButton;
    private TextView mDisplayHistory;
    private String mHistoryText;
    private static LineGraphSeries<DataPoint> series;
    private static GraphView graph;
    private static LineGraphSeries<DataPoint> series2;
    private static GraphView graph2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);

        mDisplayHistory = (TextView)findViewById(R.id.display_history);
        mDoneButton = (Button)findViewById(R.id.history_done_button);


        mHistoryText = "Panic Attack: ";
        mDisplayHistory.setText(mHistoryText);


        /* TODO: get String/JSON object from disk, replace this dummy data */
        mDisplayHistory.append("\n10 / 31 / 2018");
        mDisplayHistory.append("\n10:04pm");
        mDisplayHistory.append("\n3 minutes long");
        graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<>(new DataPoint[] {new DataPoint(0,60)});
        series.appendData(new DataPoint(10,75),true,2);
        series.appendData(new DataPoint(20,77),true,3);
        series.appendData(new DataPoint(30,73),true,4);
        series.appendData(new DataPoint(40,70),true,5);
        series.appendData(new DataPoint(50,69),true,6);
        series.appendData(new DataPoint(60,67),true,7);
        series.appendData(new DataPoint(70,65),true,8);
        series.appendData(new DataPoint(80,62),true,9);
        series.appendData(new DataPoint(90,60),true,10);
        series.appendData(new DataPoint(100,62),true,11);
        series.setColor(Color.RED);
        series.setThickness(16);
        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.setTitle("Heartrate over time");
        styling = graph.getViewport();
        styling.setMinX((double)0);
        styling.setMaxX((double)110);
        styling.setMinY((double)20);
        styling.setMaxY((double)120);


        graph2 = (GraphView) findViewById(R.id.graph2);
        series2 = new LineGraphSeries<>(new DataPoint[] {new DataPoint(0,9)});
        series2.appendData(new DataPoint(10,8),true,2);
        series2.appendData(new DataPoint(20,8),true,3);
        series2.appendData(new DataPoint(30,7),true,4);
        series2.appendData(new DataPoint(40,7),true,5);
        series2.appendData(new DataPoint(50,6),true,6);
        series2.appendData(new DataPoint(60,5),true,7);
        series2.appendData(new DataPoint(70,3),true,8);
        series2.appendData(new DataPoint(80,2),true,9);
        series2.appendData(new DataPoint(90,2),true,10);
        series2.appendData(new DataPoint(100,2),true,11);
        series2.setColor(Color.RED);
        series2.setThickness(16);
        graph2.addSeries(series2);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setYAxisBoundsManual(true);
        graph2.setTitle("Self-reported anxiety over time");
        styling = graph2.getViewport();
        styling.setMinX((double)0);
        styling.setMaxX((double)110);
        styling.setMinY((double)0);
        styling.setMaxY((double)10);

        mDisplayHistory.append("\n What were you thinking about before your panic attack? \n");
        mDisplayHistory.append(" I was worried about my project ");
        mDisplayHistory.append("\n What were you doing before your panic attack?\n");
        mDisplayHistory.append(" I was working on my project");
        mDisplayHistory.append("\n Where were you when your panic attack started?\n");
        mDisplayHistory.append(" Waterman ");

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewHistoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }




}
