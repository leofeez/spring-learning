
## Spring AOP

AOP(Aspect Oriented Programming): 面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。
利用AOP可以让某些不影响实际业务逻辑的功能横向抽取成通用的一个切面，实现解耦。例如Spring 的事务处理 `@Transactional`，
参数校验 `Hibernate validator`。而实现AOP的底层原理就是利用动态代理，在程序运行期间通过代理动态的将某些功能切入到指定指定位置，
详情见[动态代理原理](./src/main/java/com/leofee/proxy)

### 重要概念
 1. `Aspect`(切面)：Aspect 声明类似于 Java 中的类声明，在 Aspect 中会包含着一些 Pointcut 以及相应的 Advice。
 2. `Joint point`(连接点)：表示在程序中明确定义的点，典型的包括方法调用，对类成员的访问以及异常处理程序块的执行等等，它自身还可以嵌套其它 joint point。
 3. `Point Cut`(切入点)：定义了相应的 Advice 将要发生的地方，通常用 execution 进行匹配。
 4. `Advice`(增强)：Advice 定义了需要在目标上需要增加的功能，如 `before`，`after`，`returning`，`throwing`
 5. `Target`(目标)：织入  `Advice` 的目标。
 6. `Weaving`(织入)：将 `Advice` 和其他对象连接起来。

### 使用AOP
例如：这里我们需要在目标方法上增加日志输出，和入参和出参打印的功能，

如果采用传统方式我们需要利用在方法执行的第一行和最后一行都要利用log.info() 去打印，
如果很多方法都需要这样一个功能，那我们每个方法都要写这样重复的代码，但是，利用AOP，我们把这个功能作为一个切面，只需要定义好切入点即可。

1. pom 增加依赖

    ```
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.1.8.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>javax.inject</groupId>
        <artifactId>javax.inject</artifactId>
        <version>1</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aspects</artifactId>
        <version>5.1.8.RELEASE</version>
    </dependency>
    ```

2. 定义一个切面

    ```
    @Aspect
    public class LogAspect {
    
        @Pointcut("execution(* com.leofee.service.CalculatorService.div(..))")
        public void pointCut() {
        }
    
        @Before("pointCut()")
        public void logStart(JoinPoint joinPoint) {
            String methodName = joinPoint.getSignature().getName();
            joinPoint.getSignature();
            System.out.println("method: " + methodName + "准备运行, 参数列表为：{" + Arrays.asList(joinPoint.getArgs())+ "}");
        }
    
        @After("pointCut()")
        public void logEnd() {
            System.out.println("除法结束。。。。。。");
        }
    
        @AfterReturning(pointcut = "pointCut()", returning = "result")
        public void logReturn(Object result) {
            System.out.println("除法正常结束，返回值为：" + result);
        }
    
        @AfterThrowing(pointcut = "pointCut()", throwing = "exception")
        public void logException(Exception exception) {
            System.out.println("除法计算异常，异常信息为：" + exception);
        }
    
        @Around("pointCut()")
        public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
            System.out.println("环绕通知, 开始执行....");
            Object result = joinPoint.proceed();
            System.out.println("环绕通知, 开始结束....");
            return result;
        }
    }
    ```
    -  在类上增加注解，`@Aspect` 表明这个类是一个切面类。
    - `@pointCut()` 方法用于定位这个增强目标在哪里，关于 execution 的具体格式请自行百度噢。
    - `@Before` 表明在目标方法之前执行切入。
    - `@After` 表明在目标方法执行结束切入，但是在方法返回之前执行 `Advice`
    - `@AfterReturning` 表明在目标方法返回时切入。
    - `@AfterThrowing` 表明在目标方法出现异常时切入。

3. 开启AOP功能
    在配置类上增加 `@EnableAspectJAutoProxy`
    ```
    @ComponentScan(basePackages = "com.leofee.service")
    @EnableAspectJAutoProxy
    @Configuration
    public class AopConfig {
    
        @Bean
        public LogAspect getLogAspect() {
            return new LogAspect();
        }
    }
    ```