package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println("contextInitialized......" + servletContext);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed......");
    }
}
