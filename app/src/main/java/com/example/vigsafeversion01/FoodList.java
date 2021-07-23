package com.example.vigsafeversion01;

import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodList {
    String productType, productDescription;
    Bitmap imageId;
    EditText temperature;

    public FoodList(Bitmap imageId, String productType, String productDescription) {
        this.productType = productType;
        this.productDescription = productDescription;
        this.imageId = imageId;
        //this.temperature = temperature;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Bitmap getImageId() {
        return imageId;
    }

    public void setImageId(Bitmap imageId) {
        this.imageId = imageId;
    }

    //public EditText getTemperature() {
        //return temperature;
    //}

    //public void setTemperature(EditText temperature) {
        //this.temperature = temperature;
    //}
}
