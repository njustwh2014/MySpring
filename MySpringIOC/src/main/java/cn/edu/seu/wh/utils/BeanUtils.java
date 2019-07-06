/*
 * @Author: 王欢(HeartWh)
 * @Github:https://github.com/njustwh2014
 */

package cn.edu.seu.wh.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/*
* BeanUtils 负责处理对象的实例化，这里我们使用了 cglib 这个工具包
* */
public class BeanUtils {
    public static <T>T instanceByCglib(Class<T> clz, Constructor ctr,Object[] args){
        /*
        * Enhancer允许为非接口类型创建一个Java代理。
        * Enhancer动态创建了给定类型的子类但是拦截了所有的方法。和Proxy不一样的是，不管是接口还是类他都能正常工作。
        * */
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(clz);//enhancer 会自动生产代理类的子类 并且所有非final的方法会自动生产callback钩子
        enhancer.setCallback(NoOp.INSTANCE);//设置回调接口 不太懂哦
        if(ctr==null){
            return (T)enhancer.create();
        }else{
            return (T)enhancer.create(ctr.getParameterTypes(),args);
        }
    }
}
