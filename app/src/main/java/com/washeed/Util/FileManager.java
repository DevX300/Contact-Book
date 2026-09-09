package com.washeed.Util;

import android.os.Build;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileManager {

    private FileManager(){}

    private final static String DATA_DIR= "data";

    public static void initializeDirectory(){
        Path folder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            folder = Path.of(DATA_DIR);
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Files.createDirectories(folder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final static String CONTACT_FILE = "data/contacts.ser";
    public static Path initializeContactFile(){
        Path file = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            file = Path.of(CONTACT_FILE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!Files.exists(file)) {
                try {
                    Files.createFile(file);
                } catch (IOException e) {
                    System.out.println("Did not create File:"+ e.getLocalizedMessage());
                }
            }
        }
        return file;
    }


    public static void saveFile(ArrayList<Contact> contacts) throws IOException {
        initializeDirectory();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ObjectOutputStream saveToFile =
                    new ObjectOutputStream( new FileOutputStream(initializeContactFile().toFile()));
            saveToFile.writeObject(contacts);
        }
    }

    public static ArrayList<Contact> loadFile() throws IOException, ClassNotFoundException {
        initializeDirectory();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ObjectInputStream contactFiles =
                    new ObjectInputStream( new FileInputStream(initializeContactFile().toFile()));
            Object obj = contactFiles.readObject();

        }
        return new ArrayList<>();


    }
}
