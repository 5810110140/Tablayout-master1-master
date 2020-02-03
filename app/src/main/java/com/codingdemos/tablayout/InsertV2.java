package com.codingdemos.tablayout;

import java.util.HashMap;

public class InsertV2 {


      private    String id_insert;
      private   String type1,type2,type3;
       // private String type2;
      //  private String type3;

     private    String ivm1, ivm2, ivm3;
        //private String ivm2;
       // private String ivm3;

      private     String date;
      private     String time;

       public InsertV2(){}

    public
        InsertV2(String id_insert, String type1, String type2, String type3, String ivm1, String ivm2, String ivm3, String date, String time)
        {
            this.id_insert = id_insert;
            this.type1 = type1;
            this.type2 = type2;
            this.type3 = type3;
            this.ivm1 = ivm1;
            this.ivm2 = ivm2;
            this.ivm3 = ivm3;
            this.date = date;
            this.time = time;
        }


    public String getId_insert() {
        return id_insert;
    }

    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public String getType3() {
        return type3;
    }

    public String getIvm1() {
        return ivm1;
    }

    public String getIvm2() {
        return ivm2;
    }

    public String getIvm3() {
        return ivm3;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}


