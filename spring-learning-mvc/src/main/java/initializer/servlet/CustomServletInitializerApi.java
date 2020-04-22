package initializer.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 自定义 servlet 初始化的接口
 *
 * @author leofee
 * @see org.springframework.web.WebApplicationInitializer
 */
public interface CustomServletInitializerApi {

    /**
     * 容器启动时， 注册 servlet 组件
     *
     * @param servletContext servlet 上下文
     * @throws ServletException servletException
     */
    void onStartup(ServletContext servletContext) throws ServletException;
}
