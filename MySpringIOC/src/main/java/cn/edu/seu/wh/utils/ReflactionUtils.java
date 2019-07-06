/*
 * @Author: 王欢(HeartWh)
 * @Github:https://github.com/njustwh2014
 */

package cn.edu.seu.wh.utils;

import java.lang.reflect.Field;

/*
*ReflectionUtils 主要通过 Java 的反射原理来完成对象的依赖注入
* */
public class ReflactionUtils {
    public static void injectField(Field field,Object obj,Object value) throws IllegalAccessException{
        //Field 提供有关类或接口的单个字段的信息，以及对它的动态访问权限。反射的字段可能是一个类（静态）字段或实例字段。
        /*
        * Field是一个类,位于java.lang.reflect包下。在Java反射中Field类描述的是类的属性信息，功能包括：
            1.获取当前对象的成员变量的类型
            2.对成员变量重新设值
           * */
        if(field!=null){
            field.setAccessible(true);
            field.set(obj,value);
        }
    }
}
