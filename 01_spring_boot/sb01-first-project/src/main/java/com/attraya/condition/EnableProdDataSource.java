package com.attraya.condition;


public class EnableProdDataSource implements DataSourceConfig{
    @Override
    public void makeConnection() {
        System.out.println("Connection established to prod data...");
    }
}
