package controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

@Slf4j
@Controller
public class AsyncController {

    @ResponseBody
    @RequestMapping("/asyncHello")
    public Callable<String> hello() {
        System.out.println("主线程开始......" + Thread.currentThread().getName());

        return () -> {
            System.out.println("子线程开始......" + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("子线程结束......" + Thread.currentThread().getName());
            return "hello my async controller!";
        };
    }
}
