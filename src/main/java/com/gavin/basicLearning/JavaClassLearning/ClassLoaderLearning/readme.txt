https://www.cnblogs.com/cl-rr/p/9081817.html
启动类加载器(BootstrapLoader)，由C++实现，没有父类。
拓展类加载器(ExtClassLoader)，由Java语言实现，父类加载器为BootstrapLoader
系统类加载器(AppClassLoader)，由Java语言实现，父类加载器为ExtClassLoader
自定义类加载器，父类加载器肯定为AppClassLoader。