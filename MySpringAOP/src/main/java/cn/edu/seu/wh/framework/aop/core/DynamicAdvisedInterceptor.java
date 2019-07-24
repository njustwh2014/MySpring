/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.core;

import cn.edu.seu.wh.framework.aop.Invocation.CglibMethodInvocation;
import cn.edu.seu.wh.framework.aop.Invocation.MethodInvocation;
import cn.edu.seu.wh.framework.aop.advisor.TargetSource;
import cn.edu.seu.wh.framework.aop.interceptor.AopMethodInterceptor;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

public class DynamicAdvisedInterceptor implements MethodInterceptor {
    protected final List<AopMethodInterceptor> interceptorList;
    protected final TargetSource targetSource;

    public DynamicAdvisedInterceptor(List<AopMethodInterceptor> interceptorList, TargetSource targetSource) {
        this.interceptorList = interceptorList;
        this.targetSource = targetSource;
    }

    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        MethodInvocation methodInvocation=new CglibMethodInvocation(o,targetSource.getTargetObject(),method,args,interceptorList,methodProxy);
        return methodInvocation.proceed();
    }
}
