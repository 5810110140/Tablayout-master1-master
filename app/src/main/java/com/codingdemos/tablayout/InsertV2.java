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

      private     String date,date2,date3;
      private     String time,time2,time3;

       public InsertV2(){}



    public
        InsertV2(String id_insert, String type1, String type2, String type3, String ivm1, String ivm2, String ivm3, String date,String date2, String date3, String time,String time2, String time3)
        {
            this.id_insert = id_insert;
            this.type1 = type1;
            this.type2 = type2;
            this.type3 = type3;
            this.ivm1 = ivm1;
            this.ivm2 = ivm2;
            this.ivm3 = ivm3;
            this.date = date;
            this.date2 = date2;
            this.date3 = date3;
            this.time = time;
            this.time2 = time2;
            this.time3 = time3;

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

    public String getDate2() {
        return date2;
    }

    public String getDate3() {
        return date3;
    }

    public String getTime() {
        return time;
    }

    public String getTime2() {
        return time2;
    }

    public String getTime3() {
        return time3;
    }
}


