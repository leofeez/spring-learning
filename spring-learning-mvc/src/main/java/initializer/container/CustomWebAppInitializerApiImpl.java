package initializer.container;

import servlet.CustomServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class CustomWebAppInitializerApiImpl implements CustomWebAppInitializerApi {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // 注册自定义的servlet
        registerCustomServlet(servletContext);
    }


    /**
     * 向 WEB 容器中注册 {@code servlet}
     */
    private void registerCustomServlet(ServletContext servletContext) {
        ServletRegistration.Dynamic servlet = servletContext.addServlet(CustomServlet.class.getName(), new CustomServlet());
        servlet.addMapping("*.do");
    }
}
