package edu.android.and03_quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 레이아웃 파일에 있는 위젯들을 멤버 변수로 선언
    private TextView textQuiz;
    private Button btnPrev;
    private Button btnNext;

    // 퀴즈 문제들
    private static final String[] QUIZ = {
            "대한민국의 수도는 서울입니다.",
            "중국의 수도는 북경입니다.",
            "미국의 수도는 뉴욕입니다.",
            "영국의 수도는 런던입니다.",
            "일본의 수도는 오사카입니다."
    };
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textQuiz = (TextView)findViewById(R.id.textQuiz);
        btnPrev = (Button)findViewById(R.id.btnPrev);
        btnNext = (Button)findViewById(R.id.btnNext);

        textQuiz.setText(QUIZ[index]);
        String first = "첫번째 문제입니다";

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index > 0) {
                    index--;
                    textQuiz.setText(QUIZ[index]);
                } else {
                    Toast.makeText(MainActivity.this, "첫번째 문제입니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index < QUIZ.length -1) {
                    index++;
                    textQuiz.setText(QUIZ[index]);

                } else {
                    Toast.makeText(MainActivity.this, "마지막 문제입니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
