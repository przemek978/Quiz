package pl.edu.pb.wi;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private TextView counterTextView;
    private int Counter=0;
    private Question[] questions=new Question[]{
            new Question(R.string.q_sys,false),
            new Question(R.string.q_version,false),
            new Question(R.string.q_activity,true),
            new Question(R.string.q_open,false),
            new Question(R.string.q_five,true),
    };
    private int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        questionTextView=findViewById(R.id.question_text_view);
        counterTextView=findViewById(R.id.counter_text_view);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
                checkpoint();
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }

        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
                checkpoint();
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1)%questions.length;
                setNextQuestion();
            }
        });
        setNextQuestion();

    }

    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if (userAnswer == correctAnswer) {
            resultMessageId = R.string.correct_answer;
            Counter++;
        } else {
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }
    public void checkpoint()
    {
        if(((currentIndex + 1)%questions.length)==0){
            String a="Uzyskales "+Counter+ " pkt";
            Toast.makeText(getBaseContext(),a, Toast.LENGTH_SHORT).show();
            Counter=0;
        }
    }
    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
        counterTextView.setText(Counter+"pkt");
    }
}