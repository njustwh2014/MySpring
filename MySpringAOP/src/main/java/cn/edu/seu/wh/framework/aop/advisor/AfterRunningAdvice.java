/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.advisor;

import java.lang.reflect.Method;

public interface AfterRunningAdvice extends Advice {
    Object after(Object returnVal, Method method,Object[] args,Object target);
}
