/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.test;

import cn.edu.seu.wh.framework.aop.core.AopApplicationContext;

public class MainTest {
    public static void main(String[] args) throws Exception {
        AopApplicationContext aopApplicationContext=new AopApplicationContext("application.json");
        aopApplicationContext.init();
        TestService testService=(TestService)aopApplicationContext.getBean("testServiceProxy");

        testService.testMethod();

    }
}
