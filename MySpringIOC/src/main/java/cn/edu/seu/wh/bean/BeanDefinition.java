/*
 * @Author: 王欢(HeartWh)
 * @Github:https://github.com/njustwh2014
 */

package cn.edu.seu.wh.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;


/*
*BeanDefinition 是我们项目的核心数据结构。用于描述我们需要 IoC 框架管理的对象。
*包含了对象的 name，class的名称。如果是接口的实现，还有该对象实现的接口。
*以及构造函数的传参的列表 constructorArgs 和需要注入的参数列表 propertyArgs。
* */
@Data
@ToString
public class BeanDefinition {
    private String name;
    private String className;
    private String interfaceName;
    private List<ConstructorArg> constructorArgs;
    private List<PropertyArg> propertyArgs;

}
