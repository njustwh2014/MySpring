/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.bean;

import cn.edu.seu.wh.bean.BeanDefinition;
import lombok.Data;

import java.util.List;

@Data
public class AopBeanDefinition extends BeanDefinition {
    private String target;

    private List<String> interceptorNames;
}
