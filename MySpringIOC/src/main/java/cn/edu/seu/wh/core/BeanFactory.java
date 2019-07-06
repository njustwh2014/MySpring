/*
 * @Author: 王欢(HeartWh)
 * @Github:https://github.com/njustwh2014
 */

package cn.edu.seu.wh.core;

public interface BeanFactory {
    /*
    * IOC框架目前只有ByName方法注入
    * */
    Object getBean(String name) throws Exception;
}
