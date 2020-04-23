## Spring MVC

### Servlet 3.0 新特性
在以往的 web 项目中，第一步就需要配置 `web.xml` ，配置 `servlet` ， `filter`， `listener`，
但是在 servlet 3.0 中提供了新的更便捷的方式。

- 基于注解的方式可以实现无 xml 配置，如`@WebServlet`, `@WebFilter`, `@WebListener`。

    `@WebServlet`：只需要在自定义的xxxServlet类上加上该注解，然后配置好`urlPatterns`，就可以直接拦截到对应的请求。
    ```
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
    
- 通过 **Java-SPI** 的方式注册servlet组件：

    由于 `Java-SPI` 的本质是面向接口编程，这里的接口指的就是`javax.servlet.ServletContainerInitializer`。具体实现如下：

    1. 在项目路径下`resources`里新建一个`META-INF/services`的文件夹。
    2. 创建`ServletContainerInitializer`接口的实现类，并加上`@HandlesTypes`注解，
       注解的 `value` 即初始化 servlet 组件的实现类对应的接口，如`@HandlesTypes(CustomServletInitializerApi.class)`。
        ```
        @HandlesTypes(CustomServletInitializerApi.class)
        public class CustomServletContainerInitializer implements ServletContainerInitializer {
        
            /**
             * Tomcat 在启动时会自动调用该方法
             *
             * @param classes {@link HandlesTypes @HandlesTypes} 注解指定接口的实现类class
             */
            @Override
            public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
        
                // 实例化接口实现类
                Set<CustomServletInitializerApi> initializers = instantiateInitializers(classes);
        
                // 执行接口方法
                invokeInitializers(initializers, servletContext);
        
            }
            ......
        }
        ```
    3. 在该文件夹中新建一个文件，文件名称为接口名称即`javax.servlet.ServletContainerInitializer`，文件
       内容就是该接口的实现类的全限定名。
    4. 在 `xxxServletInitializerImpl`中利用 `ServletContext#addServlet` 动态注册 servlet 组件。
        ```java
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
                  // 动态添加 servlet 组件
                  ServletRegistration.Dynamic servlet = servletContext.addServlet(
                          CustomServlet.class.getName(), new CustomServlet());
                  servlet.addMapping("*.do");
              }
          }
        ```
    至此，自定义的 servlet 组件就注册完成，通过对应的访问规则就可以访问到注册的 servlet。
    
### DispatcherServlet
通过上述 SPI 的方式注册servlet，可以知道基于注解配置的SpringMVC中的 `DispatcherServlet` 就是通过 SPI 实现动态注册。

流程如下:
   
   1. `@HandlerTypes(WebApplicationInitializer.class)` 注解:  
   该注解指定接口之后，tomcat 在启动的时候就会去查找`WebApplicationInitializer`接口的所有实现类 class 并放到 
   `ServletContainerInitializer#onStartup(Set, ServletContext)` 方法参数的 set 集合中。
   
   2. 执行 `... onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext)`：  
   
      ```
       
        @Override
    	 public void onStartup(@Nullable Set<Class<?>> webAppInitializerClasses, ServletContext servletContext)
    			throws ServletException {

    		List<WebApplicationInitializer> initializers = new LinkedList<>();

    		if (webAppInitializerClasses != null) {
    		    // 遍历并实例化实现类
    		    for (Class<?> waiClass : webAppInitializerClasses) {
    		    
    		    // 过滤掉 接口，抽象类，然后必须实现 WebApplicationInitializer
    		    if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) && 
    		                            WebApplicationInitializer.class.isAssignableFrom(waiClass)) {
                   try {
                       // 反射生成实现类
                       initializers.add((WebApplicationInitializer)
                               ReflectionUtils.accessibleConstructor(waiClass).newInstance());
                       }
                       catch (Throwable ex) {
                           throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
                       }
                   }
               }
           }
           ......
           // 遍历执行 WebApplicationInitializer 的 onStartup 方法，
           // 如 SpringBootServletInitializer#onStartup...
           for (WebApplicationInitializer initializer : initializers) {
               initializer.onStartup(servletContext);
           }
    	}

      ```
   
   3. 执行 `AbstractDispatcherServletInitializer#onStartup(ServletContext servletContext)` 注册servlet组件:
       ```
            @Override
        	public void onStartup(ServletContext servletContext) throws ServletException {
        		super.onStartup(servletContext);
                // 注册 dispatcher servlet 组件
        		registerDispatcherServlet(servletContext);
        	}
       ```
至此，`DispatcherServlet` 就通过 SPI 的机制动态的注册完成。

后续使用`DispatcherServlet` 时，需要引入其他的配置，如拦截Mapping，可以直接继承`AbstractAnnotationConfigDispatcherServletInitializer`。

一共有三个必须实现的抽象方法：

- `protected Class<?>[] getRootConfigClasses();`: 该方法用于设置`RootApplicationContext`的配置类，
可以理解为包含`@Service`，`@Respository`，数据源等相关配置。

- `protected Class<?>[] getServletConfigClasses();`: 该方法用于设置`ServletApplicationContext`的配置类，
可以理解为包含`@Controller`，`ViewResolver`等相关配置。

- `protected String[] getServletMappings();`: 该方法用于设置`DispatcherServlet`拦截的url。
