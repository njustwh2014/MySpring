/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.interceptor;

import cn.edu.seu.wh.framework.aop.Invocation.MethodInvocation;

/*
* 方法拦截器，且AOP所有拦截器都要实现此接口
* */
public interface AopMethodInterceptor {
    Object invoke(MethodInvocation methodInvocation) throws Throwable;
}
