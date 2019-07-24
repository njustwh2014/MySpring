/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.advisor;

import java.lang.reflect.Method;

public interface BeforeMethodAdvice extends Advice{
    void before(Method method,Object[] args,Object target);
}
