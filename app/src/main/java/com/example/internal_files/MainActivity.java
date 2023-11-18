package com.example.internal_files;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    /**
     * @author Roey Schwartz rs7532@bs.amalnet.k12.il
     * @version 1
     * @since 18.11.2023
     * this code will show an EditText and you can save to a file what you wrote, reset the content of the file and
       close the application with that saves the current content of the file
     */
    private final String FILENAME = "intText.txt";
    EditText et;
    TextView tv;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);

        tv.setText(Read_text());

    }

    /**
     * <p>
     *     the function get a text that the user wants to save in the file,
     *     the function will add that text to the current content in the file
     * </>
     */

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

    /**
     * <p>
     *     the function doesn't get an variable,
     *     the function will return a text type of String that including the current content in the file
     * </>
     */

    public String Read_text(){
        FileInputStream fIS = null;
        try {
            fIS = openFileInput(FILENAME);
        } catch (FileNotFoundException e) {
            Write_text("");
        }

        InputStreamReader iSR = new InputStreamReader(fIS);
        BufferedReader bR = new BufferedReader(iSR);
        StringBuilder sB = new StringBuilder();
        String line;
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
    } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>
     *     the function get a variable of View type,
     *     the function will save the text that the user wants to save and show it.
     * </>
     */
    public void savePressed(View view) {
        // READ:
        System.out.println("haven't got the massage!");
        String fileString = Read_text();
        System.out.println("got the massage!");
        // WRITE:
        Write_text(String.valueOf(fileString));
        tv.setText(fileString);
    }

    /**
     * <p>
     *     the function get a variable of View type,
     *     the function will delete the content of the file
     * </>
     */
    public void resetPressed(View view) {
        Write_text("");
        tv.setText("");
    }

    /**
     * <p>
     *     the function get a variable of View type,
     *     the function will save the content in the EditText to the file and the current content in the file and close the application
     * </>
     */
    public void exitPressed(View view) {
        // READ:
        String sB = Read_text();
        // WRITE:
        Write_text(String.valueOf(sB));
        finish();
    }

    /**
     * <p>
     *     the function get a variable of View type,
     *     the function will transfer the user to the credits screen
     * </>
     */
    public void creditsPressed(View view) {
        Intent si = new Intent(this, credits.class);
        startActivity(si);
    }
}