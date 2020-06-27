package com.gavin.basicLearning.DataStuctureLearning.HashTabTest;

import java.util.Scanner;

/**
 * gOOGLE公司上机题
 * 一个公司，有新员工来报道时，将该员工信息加入,输入该员工的id，
 * 要求找到该员工的所有信息
 * 要求：不用数据库，尽量节省内存，速度越快越好->哈希表
 */
public class Main {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(10);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("add:增加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出");
            String input = sc.nextLine();
            switch (input){
                case "add":
                    System.out.println("请输入雇员信息:如{id,name}");
                    String empData = sc.nextLine();
                    String[] list = empData.split(",");
                    Emp emp = new Emp(Integer.parseInt(list[0]),list[1]);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.printList();
                    break;
                case "find":
                    System.out.println("请输入id：如{id}");
                    String id = sc.nextLine();
                    hashTab.findEmpByid(Integer.parseInt(id));
                    break;
                case "exit":
                    System.out.println("程序退出");
                    return;
                default:
                    System.out.println("请重新输入");
                    break;
            }
        }
    }
}
