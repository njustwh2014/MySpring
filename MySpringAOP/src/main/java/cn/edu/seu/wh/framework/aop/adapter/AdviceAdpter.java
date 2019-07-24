/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.adapter;

import cn.edu.seu.wh.framework.aop.advisor.Advisor;
import cn.edu.seu.wh.framework.aop.interceptor.AopMethodInterceptor;

public interface AdviceAdpter {
    AopMethodInterceptor getInterceptor(Advisor advisor);
}
