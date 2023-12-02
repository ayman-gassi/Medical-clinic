package com.example.cmedicale.storage;

public class Connect {
    private static FileConnect FileInstance = null;
    private static DbConnect DbInstance = null;

    public static FileConnect getFileConnectInstance() {
        if (FileInstance == null) {
            FileInstance = FileConnect.GetInstance();
        }
        return FileInstance;
    }

    public static DbConnect getDbConnectInstance() {
        if (DbInstance == null) {
            DbInstance = DbInstance.GetInstance();
        }
        return DbInstance;
    }
}
