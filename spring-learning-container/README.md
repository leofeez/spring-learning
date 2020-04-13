# Spring 容器

## 1. `FactoryBean`
- FactoryBean，就是单个对象的工厂类，和普通的Bean不一样，该工厂类所持有的对象引用应该是`getObject()`方法实际创建并返回的Bean而不是它本身。
尽管Spring容器在启动时会以普通Bean创建的方式一样去创建FactoryBean。

- FactoryBean支持单例和多例，根据`isSingleton()`返回值来确定是否是单例，默认为true。

- FactoryBean生成的Bean，支持懒加载或者在容器启动阶段就同步创建Bean，如果需要同步创建Bean则可以选择实现 `SmartFactoryBean`，
该接口继承`FactoryBean`并提供是否支持同步创建Bean的实例`SmartFactoryBean#isEagerInit()`。

源码解析见：[(手把手玩转Spring 之 FactoryBean)](https://blog.csdn.net/Ecilipse/article/details/105408920)

## 2. `InitializingBean`
在Spring容器创建Bean的时候，我们可以通过自定义的方式去给Bean实例化之后做一些初始化的操作：
1. 利用`@Bean(initMethod="init")`中指定 initMethod。
2. 实现`InitializingBean`接口。

```
@Configuration
public class InitializingBeanConfig {
    /**
     * 实现了 InitializingBean
     */
    @Bean(name = "myInitializingBean")
    public MyInitializingBean getMyInitializingBean() {
        return new MyInitializingBean();
    }
    /**
     * 自定义 initMethod 方法
     */
    @Bean(name = "myCustomInitializingBean", initMethod = "init", destroyMethod = "destroy")
    public MyCustomInitializingBean getMyCustomInitializingBean() {
        return new MyCustomInitializingBean();
    }
}
```

## 3. `ApplicationContextAware`
`ApplicationContextAware` 是Spring容器提供用于初始化Bean的一个入口，通常情况下我们所有的Bean的生命周期都是交给Spring容器去管理，
如实例化，初始化，销毁等。

根据官方文档介绍，：
- 实现该接口之后，在容器启动时，BeanFactory 就是自动通知我们实现类去完成一系列操作。
- 实现该接口之后，我们可以在容器启动时，能够很便利的通过当前的`ApplicationContext`去获取到我们需要的Bean，Spring建议，如果是为了
设置类似于依赖Bean的话，Spring是不推荐实现该接口的，应该交给Spring去帮助我们加载依赖的Bean，如通过`@Autowired`的方式。
- Spring 还给我们提供了一个该接口的抽象实现类，其实我们可以直接继承该抽象实现类`ApplicationObjectSupport`，完成我们的想要的功能。

例如自定义我们的`ApplicationContextAware`:

```
@Component
public class MyApplicationContextAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("set my applicationContext !");
        this.applicationContext = applicationContext;

        // 自定义其他操作
        doSomethingMore();
    }

    /**
     * 只要持有了 {@link ApplicationContext} 我们就可以做一些其他的操作，
     * 如：通过 applicationContext.getBean() 获取到容器中的其他 Bean实例完成一系列的功能
     */
    protected void doSomethingMore() {
        Environment environment = this.applicationContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println("当前操作系统为: " + osName);
    }
}
```

在容器启动时就会执行`setApplicationContext`，然后执行`doSomethingMore`完成我们想扩展的功能。
源码解析见：[(手把手玩转Spring 之 ApplicationContextAware)](https://blog.csdn.net/Ecilipse/article/details/105437086)

## 4. `BeanPostProcessor`

