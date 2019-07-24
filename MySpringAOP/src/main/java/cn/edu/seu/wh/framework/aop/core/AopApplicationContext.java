/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

/*
 * @github: https://github.com/njustwh2014
 * @name: wanghuan
 */

package cn.edu.seu.wh.framework.aop.core;

import cn.edu.seu.wh.framework.aop.bean.AopBeanDefinition;
import cn.edu.seu.wh.bean.BeanDefinition;
import cn.edu.seu.wh.utils.ClassUtils;
import cn.edu.seu.wh.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;

public class AopApplicationContext extends AopBeanFactoryImpl {

    private String fileName;

    public AopApplicationContext(String fileName) {
        this.fileName = fileName;
    }
    public void init(){
        loadFile();
    }

    private void loadFile(){
        InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        List<AopBeanDefinition> beanDefinitions= JsonUtils.readValue(inputStream, new TypeReference<List<AopBeanDefinition>>() {});

        if(beanDefinitions!=null&&!beanDefinitions.isEmpty()){
            for(BeanDefinition beanDefinition:beanDefinitions){
                Class<?> clz= ClassUtils.loadClass(beanDefinition.getClassName());
                if(clz==ProxyFactoryBean.class){
                    registerBean(beanDefinition.getName(),(AopBeanDefinition) beanDefinition);
                }else{
                    registerBean(beanDefinition.getName(),(BeanDefinition)beanDefinition);
                }
            }
        }
    }
}
