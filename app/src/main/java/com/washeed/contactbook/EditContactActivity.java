package com.washeed.contactbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.washeed.Util.Contact;
import com.washeed.Util.FileManager;

import java.io.File;
import java.io.IOException;

public class EditContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView setNameText = findViewById(R.id.nameEdit);
        TextView setNumberText = findViewById(R.id.numberEdit);
        Intent getIntent = getIntent();
        String getName = getIntent.getStringExtra("name");
        String getNumber = getIntent.getStringExtra("number");
        int getPosition = getIntent.getIntExtra("position", -1);
        setNameText.setText(getName);
        setNumberText.setText(getNumber);

        //update
        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText getNameText = findViewById(R.id.nameEdit);
                EditText getNumberText = findViewById(R.id.numberEdit);
                String name = getNameText.getText().toString().trim();  //update the text from the field makes it into string
                String number = getNumberText.getText().toString().trim();
                if(name.isEmpty() && number.isEmpty()){
                    new MaterialAlertDialogBuilder(EditContactActivity.this)
                            .setTitle("Error")
                            .setMessage("Empty Field is invalid")
                            .setPositiveButton("Ok", null)
                            .show();
                }
                if (!name.isEmpty() && !number.isEmpty()){
                    if (getPosition!= -1){
                        Contact updateContact = FileManager.contacts.get(getPosition);   // stores the index of the object clicked
                        updateContact.setName(name);
                        updateContact.setNumber(number);    //updates the list
                        try {
                            FileManager.saveFile(EditContactActivity.this, FileManager.contacts);   // update and save
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(EditContactActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);  // clears the stack and makes sure the main activity is launched
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}