package com.example.prasanth.androidsharedpreferences;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalStorageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILENAME = "MyFile.txt";
    private static final String fileData = "My Name is Prashanth";
    private Button writeToExternalBtn, readFromExternalBtn;
    private Boolean media_available = false;
    private Boolean medial_writable = false;
    private String state, getFileData;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private byte[] byteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        writeToExternalBtn = (Button) findViewById(R.id.writeToExternalBtn);
        readFromExternalBtn = (Button) findViewById(R.id.readFromExternalBtn);

        state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            media_available = true;
            medial_writable = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            media_available = true;
            medial_writable = false;
        } else {
            media_available = false;
            medial_writable = false;
        }

        writeToExternalBtn.setOnClickListener(this);
        readFromExternalBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.writeToExternalBtn:

                writeToExternalStorage();
                break;
            case R.id.readFromExternalBtn:
                readFromExternalStorage();
                break;
        }
    }


    public void writeToExternalStorage() {
        try {
            fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "//" + FILENAME);
            Log.i("directory is" + Environment.getExternalStorageDirectory(), "hello");


            fileOutputStream.write(fileData.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Toast.makeText(this, "Data Stored To External Storage Successfully", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "data is" + Environment.getExternalStorageDirectory(), Toast.LENGTH_LONG).show();
    }

    public void readFromExternalStorage() {
        try {
            fileInputStream = openFileInput(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            byteData = new byte[fileInputStream.available()];
            if (fileInputStream.read(byteData) != -1) {
                getFileData = new String(byteData);
                Toast.makeText(this, "External Data Retreived is " + fileData, Toast.LENGTH_LONG).show();
                fileInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
