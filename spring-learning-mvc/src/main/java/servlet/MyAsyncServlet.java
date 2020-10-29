package servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/async/*", asyncSupported = true)
public class MyAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("主线程开始......" + Thread.currentThread().getName());
        AsyncContext asyncContext = req.startAsync();

        asyncContext.start(() -> {
            log.info("新线程开启......" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}

            asyncContext.complete();
            try {
                asyncContext.getResponse().getWriter().write("sub thread!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("新线程结束......" + Thread.currentThread().getName());
        });
        resp.getWriter().write("hello async servlet!");
        log.info("主线程结束......" + Thread.currentThread().getName());
    }
}
