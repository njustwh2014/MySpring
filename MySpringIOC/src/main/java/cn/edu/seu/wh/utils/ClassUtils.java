/*
 * @Author: 王欢(HeartWh)
 * @Github:https://github.com/njustwh2014
 */

package cn.edu.seu.wh.utils;

/*
* ClassUtils 负责处理 Java 类的加载
* */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class loadClass(String className){
        try{
            return getDefaultClassLoader().loadClass(className);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
