/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.adapter;

import cn.edu.seu.wh.framework.aop.advisor.Advisor;
import cn.edu.seu.wh.framework.aop.advisor.BeforeMethodAdvice;
import cn.edu.seu.wh.framework.aop.interceptor.AopMethodInterceptor;
import cn.edu.seu.wh.framework.aop.interceptor.BeforeMethodAdviceInterceptor;

public class BeforeMethodAdviceAdapter implements AdviceAdpter {
    private BeforeMethodAdviceAdapter(){
        //不能在外部通过new BeforeMethodAdviceAdapter() 单例
    }

    private static final BeforeMethodAdviceAdapter INSTANTS=new BeforeMethodAdviceAdapter();

    public static  BeforeMethodAdviceAdapter getInstants(){
        return INSTANTS;
    }

    public AopMethodInterceptor getInterceptor(Advisor advisor) {
        BeforeMethodAdvice advice=(BeforeMethodAdvice)advisor.getAdvice();
        return new BeforeMethodAdviceInterceptor(advice);
    }
}
