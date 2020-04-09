package com.codingdemos.tablayout;

import com.jjoe64.graphview.series.DataPointInterface;

import java.io.Serializable;
import java.util.Date;

public class DataPoint  {

    private double x;
    private double y;

    public DataPoint() {

    }

    public DataPoint(double x,double y) {
        this.x = x;
        this.y = y;
    }

    public DataPoint(Date x,double y){
        this.x = x.getTime();
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{"+x+"/"+y+"}";
    }
}
