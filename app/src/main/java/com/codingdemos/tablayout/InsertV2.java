package com.codingdemos.tablayout;

import java.util.HashMap;

public class InsertV2 {


      private    String id_insert;
      private   String type1;
       // private String type2;
      //  private String type3;

     private    String ivm1;
        //private String ivm2;
       // private String ivm3;

      private     String date;
      private     String time;

    public InsertV2(String id_insert, String type1, String ivm1, String date, String time) {
        this.id_insert = id_insert;
        this.type1 = type1;

        this.ivm1 = ivm1;

        this.date = date;

        this.time = time;

    }

    public InsertV2() {

    }


   /* public
        InsertV2(String id_insert, String type1, String ivm1, String date, String time)
        {
            this.id_insert = id_insert;
            this.type1 = type1;

            this.ivm1 = ivm1;

            this.date = date;

            this.time = time;


        }*/


    public String getId_insert() {
        return id_insert;
    }

    public String getType1() {
        return type1;
    }
    public void setType1(String type1){
        this.type1 = type1;
    }


    public String getIvm1() {
        return ivm1;
    }
    public void setTvm1(String Tvm1){
        this.ivm1 = Tvm1;
    }


    public String getDate() {
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }


    public String getTime() {
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }



}


