package com.washeed.Util;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileManager {
    private FileManager(){}
    public static ArrayList<Contact> contacts = new ArrayList<>();
    private final static String DATA_DIR= "data";
    private final static String CONTACT_FILE = "contacts.json";
    public static File initializeDirectory(Context context) {
        System.out.println("directory start");
        File directory = new File(context.getFilesDir(), DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory;
    }
    public static File initializeContactFile(Context context) {
        System.out.println("contactFile start");
        File directory = initializeDirectory(context);
        return new File(directory, CONTACT_FILE);
    }
    public static void saveFile(Context context, ArrayList<Contact> contacts) throws IOException {
        File file = initializeContactFile(context);
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(contacts, writer);
        }
    }
    public static ArrayList<Contact> loadFile(Context context) throws IOException {
        File file = initializeContactFile(context);
        if (!file.exists()) {return new ArrayList<>();}   //returns empty arraylist for no crash
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            Contact[] contacts = gson.fromJson(reader, Contact[].class);
            if (contacts == null) {return new ArrayList<>();}
            return new ArrayList<>(Arrays.asList(contacts));
        }
    }
}
