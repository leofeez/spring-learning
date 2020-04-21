## Spring MVC

### Servlet 3.0 新特性
在以往的 web 项目中，我们第一步就需要配置 `web.xml` ，配置 `servlet` ， `filter`， `listener`，
但是在 servlet 3.0 中提供了新的更便捷的方式。

- 基于注解的方式可以实现无 xml 配置，如`@WebServlet`, `@WebFilter`, `@WebListener`。

    `@WebServlet`：我们只需要在我们定义的xxxServlet类上加上该注解，然后配置好`urlPatterns`，就可以直接访问到。
    ```java
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
    ```
    关于`@WebFilter`, `@WebListener`同理。
    
- 通过 **Java-SPI** 的方式注册servlet容器：

    由于 `Java-SPI` 的本质是面向接口编程，所以这里的接口指的就是`javax.servlet.ServletContainerInitializer`。

    1. 第一步要求我们在项目路径下`resources`里新建一个`META-INF/services`的文件夹。
    2. 第二步我们创建自定义的 `Servlet` 组件初始化类，实现`ServletContainerInitializer`接口。
    3. 第二步在该文件夹中新建一个文件，文件名称为接口名称即`javax.servlet.ServletContainerInitializer`，文件
    内容就是该接口的实现类。
    4. 
    