package com.washeed.contactbook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.washeed.Util.Contact;
import com.washeed.Util.FileManager;

import java.io.IOException;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText getNameText = findViewById(R.id.nameAdd);
                EditText getNumberText = findViewById(R.id.numberAdd);

                String name = getNameText.getText().toString().trim();  //gets the text from the field makes it into string
                String number = getNumberText.getText().toString().trim();
                if(!name.isEmpty()&&!number.isEmpty()){
                    Contact addContact = new Contact(name, number);
                    FileManager.contacts.add(addContact);
                    try {
                        FileManager.saveFile(AddContactActivity.this, FileManager.contacts);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
                if(name.isEmpty() && number.isEmpty()){
//                    new Toast(AddContactActivity.this).setText("Hello");
                    new MaterialAlertDialogBuilder(AddContactActivity.this)
                            .setTitle("Error")
                            .setMessage("Empty Field is invalid")
                            .setPositiveButton("Ok", null)
                            .show();
                }
            }
        });
    }
}