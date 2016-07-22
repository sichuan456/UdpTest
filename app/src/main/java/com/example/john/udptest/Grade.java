package com.example.john.udptest;

/**
 * Created by John on 2016/7/22.
 */
public enum Grade {

//    A,B,C,D,E;

//    A("100-90"),B("89-80"),C("79-70"),D("69-60"),E("59-0");
//    private String value;
//    private Grade(String score){
//        this.value = score;
//    }
//
//    public String getValue(){
//        return this.value;
//    }

    A("100-90"){
        @Override
        public String getLevel() {
            return "优";
        }
    },B("89-80"){
        @Override
        public String getLevel() {
            return "良";
        }
    },C("79-70"){
        @Override
        public String getLevel() {
            return null;
        }
    },D("69-60"){
        @Override
        public String getLevel() {
            return null;
        }
    },E("59-0"){
        @Override
        public String getLevel() {
            return null;
        }
    };
    private String value;
    private Grade(String score){
        this.value = score;
    }

    public String getValue(){
        return this.value;
    }

    public abstract String getLevel();

}
