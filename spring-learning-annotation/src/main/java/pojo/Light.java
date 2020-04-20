package pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class Light implements BeanNameAware, ApplicationContextAware, EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    public void setBeanName(String name) {
        System.out.println(getClass() + "当前 bean name 是 ：" + name);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("解析的后的结果为 ： " + resolver.resolveStringValue("${os.name}, #{3+5}"));
    }
}
