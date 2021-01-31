package com.gavin.basicLearning.ReferenceLearning.HighSpeedBuffTest;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

/**
 * 高速缓存器
 * */
public class EmployeeCache {
    // 一个Cache实例
    static private EmployeeCache cache;
    // 用于Chche内容的存储
    private Hashtable<String, EmployeeRef> employeeRefs;
    // 垃圾Reference的队列
    private ReferenceQueue<Employee> queue;

    /**
     * 继承SoftReference，使得每一个实例都具有可识别的标识。
     * 并且该标识与其在HashMap内的key相同。
     */
    private class EmployeeRef extends SoftReference<Employee> {
        private String _key = "";

        public EmployeeRef(Employee em, ReferenceQueue<Employee> q) {
            super(em, q);
            _key = em.getId();
        }
    }

    // 构建一个缓存器实例
    private EmployeeCache() {
        employeeRefs = new Hashtable<>();
        queue = new ReferenceQueue<>();
    }

    // 取得缓存器实例
    public static EmployeeCache getInstance() {
        if (cache == null) {
            cache = new EmployeeCache();
        }
        return cache;
    }

    // 以软引用的方式对一个Employee对象的实例进行引用并保存该引用
    private void cacheEmployee(Employee em) {
        cleanCache();// 清除垃圾引用
        EmployeeRef ref = new EmployeeRef(em, queue);
        employeeRefs.put(em.getId(), ref);
    }

    /**
     * 依据所指定的ID号，重新获取相应Employee对象的实例
     */
    public Employee getEmployee(String ID) {
        Employee em = null;
        // 缓存中是否有该Employee实例的软引用，如果有，从软引用中取得。
        if (employeeRefs.containsKey(ID)) {
            EmployeeRef ref = (EmployeeRef) employeeRefs.get(ID);
            em = (Employee) ref.get();
        }
        // 如果没有软引用，或者从软引用中得到的实例是null，重新构建一个实例，
        // 并保存对这个新建实例的软引用
        if (em == null) {
            em = new Employee(ID);
            System.out.println("Retrieve From EmployeeInfoCenter. ID=" + ID);
            this.cacheEmployee(em);
        }
        return em;
    }

    // 清除那些所软引用的Employee对象已经被回收的EmployeeRef对象
    private void cleanCache() {
        EmployeeRef ref = null;
        while ((ref = (EmployeeRef) queue.poll()) != null) {
            employeeRefs.remove(ref._key);
        }
    }

    // 清除Cache内的全部内容
    public void clearCache() {
        cleanCache();
        employeeRefs.clear();
        //告诉垃圾收集器打算进行垃圾收集，而垃圾收集器进不进行收集是不确定的
        System.gc();
        //强制调用已经失去引用的对象的finalize方法
        System.runFinalization();
    }

}
