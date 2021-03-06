package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author leofee
 */
public class CustomServlet extends HttpServlet {

    /**
     * 重写父类的 {@code doGet} 方法
     *
     * @param req  request
     * @param resp response
     * @throws IOException exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("hello! this is my SPI servlet!");
    }
}
