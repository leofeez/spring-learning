package handler;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import pojo.Parent;

/**
 * @author leofee
 */
public class MyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Parent.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        super.doParse(element, builder);

        String id = element.getAttribute("id");
        if (StringUtils.hasLength(id)) {
            builder.addPropertyValue("id", id);
        }

        String username = element.getAttribute("username");
        if (StringUtils.hasLength(id)) {
            builder.addPropertyValue("name", username);
        }

    }
}
