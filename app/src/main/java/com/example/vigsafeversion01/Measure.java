package com.example.vigsafeversion01;

import java.io.Serializable;

public class Measure implements Serializable {
        String productType;
        String temperature;
        String date;

        public Measure(String productType, String temperature, String date) {
            this.productType = productType;
            this.temperature = temperature;
            this.date = date;
        }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductType() {
        return productType;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getDate() {
        return date;
    }
}

