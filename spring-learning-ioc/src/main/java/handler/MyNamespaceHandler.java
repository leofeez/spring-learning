package handler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author leofee
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("world", new MyBeanDefinitionParser());
    }
}
