/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.Invocation;

import cn.edu.seu.wh.framework.aop.interceptor.AopMethodInterceptor;
import cn.edu.seu.wh.framework.aop.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

/*
* 在实际的运用中，我们的方法很可能被多个方法的拦截器所增强。所以我们，使用了一个list来保存所有的拦截器。
* 所以我们需要递归的去增加拦截器。当处理完了所有的拦截器之后，才会真正调用调用被增强的方法。
* 我们可以认为，前文所述的动态的织入代码就发生在这里。
* */
public class ReflectionMethodInvocation implements ProxyMethodInvocation {
    protected final Object proxy;
    protected final Object target;
    protected final Method method;
    protected Object[] arguments=new Object[0];
    protected final List<AopMethodInterceptor> interceptorList;
    private int currentInterceptorIndex=-1;

    public ReflectionMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, List<AopMethodInterceptor> interceptorList) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.interceptorList = interceptorList;
    }

    public Object getProxy() {
        return proxy;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Object proceed() throws Throwable {
        // 执行完所有拦截器后，执行目标方法
        if(currentInterceptorIndex==interceptorList.size()-1){
            return invokeOriginal();
        }

        //迭代的执行拦截器
        AopMethodInterceptor aopMethodInterceptor=interceptorList.get(++currentInterceptorIndex);
        return aopMethodInterceptor.invoke(this);
    }

    protected Object invokeOriginal() throws Throwable{
        return ReflectionUtils.invokeMethodUseReflection(target,method,arguments);
    }
}
