/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.advisor;

import cn.edu.seu.wh.framework.aop.interceptor.AopMethodInterceptor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
/*
* 这个AdvisedSupport就是 我们Aop框架能够理解的数据结构，这个时候问题就变成了--对于哪个目标，增加哪些拦截器。
* */
@Data
public class AdvisedSupport extends Advisor{
    private TargetSource targetSource;
    private List<AopMethodInterceptor> aopMethodInterceptorList=new LinkedList<AopMethodInterceptor>();

    public void addAopMethodInterceptor(AopMethodInterceptor interceptor){
        aopMethodInterceptorList.add(interceptor);
    }

    public void addAopMethodInterceptors(List<AopMethodInterceptor> aopMethodInterceptors){
        aopMethodInterceptorList.addAll(aopMethodInterceptors);
    }
}
