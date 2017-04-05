package com.example.prasanth.androidsharedpreferences;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ShrdPrfnceIntnalStgActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FILENAME="MyFile.txt";
    private String data="My Name is Prashanth";
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;
    private Button saveFiletoInternalMem,getFiletoInternalMem;
    private byte[] byteData;
    private String getData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shrd_prfnce_intnal_stg);

        saveFiletoInternalMem=(Button)findViewById(R.id.storeData);
        getFiletoInternalMem=(Button)findViewById(R.id.retreiveData);

        saveFiletoInternalMem.setOnClickListener(this);
        getFiletoInternalMem.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.storeData:
                storeDataInFile();
                break;

            case R.id.retreiveData:
                getDataFromFile();
                break;
        }
    }

    public void storeDataInFile()
    {

        try {
            fileOutputStream=openFileOutput(FILENAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileOutputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this,"Data Saved To Internal Storage",Toast.LENGTH_SHORT).show();
    }

    public void getDataFromFile() {
        try {
            fileInputStream=openFileInput(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
             byteData= new byte[fileInputStream.available()];
            if(fileInputStream.read(byteData)!=-1)
            {
                getData=new String(byteData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,"Retreived Data is "+getData.toString(),Toast.LENGTH_LONG).show();
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

