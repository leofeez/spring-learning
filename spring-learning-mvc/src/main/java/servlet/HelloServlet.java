package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过 {@link WebServlet @WebServlet} 自定义的 servlet 组件
 *
 * @author leofee
 */
@WebServlet(urlPatterns = "/hello/*")
public class HelloServlet extends HttpServlet {

    /**
     * 重写父类的 {@code doGet} 方法
     *
     * @param req  request
     * @param resp response
     * @throws IOException exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("hello! this is my servlet!");
    }
}
