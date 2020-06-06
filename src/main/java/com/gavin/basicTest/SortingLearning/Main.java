package com.gavin.basicTest.SortingLearning;

import com.gavin.basicTest.SortingLearning.Annotation.SortMethod;
import com.gavin.basicTest.SortingLearning.SortImpl.BubbleSorting;
import com.gavin.basicTest.SortingLearning.SortImpl.ChangeShellSorting;
import com.gavin.basicTest.SortingLearning.SortImpl.InsertSorting;
import com.gavin.basicTest.SortingLearning.SortImpl.SelectSorting;
import org.reflections.Reflections;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

//排序算法稳定性的描述https://blog.csdn.net/yangnianjinxin/article/details/77918882
public class Main {
    private PrintStream psConsole =new PrintStream(System.out);
    public static void main(String[] args) {
        new Main().singleThread();
    }

    /**
     * 单线程执行
     */
    public void singleThread() {
        System.setOut(psConsole);
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int[] array = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(8000000);
        }
        while (true) {
            System.out.println("请输入排序方法编号:1.冒泡排序,2.选择排序,3.插入排序,4.希尔排序(交换式)," +
                    "5.希尔排序(移动式),6.快速排序,7.归并排序,8.基数排序");
            String input = sc.nextLine();
            if (!input.matches("[1-8]{1}")) {
                System.out.println("输入错误，请重新输入");
                continue;
            }
            int num = Integer.parseInt(input);
            ISorting sortMethod = null;
            //使用反射获取实例
            Reflections reflections = new Reflections("com.gavin.basicTest.SortingLearning.SortImpl");
            Set<Class<?>> classes = reflections.getTypesAnnotatedWith(SortMethod.class);
            for (Class clazz : classes
            ) {
                SortMethod field = (SortMethod) clazz.getAnnotation(SortMethod.class);
                if (field.id() == num) {
                    try {
                        sortMethod = (ISorting) clazz.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (sortMethod == null) {
                System.out.println("未找到对应的排序方法，请重新输入");
                continue;
            }
            //startSorting(sortMethod, array);
            startSorting(sortMethod,new int[]{49, 38, 65, 97, 76, 13, 27, 50});
        }
    }

    /**
     * 多线程同时执行(使用log记录)
     */
    public void mutiThread() {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            Reflections reflections = new Reflections("com.gavin.basicTest.SortingLearning.SortImpl");

            Set<Class<?>> classes = reflections.getTypesAnnotatedWith(SortMethod.class);
            Random r = new Random();
            int[] array = new int[800000];
            for (int i = 0; i < array.length; i++) {
                array[i] = r.nextInt(800000);
            }
            System.out.println("排序开始执行,请在日志查看....");
            System.setOut(new PrintStream("./log.txt"));
            for (Class clazz : classes
            ) {
                SortMethod annotation =(SortMethod)(clazz.getAnnotation(SortMethod.class));
                if(annotation.id()>2){
                    ISorting sorting = (ISorting) clazz.getDeclaredConstructor().newInstance();
                    executorService.execute(() -> {
                        startSorting(sorting, array);
                    });
                }
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);//这里会同步等待
            System.setOut(psConsole);
            System.out.println("程序执行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startSorting(ISorting sortMethod, int[] array) {
        ClassLoader classLoader = sortMethod.getClass().getClassLoader();
        Class<?>[] interfaces = sortMethod.getClass().getInterfaces();
        SortingProxyHandle handle = new SortingProxyHandle(sortMethod);
        ISorting instance = (ISorting) Proxy.newProxyInstance(classLoader, interfaces, handle);
        int[] tempArray = new int[array.length];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        instance.sort(tempArray);
    }
}
