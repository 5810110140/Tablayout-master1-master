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


    public
        InsertV2(String id_insert, String type1, String ivm1, String date, String time)
        {
            this.id_insert = id_insert;
            this.type1 = type1;

            this.ivm1 = ivm1;

            this.date = date;

            this.time = time;


        }


    public String getId_insert() {
        return id_insert;
    }

    public String getType1() {
        return type1;
    }


    public String getIvm1() {
        return ivm1;
    }




    public String getDate() {
        return date;
    }


    public String getTime() {
        return time;
    }



}


