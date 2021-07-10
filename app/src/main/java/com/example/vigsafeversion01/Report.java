package com.example.vigsafeversion01;

import java.io.Serializable;
import java.util.ArrayList;

public class Report implements Serializable {
    ArrayList<Measure> measurements = new ArrayList<Measure>();

    public void addMeasure(Measure measure) {
        measurements.add(measure);
    }

    public ArrayList<Measure> getMeasurements() {
        return measurements;
    }
}
