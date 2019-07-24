/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.test;

import cn.edu.seu.wh.framework.aop.advisor.AfterRunningAdvice;

import java.lang.reflect.Method;

public class EndTimeAfterMethod implements AfterRunningAdvice {
    public Object after(Object returnVal, Method method, Object[] args, Object target) {
        long endTime=System.currentTimeMillis();
        long startTime= ThreadLocalUtils.get();
        ThreadLocalUtils.remove();
        System.out.println("方法耗时： "+(endTime-startTime)+" ms");
        return returnVal;
    }
}
