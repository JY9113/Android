package edu.android.and64_jsonparser4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_FILE1 = "contact1.json";
    private static final String JSON_FILE2 = "contact2.json";

    private TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);

    }

    public void onClickWriteObj(View view) {
        // Contact 객체 생성
        Contact c = new Contact("안지영", "010-1111-1111");

        // Contact 객체를 JSON 객체로 변환
        Gson gson = new Gson();

        // toJson(object) : object를 JSON 문자열로 변환
        String json = gson.toJson(c);
        tv1.setText(json);

        // 생성된 JSON 문자열을 파일에 씀
        writeToFile(json, JSON_FILE1);
    }

    public void onClickReadObj(View view) {
        String jsonStr = readFromFile(JSON_FILE1);

        // JSON 문자열을 Contact 객체로 변환
        Gson gson = new Gson();
        Contact contact = gson.fromJson(jsonStr, Contact.class);

        // Contact 객체에서 데이터 추출
        tv2.setText(contact.toString());

    }



    public void onClickReadArray(View view) {

    }

    public void onClickWriteArray(View view) {

    }

    private void writeToFile(String str, String fileName) {
        OutputStream out = null;    // File Output Stream  파일을 열겠다
        OutputStreamWriter writer = null;   // 인코딩된 문자열을 쓰기 위해서
        BufferedWriter bw = null;

        try {
            out = openFileOutput(fileName, MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            bw = new BufferedWriter(writer);
            bw.write(str);  // 매개변수로 넘겨받은 string
            Toast.makeText(this, "파일 생성 성공", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String readFromFile(String fileName) {
        StringBuffer buffer = new StringBuffer();

        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;

        try {
            in = openFileInput(fileName);
            reader = new InputStreamReader(in);
            br = new BufferedReader(reader);

            String s = br.readLine();
            if (s != null) {
                buffer.append(s);
                s = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
