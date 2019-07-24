/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.advisor;

import lombok.Data;

@Data
public class Advisor {
    //干什么
    private Advice advice;
    //在哪里
    private Pointcut pointcut;
}
