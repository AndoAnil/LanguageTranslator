package com.example.languagetranslator;

import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import static com.example.languagetranslator.MainActivity.translate;

public class TranslateOne {

    public static void Translate(int userone, int usertwo)
    {
        FirebaseTranslatorOptions options =
                new FirebaseTranslatorOptions.Builder()
                        .setSourceLanguage(userone)
                        .setTargetLanguage(usertwo)
                        .build();

        final FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance().getTranslator(options);
        translator.downloadModelIfNeeded()
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void v) {
                                // Model downloaded successfully. Okay to start translating.
                                // (Set a flag, unhide the translation UI, etc.)
                                final String text = MainActivity.userOne.getText().toString();
                                translate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startTranslate(translator,MainActivity.userOne.getText().toString());
                                    }
                                });

                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Model couldn’t be downloaded or other internal error.
                                // ...
                            }
                        });



    }


    //Translation process working here.
    private static void startTranslate(FirebaseTranslator translater, String text){


        translater.translate(text)
                .addOnSuccessListener(
                        new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(@NonNull String translatedText) {
                                // Translation successful.translatedText in hindi language - नमस्ते आप कैसे हैं

                                MainActivity.userTwo.setText(translatedText);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error.
                                // ...

                            }
                        });
    }

}
