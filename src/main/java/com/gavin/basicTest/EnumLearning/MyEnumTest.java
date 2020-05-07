package com.gavin.basicTest.EnumLearning;

@SuppressWarnings("all")
enum MyEnumTest{
    Boy("男",1),
    Girl("女",2);
    private String name;
    private  int age;
    private  MyEnumTest(String name,int age){
        this.age=age;
        this.name = name;
    }
    private  MyEnumTest(){

    }
    public  String toString(){
        return  name+":"+age;
    }
}
