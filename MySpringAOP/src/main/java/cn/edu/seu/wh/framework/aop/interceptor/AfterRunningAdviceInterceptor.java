/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.interceptor;

import cn.edu.seu.wh.framework.aop.Invocation.MethodInvocation;
import cn.edu.seu.wh.framework.aop.advisor.AfterRunningAdvice;

public class AfterRunningAdviceInterceptor implements AopMethodInterceptor {
    private AfterRunningAdvice afterRunningAdvice;

    public AfterRunningAdviceInterceptor(AfterRunningAdvice afterRunningAdvice) {
        this.afterRunningAdvice = afterRunningAdvice;
    }

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object returnVal=methodInvocation.proceed();
        afterRunningAdvice.after(returnVal,methodInvocation.getMethod(),methodInvocation.getArguments(),methodInvocation);
        return returnVal;
    }
}
