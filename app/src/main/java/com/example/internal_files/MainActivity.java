package com.example.internal_files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private final String FILENAME = "inttext.txt";
    EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);

        et.setText(Read_text());

    }

    public void Write_text(String text){
        try {
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            bW.write(text);
            bW.close();
            oSW.close();
            fOS.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String Read_text(){
        FileInputStream fIS = null;
        try {
            fIS = openFileInput(FILENAME);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        InputStreamReader iSR = new InputStreamReader(fIS);
        BufferedReader bR = new BufferedReader(iSR);
        StringBuilder sB = new StringBuilder();
        String line = null;
        try {
            line = bR.readLine();
            while(line != null){
                sB.append(line+'\n');
                line = bR.readLine();
            }
            sB.append(et.getText());
            bR.close();
            iSR.close();
            fIS.close();
            return String.valueOf(sB);
    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void savePressed(View view) {
            // READ:
            String sB = Read_text();
            // WRITE:
            Write_text(String.valueOf(sB));
    }

    public void resetPressed(View view) {
        Write_text("");
    }

    public void exitPressed(View view) {
        // READ:
        String sB = Read_text();
        // WRITE:
        Write_text(String.valueOf(sB));
        finish();
    }
}