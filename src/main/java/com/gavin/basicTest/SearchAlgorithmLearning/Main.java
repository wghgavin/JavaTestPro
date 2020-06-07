package com.gavin.basicTest.SearchAlgorithmLearning;
import com.gavin.basicTest.SearchAlgorithmLearning.Annotation.SearchMethod;
import org.reflections.Reflections;
import java.lang.reflect.Proxy;
import java.util.*;

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        int[] array = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(8000000);
        }
        System.out.println("正在生成有序数组...");
        Arrays.sort(array);
        System.out.println("已生成有序数组");
        System.out.println(Arrays.toString(array));
        System.out.println("请输入一个int类型数");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int des = Integer.parseInt(input);
        new Main().singleThreadLoopUp(des,array);
    }
    public void singleThreadLoopUp(int des,int[] array){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入查找算法方法编号:1.二分查找,2.插值查找");
            String input = sc.nextLine();
            if (!input.matches("[1-3]{1}")) {
                System.out.println("输入错误，请重新输入");
                continue;
            }
            int num = Integer.parseInt(input);
            ISearchMethod searchMethod = null;
            Reflections reflections = new Reflections("com.gavin.basicTest.SearchAlgorithmLearning.Impl");
            Set<Class<?>> classes = reflections.getTypesAnnotatedWith(SearchMethod.class);
            for (Class clazz : classes
            ) {
                SearchMethod field = (SearchMethod) clazz.getAnnotation(SearchMethod.class);
                if (field.id() == num) {
                    try {
                        searchMethod = (ISearchMethod) clazz.getDeclaredConstructor().newInstance();
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (searchMethod == null) {
                System.out.println("未找到对应的排序方法，请重新输入");
                continue;
            }
            List<Integer> result =lookUp(searchMethod,des,new int[]{1,2,3,4,5,6,7,8,9,11,11,11,12,13,14,15,16,45,65,88,94,122,455,6666,66647,4545225});
           // List<Integer> result =lookUp(searchMethod,des,array);
            if(result==null) System.out.println("未找到对应值");
            else System.out.println("找到对应值索引为:"+result);
        }

    }
    private List<Integer> lookUp(ISearchMethod searchMethod, int des, int[] array){
        ClassLoader loader = searchMethod.getClass().getClassLoader();
        Class<?>[] interfaces =searchMethod.getClass().getInterfaces();
        SearchProxyHandle handle = new SearchProxyHandle(searchMethod);
        ISearchMethod instance = (ISearchMethod)Proxy.newProxyInstance(loader,interfaces,handle);
        List<Integer> result = instance.lookup(array,des);
        return result;
    }
}
