package com.washeed.contactbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.washeed.Util.FileManager;

import java.io.IOException;

public class ContactDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView setNameText = findViewById(R.id.nameView);
        TextView setNumberText = findViewById(R.id.numberView);

        Intent getIntent = getIntent();
        String getName = getIntent.getStringExtra("name");
        String getNumber = getIntent.getStringExtra("number");
        int getPosition = getIntent.getIntExtra("position", -1);
        setNameText.setText(getName);
        setNumberText.setText(getNumber);


        //edit button functionality
        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendEditIntent = new Intent(ContactDetailActivity.this, EditContactActivity.class);
                sendEditIntent.putExtra("name", getName);
                sendEditIntent.putExtra("number", getNumber);
                sendEditIntent.putExtra("position", getPosition);
                startActivity(sendEditIntent);
            }
        });

        //remove button functionality
        Button removeButton = findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPosition != -1) {
                    FileManager.contacts.remove(getPosition);
                    try {
                        FileManager.saveFile(ContactDetailActivity.this, FileManager.contacts);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }
        });
    }
}