/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.Invocation;

/*
* 代理方法的调用
* */
public interface ProxyMethodInvocation extends MethodInvocation{
    Object getProxy();
}
