package com.example.languagetranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.content.DialogInterface;

import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     static EditText userOne;
     static EditText userTwo;
      static ImageButton translate;
      static ImageButton translateTwo;

     static ImageButton userOneVoice;
     static ImageButton userTwoVoice;
     static TextView userOneLang,userTwoLang;
     public  int userOneCode;
    public   int userTwoCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userOne=(EditText)findViewById(R.id.editTextTextMultiLine);
        userTwo=(EditText)findViewById(R.id.editTextTextMultiLine2);
        translate=(ImageButton)findViewById(R.id.translate);
        translateTwo=(ImageButton)findViewById(R.id.translateOne);
        userOneVoice=(ImageButton)findViewById(R.id.voiceUserOne);
        userTwoVoice=(ImageButton)findViewById(R.id.voiceUserTwo);
        userOneLang=(TextView)findViewById(R.id.selectUserOneLanguage);
        userTwoLang=(TextView)findViewById(R.id.selectUserTwoLanguage);

        userOneLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOnelang();
            }
        });

        userTwoLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTwolang();
            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateOne.Translate(userOneCode,userTwoCode);
            }
        });


        translateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateTwo translateTwo=new TranslateTwo();
                translateTwo.TranslateTwo(userTwoCode,userOneCode);
            }
        });



    }


    public void   userOnelang()
    {
        final int[] code = new int[1];
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Choose language Code");
        final List<String> lables = new ArrayList<>();
        for(int i=0;i<=58;i++)
        {
            String langCode= FirebaseTranslateLanguage.languageCodeForLanguage(i);
            lables.add(langCode.toUpperCase());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, lables);


        builder2.setAdapter(dataAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userOneCode =which;
                Toast.makeText(MainActivity.this,"You have selected " +lables.get(which),Toast.LENGTH_LONG).show();
                userOneLang.setText(lables.get(which));
            }
        });
        AlertDialog dialog = builder2.create();
        dialog.show();
    }


    public void   userTwolang()
    {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Choose language Code");
        final List<String> lables = new ArrayList<>();
        for(int i=0;i<=58;i++)
        {
            String langCode= FirebaseTranslateLanguage.languageCodeForLanguage(i);
            lables.add(langCode.toUpperCase());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, lables);


        builder2.setAdapter(dataAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userTwoCode =which;
                Toast.makeText(MainActivity.this,"You have selected " +lables.get(which),Toast.LENGTH_LONG).show();
                userTwoLang.setText(lables.get(which));
            }
        });
        AlertDialog dialog = builder2.create();
        dialog.show();
    }



}