/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.advisor;

import lombok.Data;

@Data
public class TargetSource {
    private Class<?> targetClass;
    private Object targetObject;
}
