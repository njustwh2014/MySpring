/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.test;

import cn.edu.seu.wh.framework.aop.advisor.BeforeMethodAdvice;

import java.lang.reflect.Method;

public class StartTimeBeforeMethod implements BeforeMethodAdvice {

    public void before(Method method, Object[] args, Object target) {
        long startTime=System.currentTimeMillis();
        System.out.println("开始计时");
        ThreadLocalUtils.set(startTime);
    }
}
