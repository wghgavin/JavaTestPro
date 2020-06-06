package com.gavin.basicTest.IOLearning;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesTest {
    public static void main(String[] args) {

    }

    private static void write() {
        Properties prop = new Properties();
        prop.setProperty("第一项", "1");
        prop.setProperty("第二项", "2");
        //Set<String> set = prop.stringPropertyNames();
        FileWriter fw = null;
        try {
            fw = new FileWriter("a.txt");
            prop.store(fw, "Save data");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read() {
        Properties p = new Properties();
        try {
            p.load(new FileReader("a.txt"));
            Set<Map.Entry<Object, Object>> entries = p.entrySet();
            Iterator<Map.Entry<Object, Object>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                var result = iterator.next();
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
