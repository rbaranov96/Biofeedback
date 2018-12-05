package com.example.robertbaranov.biofeedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.util.Log;

public class QuestionaireActivity extends AppCompatActivity {
    EditText ed1;
    String data1;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private String file = "history.csv";
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question1),
            new Question(R.string.question2),
            new Question(R.string.question3),

    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            } });
        updateQuestion();

        ed1=(EditText)findViewById(R.id.editText);

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data1=ed1.getText().toString();
                if (mCurrentIndex < 2 ) {
                    try {
                        FileOutputStream fOut = openFileOutput(file, MODE_APPEND);
                        fOut.write(data1.getBytes());
                        fOut.close();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();
                    ed1.setText("");
                }
                else {

                    try {
                        FileOutputStream fOut = openFileOutput(file, MODE_APPEND);
                        fOut.write(data1.getBytes());
                        fOut.close();
                        } catch (Exception e) {
                        // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(QuestionaireActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(),"Submitted",Toast.LENGTH_SHORT).show();
                        }
                    }
            });
        //updateQuestion();

    }
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }


}
