/*
 * @Author: 王欢(HeartWh)
 * @Github:https://github.com/njustwh2014
 */

package cn.edu.seu.wh.core;

import cn.edu.seu.wh.bean.BeanDefinition;
import cn.edu.seu.wh.bean.ConstructorArg;
import cn.edu.seu.wh.utils.BeanUtils;
import cn.edu.seu.wh.utils.ClassUtils;
import cn.edu.seu.wh.utils.ReflactionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BeanFactoryImpl implements BeanFactory {
    private static final ConcurrentHashMap<String,Object> beanMap=new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<>();
    /*
    * 在多线程程序中，应当尽量使用线程安全的集合。在集合的修改和查询过程中往往涉及到很多复杂的操作。
    * 比如set集合，在添加或删除元素时，需要对其中的树结构进行调整，一般需要在log(n)时间内才能完成，
    * 这样如果两个线程同时对同一个集合进行修改，就很可能造成这个集合的崩溃。
    * 可以使用读写锁来对集合的修改加以控制，但是这种控制往往是复杂的，并且低效。
    * 因此java提供了一些线程安全的集合类，在多线程程序中可以使用这些线程安全的集合以避免可能的不一致和崩溃现象。
    * */
    private static final Set<String> beanNameSet= Collections.synchronizedSet(new HashSet<>());//考虑多线程
    @Override
    public Object getBean(String name) throws Exception {
        //查找的对象是否已经实例化过
        Object bean=beanMap.get(name);
        if(bean!=null){
            return bean;
        }
        //如果没有实例化 则需要调用creatBean来实例化
        bean=creatBean(beanDefinitionMap.get(name));
        if(bean!=null){
            //对象创建后，将参数注入对象
            populateBean(bean);

            //再把对象放入map中
            beanMap.put(name,bean);
        }
        return bean;
    }

    /*
    * 容器初始化的时候，会调用 BeanFactoryImpl.registerBean 方法。把 对象的 BeanDefination 数据结构，存储起来。
    * */
    protected void registerBean(String beanName,BeanDefinition bd){
        beanDefinitionMap.put(beanName,bd);
        beanNameSet.add(beanName);
    }

    private Object creatBean(BeanDefinition beanDefinition) throws Exception{
        String beanName=beanDefinition.getClassName();
        Class clz= ClassUtils.loadClass(beanName);
        if(clz==null){
            throw new Exception("can not find Bean by Name");
        }
        List<ConstructorArg> constructorArgs=beanDefinition.getConstructorArgs();
        if(constructorArgs!=null&& !constructorArgs.isEmpty()){
            List<Object> objects=new ArrayList<>();
            //先注入构造依赖bean
            for(ConstructorArg constructorArg:constructorArgs){
                if (constructorArg.getValue() != null) {
                    objects.add(constructorArg.getValue());
                } else {
                    objects.add(getBean(constructorArg.getRef()));
                }
            }
            Class[] constructorArgTypes = objects.stream().map(it -> it.getClass()).collect(Collectors.toList()).toArray(new Class[]{});
            Constructor constructor = clz.getConstructor(constructorArgTypes);
            return BeanUtils.instanceByCglib(clz,constructor,objects.toArray());
        }else{
            return BeanUtils.instanceByCglib(clz,null,null);//该对象没有依赖
        }
    }

    /*
    * 对象创建成功以后，注入对象需要的参数
    * */
    private void populateBean(Object bean) throws Exception{
        Field[] fields=bean.getClass().getSuperclass().getDeclaredFields();
        if(fields!=null&&fields.length>0){
            for(Field field:fields){
                String beanName=field.getName();
                beanName= StringUtils.uncapitalize(beanName);
                if(beanNameSet.contains(field.getName())){//why field.getName() or beanName?
                    Object fieldBean=getBean(beanName);
                    if(fieldBean!=null){
                        ReflactionUtils.injectField(field,bean,fieldBean);
                    }

                }
            }
        }
    }

}
