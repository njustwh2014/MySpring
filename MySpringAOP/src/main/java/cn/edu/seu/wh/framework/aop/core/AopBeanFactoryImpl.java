/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.core;

import cn.edu.seu.wh.bean.BeanDefinition;
import cn.edu.seu.wh.core.BeanFactoryImpl;
import cn.edu.seu.wh.framework.aop.adapter.AfterRunningAdviceAdapter;
import cn.edu.seu.wh.framework.aop.adapter.BeforeMethodAdviceAdapter;
import cn.edu.seu.wh.framework.aop.advisor.*;
import cn.edu.seu.wh.framework.aop.bean.AopBeanDefinition;
import cn.edu.seu.wh.framework.aop.interceptor.AopMethodInterceptor;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class AopBeanFactoryImpl extends BeanFactoryImpl {

    private static final ConcurrentHashMap<String, AopBeanDefinition> aopBeanDefinitionMap=new ConcurrentHashMap<String, AopBeanDefinition>();

    private static final ConcurrentHashMap<String,Object> aopBeanMap=new ConcurrentHashMap<String, Object>();

    @Override
    public Object getBean(String name) throws Exception {
        Object aopBean=aopBeanMap.get(name);

        if(aopBean!=null){
            return aopBean;
        }

        if(aopBeanDefinitionMap.containsKey(name)){
            AopBeanDefinition aopBeanDefinition=aopBeanDefinitionMap.get(name);
            AdvisedSupport advisedSupport=getAdvisedSupport(aopBeanDefinition);
            aopBean=new CglibAopProxy(advisedSupport).getProxy();
            aopBeanMap.put(name,aopBean);
            return aopBean;
        }
        return super.getBean(name);
    }


    protected void registerBean(String beanName, AopBeanDefinition abd) {
        aopBeanDefinitionMap.put(beanName,abd);
    }

    private AdvisedSupport getAdvisedSupport(AopBeanDefinition aopBeanDefinition) throws Exception{
        AdvisedSupport advisedSupport=new AdvisedSupport();
        List<String> interceptorNames=aopBeanDefinition.getInterceptorNames();
        if(interceptorNames!=null&&!interceptorNames.isEmpty()){
            for(String interceptorName:interceptorNames){
                Advice advice=(Advice)getBean(interceptorName);
                Advisor advisor=new Advisor();
                advisor.setAdvice(advice);

                if(advice instanceof BeforeMethodAdvice){
                    AopMethodInterceptor interceptor= BeforeMethodAdviceAdapter.getInstants().getInterceptor(advisor);
                    advisedSupport.addAopMethodInterceptor(interceptor);
                }
                if(advice instanceof AfterRunningAdvice){
                    AopMethodInterceptor interceptor= AfterRunningAdviceAdapter.getInstants().getInterceptor(advisor);
                    advisedSupport.addAopMethodInterceptor(interceptor);
                }
            }
        }
        TargetSource targetSource=new TargetSource();
        Object object=getBean(aopBeanDefinition.getTarget());
        targetSource.setTargetClass(object.getClass());
        targetSource.setTargetObject(object);

        advisedSupport.setTargetSource(targetSource);

        return advisedSupport;

    }
}
