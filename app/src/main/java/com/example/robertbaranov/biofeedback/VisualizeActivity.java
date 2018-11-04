package com.example.robertbaranov.biofeedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }
}
