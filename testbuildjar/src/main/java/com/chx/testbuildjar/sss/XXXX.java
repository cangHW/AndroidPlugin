package com.chx.testbuildjar.sss;

import com.chx.testbuildjar.sss.DDDDD;

/**
 * @author: cangHX
 * on 2020/06/15  22:51
 */
public class XXXX {


    public static String xx(){
        StringBuilder builder = new StringBuilder();
        builder.append("sss");
        return aa(builder);


    }


    public static String aa(StringBuilder builder){

        DDDDD ddddd=new DDDDD();

        builder.append(System.currentTimeMillis());
        builder.append("qq").append(ddddd.QQ("qqqqqq"));

        return builder.toString();

    }

}
