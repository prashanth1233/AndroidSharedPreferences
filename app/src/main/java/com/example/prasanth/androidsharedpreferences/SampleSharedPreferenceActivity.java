package com.example.prasanth.androidsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SampleSharedPreferenceActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String MyPREFERENCES = "myPrefs";
    private static final String NAME = "nameKey";
    private static final String LAST_NAME = "ltNameKey";
    private static final String AGE = "ageKey";
    private EditText fName;
    private EditText lName;
    private EditText age;
    private Button saveButton, btnShowDetails;
    private String firstName, lastName;
    private int age_user;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_shared_preference);


        fName = (EditText) findViewById(R.id.firstName);
        lName = (EditText) findViewById(R.id.lastName);
        age = (EditText) findViewById(R.id.age);
        saveButton = (Button) findViewById(R.id.saveBtn);
        btnShowDetails=(Button)findViewById(R.id.showBtn);
        fName.requestFocus();
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        saveButton.setOnClickListener(this);
        btnShowDetails.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.saveBtn:
                firstName = fName.getText().toString();
                lastName = lName.getText().toString();
                age_user = Integer.parseInt(age.getText().toString());
                if (firstName != "" && lastName != "" && age_user != 0) {
                    editor = sharedPreferences.edit();
                    editor.putString(NAME, firstName);
                    editor.putString(LAST_NAME, lastName);
                    editor.putInt(AGE, age_user);
                    editor.commit();
                    Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Please Fill All The Fields", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.showBtn:
                String rtvName = sharedPreferences.getString(NAME, "");
                Toast.makeText(this, "Retreived name is" + rtvName, Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
