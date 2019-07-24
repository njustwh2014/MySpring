/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.core;

import cn.edu.seu.wh.framework.aop.advisor.AdvisedSupport;
import cn.edu.seu.wh.utils.ClassUtils;
import lombok.Data;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

@Data
public class CglibAopProxy implements AopProxy {
    private AdvisedSupport advisedSupport;
    private Object[] constructorArgs;
    private Class<?>[] constructorArgTypes;

    public CglibAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return getProxy(null);
    }

    public Object getProxy(ClassLoader classLoader) {
        Class<?> rootClass=advisedSupport.getTargetSource().getTargetClass();

        if(classLoader==null){
            classLoader= ClassUtils.getDefaultClassLoader();
        }

        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(rootClass.getSuperclass());

        //增加拦截器核心方法
        Callback callbacks=getCallBack(advisedSupport);
        enhancer.setCallback(callbacks);
        enhancer.setClassLoader(classLoader);

        if(constructorArgs!=null && constructorArgs.length>0){
            return enhancer.create(constructorArgTypes,constructorArgs);
        }

        return enhancer.create();
    }

    private Callback getCallBack(AdvisedSupport advisedSupport){
        return new DynamicAdvisedInterceptor(advisedSupport.getAopMethodInterceptorList(),advisedSupport.getTargetSource());

    }
}
