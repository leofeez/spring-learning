package initializer.servlet;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author leofee
 */
public class DispatcherServletConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * {@code RootApplicationContext} 的相关配置类
     *
     * @return root applicationContext configuration class
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * {@code ServletApplicationContext} 的相关配置
     *
     * @return servlet applicationContext configuration class
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    /**
     * 设置 {@link DispatcherServlet} 拦截的 url
     *
     * @return 拦截的 url
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
