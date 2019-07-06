/*
 * @Author: 王欢(HeartWh)
 * @Github:https://github.com/njustwh2014
 */

package cn.edu.seu.wh.core;

import cn.edu.seu.wh.bean.BeanDefinition;
import cn.edu.seu.wh.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;

/*
* 我们所谓的容器，就是对BeanFactory的扩展，负责管理 BeanFactory。
* 我们的这个IoC 框架使用 Json 作为配置文件，所以我们容器就命名为 JsonApplicationContext。
* 当然之后你愿意实现 XML 作为配置文件的容器你就可以自己写一个 XmlApplicationContext，
* 如果基于注解的容器就可以叫AnnotationApplcationContext。这些实现留个大家去完成。
* */
public class JsonApplicationContext extends BeanFactoryImpl{
    private String fileName;

    public JsonApplicationContext(String fileName) {
        this.fileName = fileName;
    }
    public void init(){
        loadFile();
    }

    private void loadFile(){
        InputStream inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        //这句注意
        List<BeanDefinition> beanDefinitions= JsonUtils.readValue(inputStream, new TypeReference<List<BeanDefinition>>(){});
        if(beanDefinitions!=null&& !beanDefinitions.isEmpty()){
            for(BeanDefinition beanDefinition:beanDefinitions){
                registerBean(beanDefinition.getName(),beanDefinition);
            }
        }
    }
}
