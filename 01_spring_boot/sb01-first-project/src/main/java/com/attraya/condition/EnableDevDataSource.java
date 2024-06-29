package com.attraya.condition;


public class EnableDevDataSource implements DataSourceConfig{
    @Override
    public void makeConnection() {
        System.out.println("Connection established to Dev (default) database");
    }
}
