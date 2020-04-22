package initializer.servlet;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import servlet.CustomServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 自定义 servlet 组件初始化的实现类
 *
 * @author leofee
 * @see AbstractAnnotationConfigDispatcherServletInitializer
 */
public class CustomServletInitializerApiImpl implements CustomServletInitializerApi {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // 注册自定义的servlet
        registerCustomServlet(servletContext);
    }


    /**
     * 注册自定义的 servlet
     */
    private void registerCustomServlet(ServletContext servletContext) {
        ServletRegistration.Dynamic servlet = servletContext.addServlet(
                CustomServlet.class.getName(), new CustomServlet());
        servlet.addMapping("*.do");
    }
}
