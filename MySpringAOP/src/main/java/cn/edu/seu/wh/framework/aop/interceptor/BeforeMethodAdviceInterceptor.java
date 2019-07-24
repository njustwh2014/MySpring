/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.interceptor;

import cn.edu.seu.wh.framework.aop.Invocation.MethodInvocation;
import cn.edu.seu.wh.framework.aop.advisor.BeforeMethodAdvice;

public class BeforeMethodAdviceInterceptor implements AopMethodInterceptor{
    private BeforeMethodAdvice advice;
    public BeforeMethodAdviceInterceptor(BeforeMethodAdvice advice){
        this.advice=advice;
    }

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        advice.before(methodInvocation.getMethod(),methodInvocation.getArguments(),methodInvocation);
        return methodInvocation.proceed();
    }
}
