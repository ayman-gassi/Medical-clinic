package com.example.cmedicale.storage;

import java.io.*;

public class FileConnect <Type> {
    private static FileConnect instance = null;
    private FileConnect(){

    }
    public static FileConnect GetInstance(){
        if (instance == null) {
            return new FileConnect();
        }
        return instance;
    }
    public void SaveToFile(Type NewObject, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream obj = new ObjectOutputStream(fos);
        obj.writeObject(NewObject);
        obj.close();
        fos.close();
    }
    public Type ReadFromFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream obs = new ObjectInputStream(fis);
        Type Data = ( Type ) obs.readObject();
        fis.close();
        obs.close();
        return Data;
    }
}
