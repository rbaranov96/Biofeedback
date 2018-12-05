package com.example.robertbaranov.biofeedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewHistoryActivity extends AppCompatActivity{
    private Button mDoneButton;
    private TextView mDisplayHistory;
    private String mHistoryText;

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

        mDisplayHistory.append("\n\nHeart rate measurements:\n\n[\n\n     Display graph for hr\n                                                                    ]\n\n");
        mDisplayHistory.append("\n\nAnxiety poll measurements:\n\n[\n\n     Display graph for anxiety poll\n                                                                       ]\n\n");


        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewHistoryActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

    }




}
