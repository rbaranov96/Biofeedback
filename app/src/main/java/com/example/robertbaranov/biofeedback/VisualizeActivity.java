package com.example.robertbaranov.biofeedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class VisualizeActivity extends AppCompatActivity {
    private Button mQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize);

        mQuestionButton = (Button)findViewById(R.id.question_button);
        mQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisualizeActivity.this, QuestionaireActivity.class);
                startActivity(intent);
            }
        });

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {new DataPoint(1,1)});
        graph.addSeries(series);
        series.appendData(new DataPoint(3,3),true,100);
        //for (int i=0;i<50;++i){
        //    series.appendData(new DataPoint(i,i),true,100);
        //}
    }
}
