/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.Invocation;

import java.lang.reflect.Method;

/*
* 用于描述方法的调用，注意是方法的调用，不是调用这个动作
* */
public interface MethodInvocation {
    Method getMethod();
    Object[] getArguments();
    Object proceed() throws Throwable;
}
