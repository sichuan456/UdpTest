package com.example.john.udptest;

/**
 * Created by John on 2016/7/22.
 */
public class EnumDemo {

    public static void main(String[] args) {
        printEnum(Grade.E);
    }

    public static  void printEnum(Grade grade){
        System.out.println(grade.getValue());
    }
}
