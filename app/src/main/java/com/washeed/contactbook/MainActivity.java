package com.washeed.contactbook;

import static com.washeed.Util.FileManager.loadFile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.washeed.Util.Contact;
import com.washeed.Util.FileManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try {
            FileManager.contacts= loadFile(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileManager.saveFile(this, FileManager.contacts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //connecting adapter with contacts
        ListView contactListView = findViewById(R.id.contactListView);
        contactAdapter adapter = new contactAdapter(this, FileManager.contacts);   //custom adapter conntected to contact_list_item(UI design for 1 list)
        contactListView.setAdapter(adapter);
        //-------------------------------------
        //when clicked one contact
        contactListView.setOnItemClickListener(
                (parent, view, position, id)-> {
                    Contact selectedContact = FileManager.contacts.get(position);
                    Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                    intent.putExtra("name", selectedContact.getName());
                    intent.putExtra("number", selectedContact.getNumber());
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
        );


        //add new contact button
        Button AddContactButton = findViewById(R.id.addContactButton);
        AddContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntend = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(sendIntend);
            }
        });
    }

    protected  void onResume(){
        //refresh
        super.onResume();
        try {
            FileManager.contacts= loadFile(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ListView contactListView = findViewById(R.id.contactListView);
//        ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,FileManager.contacts);
        contactAdapter adapter = new contactAdapter(this, FileManager.contacts);   //custom adapter conntected to contact_list_item xml(UI design for 1 list)
        contactListView.setAdapter(adapter);
    }
}