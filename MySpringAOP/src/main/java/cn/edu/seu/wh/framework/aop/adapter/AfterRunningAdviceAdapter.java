/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.adapter;

import cn.edu.seu.wh.framework.aop.advisor.Advisor;
import cn.edu.seu.wh.framework.aop.advisor.AfterRunningAdvice;
import cn.edu.seu.wh.framework.aop.interceptor.AfterRunningAdviceInterceptor;
import cn.edu.seu.wh.framework.aop.interceptor.AopMethodInterceptor;

public class AfterRunningAdviceAdapter implements AdviceAdpter {
    private AfterRunningAdviceAdapter(){

    }

    private static final AfterRunningAdviceAdapter INSTANTS=new AfterRunningAdviceAdapter();
    public static AfterRunningAdviceAdapter getInstants(){
        return INSTANTS;
    }
    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        AfterRunningAdvice advice=(AfterRunningAdvice)advisor.getAdvice();
        return new AfterRunningAdviceInterceptor(advice);
    }
}
