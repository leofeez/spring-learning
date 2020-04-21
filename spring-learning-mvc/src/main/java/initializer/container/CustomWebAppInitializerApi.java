package initializer.container;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 自定义的接口
 */
public interface CustomWebAppInitializerApi {

    void onStartup(ServletContext servletContext) throws ServletException;
}
