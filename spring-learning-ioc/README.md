# Spring 容器 IOC

IOC(Inversion of Control，控制反转): 在以往的项目中，Bean的创建都是由我们程序本身去实现这就是所谓的正转，而在Spring中，Bean的
生命周期都是交由Spring容器管理，包括创建和销毁，这就表现出对Bean的控制权的反转，所以了解Spring中Bean的生命周期就额外重要。

## Spring容器接口

Spring容器接口分为两种：

- ApplicationContext:ApplicationContext是Spring容器的上下文，可以理解为要想启动Spring容器，就必须实例化出一个具体的ApplicationContext的实现类，通常有两种：
  - ClassPathXmlApplicationContext: 基于xml文件配置的
  - AnnotationConfigApplicationContext: 基于Java注解形式配置的
- BeanFactory:Spring中所有的Bean的工厂，即Spring创建的Bean都是存放在BeanFactory中，Bean的获取和创建都是由BeanFactory来实现。

## Spring中Bean的生命周期

Spring中Bean的生命周期从宏观上看其实主要分为四步：

**Bean定义信息的解析 —>Bean定义信息的注册—>Bean 的实例化 —>Bean的初始化**

![image-20230424002723237](SpringContainer.png)

在整个Bean的生命周期中，整体的流程是ApplicationContext中主导，而具体的Bean的创建，是交由BeanFactory来主导，因为在ApplicationContext开始刷新时，会创建BeanFactory的具体实现类并将引用交由ApplicationContext持有，这样在ApplicaitonContext容器启动的过程中就可以将Bean的创建流程交由BeanFactory来主导，BeanFactory的最底层实现类为`DefaultListableBeanFactory`，它的类继承图如下：

![](DefaultListableBeanFactory.png)

## Spring中核心接口

以下几个核心接口在Spring容器启动以及Bean的生命周期中都扮演着重要的角色：

- BeanFactory：Spring 容器的顶层接口，定义了获取Bean的规范。
- ApplicationContext：Spring容器上下文，主要体现了Spring容器的中Bean的生命周期。
- Environment: 在容器启动前读取一些环境变量信息，比如`System.getenv()`。
- BeanDefinitionReader: 负责从配置文件解析BeanDefinition。
- BeanDefinition: 封装Bean的信息，如beanClass，Scope，LazyInit，factoryBeanName等。
- BeanDefinitionRegistry: 负责向Spring容器注册BeanDefinition。
- BeanDefinitionRegistryPostProcessor: 容器启动时，负责扫描需要注册到Spring容器的BeanDefinition。
- BeanFactoryPostProcessor：实现对BeanDefinition的后置处理（增强），典型的PlaceholderConfigurerSupport用于解析bean定义配置文件中的占位符。
- FactoryBean: 就是单个对象的工厂类，和普通的Bean不一样，该工厂类所持有的对象引用应该是`getObject()`方法实际创建并返回的Bean而不是它本身，主要是提供给用户自定义实例化Bean的扩展点。
- BeanPostProcessor: Bean实例的后置处理，如实现AOP增强
- Aware: 类似于一个回调接口，如果一个Bean实现了XXXAware接口，Spring容器实例化Bean之后会进行回调，通常作用是获取Spring容器的一些内置对象（Environment,ApplicationContext,BeaFactory），比如实现了ApplicationContextAware，Bean实例化之后就会通过setApplicationContext传入容器对象
- InitializingBean: Spring容器在启动创建Bean实例并且在Bean属性填充后的初始化回调。

## Spring容器启动流程

Spring 容器启动整体流程都在AbstractApplicationContext#refresh()方法中，主要流程如下：

1.prepareRefresh(): 容器刷新的准备工作，设置Spring 容器的开启状态标志，实例化StandardEnvironment对象，初始化 早期的ApplicationListener(这是一个扩展点，比如Spring boot 会在这个点注册一些监听器)

2.obtainFreshBeanFactory: 实例化DefaultListableBeanFactory，ResourceLoader读取xml配置文件，并通过BeanDefinitionReader解析成BeanDefinition装在到Spring容器。

3.prepareBeanFactory: 对BeanFactory做一些初始化工作，如设置ClassLoader,添加预制的BeanPostProcessor，注册Environment对象到Spring容器。

4.postProcessBeanFactory: 钩子函数，交由子类扩展实现，比如在SpringMVC中对BeanFactory做一些前置处理。

5.invokeBeanFactoryPostProcessors: 实例化并按顺序（PriorityOrdered优先，其次是Ordered，最后是没有实现排序接口的）执行BeanFactoryPostProcessor，对初步解析的BeanDefinition做一些增强，比如解析占位符。

6.registerBeanPostProcessors: 实例化并注册Bean初始化过程中的所需要的BeanPostProcessor。

7.initMessageSource: 准备国际化的资源

8.initApplicationEventMulticaster: 初始化事件的广播器

9.onRefresh: 钩子函数，扩展点

10.registerListeners: 注册监听器

11.finishBeanFactoryInitialization: 实例化所有的单例对象（Bean实例化，初始化核心流程）

12.finishRefresh：容器刷新完毕做一些收尾工作，比如发布容器刷新完毕事件ContextRefreshedEvent

下面就根据源码来具体看下Spring容器的启动详细步骤：

### 1. Spring容器启动解析配置文件

在使用基于xml方式配置Spring的时候，由于xml文件名或者配置内容支持占位符如`applicaitonContext-${env}.xml`，容器启动时需要解析占位符来定位到具体配置文件，
所以在容器启动时需要读取系统环境变量(包含vm options)封装到PropertySource添加到Environment中， 通过`PropertyResolver`根据加载完成的变量来解析配置文件上的占位符：

```java
public class ClassPathXmlApplicationContext {

    // 构造方法
    public ClassPathXmlApplicationContext(
            String[] configLocations, boolean refresh, @Nullable ApplicationContext parent)
            throws BeansException {
        super(parent);
        // 1.设置配置文件的位置
        setConfigLocations(configLocations);
        if (refresh) {
            refresh();
        }
    }

	public void setConfigLocations(@Nullable String... locations) {
		if (locations != null) {
			Assert.noNullElements(locations, "Config locations must not be null");
			this.configLocations = new String[locations.length];
			for (int i = 0; i < locations.length; i++) {
                // 2.解析配置文件的位置，文件名称可能存在占位符${}
				this.configLocations[i] = resolvePath(locations[i]).trim();
			}
		}
		else {
			this.configLocations = null;
		}
	}

	protected String resolvePath(String path) {
        // 3. 创建StandardEvironment，并在父类AbstractEnvironment构造中回调customizePropertySources
        // 读取System.getProperties()和System.getEnv()装载到Spring容器中的PropertySourceList中
        // 4. PropertySourcesPropertyResolver通过Environment中的变量来解析xml的配置文件名称（如果有${}占位符）
		return getEnvironment().resolveRequiredPlaceholders(path);
	}

}
```

#### 扩展点：自定义xml标签

1. 自定义标签的xsd文件
2. 自定义spring.schemas配置文件，用于将xml中的namespace和xsd做映射。
3. 自定义spring.handlers配置文件，用于指定自定义标签的handler实现类
4. 自定义NamespaceHandler，需继承NamespaceHandlerSupport
5. 自定义标签解析器，需继承于AbstractSingleBeanDefinitionParser，并重写getBeanClass（用于指定BeanDefinition的class）和doParse(Element element, BeanDefinitionBuilder builder) 用于解析每个属性值
6. 在自定义的NamepaceHandler的init方法中将标签属性和解析器BeanDefinitionParser一一映射

### 2. prepareRefresh()，容器刷新前置准备

这一步主要是做一些容器启动前的准备工作：

1. 设置容器启动active标识为true，closed关闭标记为false
2. 添加额外的PropertySource到容器的Environment中（扩展点），子类可以重写`initPropertySources()`添加自定义的PropertySource
3. 通过Environment#validateRequiredProperties() 校验必须的变量值是否存在，必须的变量值通过Environment#setRequiredProperties(String... requiredProperties)来指定。
4. 添加在容器启动刷新前的监听器Listener(比如SpringBoot中在容器启动前会执行Listener)

#### 扩展点：initPropertySources()添加自定义的变量值

子类重写该方法添加自定义的PropertySource后，这样在容器启动过程中或者容器启动后就可以通过容器对象中的`Environment`获取对应的属性值：

```java

/**
 * @author leofee
 */
public class MyApplicationContext extends AnnotationConfigApplicationContext {

    public MyApplicationContext(Class<?>... annotatedClasses) {
        super(annotatedClasses);
    }

    /**
     * 添加自定义的PropertySource
     */
    @Override
    protected void initPropertySources() {
        MutablePropertySources propertySources = getEnvironment().getPropertySources();
        Properties properties = new Properties();
        properties.setProperty("hello", "leofee's property!");
        propertySources.addLast(new PropertiesPropertySource("my_properties", properties));
    }

    public static void main(String[] args) {
        // 容器启动
        MyApplicationContext context = new MyApplicationContext(ApplicationContextAwareConfig.class);

        // 通过容器中的Environment就可以获取对应的value值
        String value = context.getEnvironment().getProperty("hello");
        System.out.println("initPropertySources 添加自定义属性 hello:" + value);
    }
}
```

### 3. obtainFreshBeanFactory()，创建BeanFactory实例

这一步会调用`AbstractApplicationContext#refreshBeanFactory()`方法，创建`DefaultListableBeanFactory`实例，并将BeanFactory的引用设置到ApplicationContext#beanFactory中，这样后续的Bean的创建就可以交由BeanFactory来主导，而不是ApplicationContext。

`refreshBeanFactory`为`AbstractApplicationContext`中的抽象方法，子类必须实现此方法才能执行实际的配置加载，针对基于XML方式配置和基于注解形式配置分别有不同的实现：

- 如果是基于Java注解形式配置（容器对象为AnnotationConfigApplicationContext）的Spring容器，在`GenericApplicationContext`中，只设置了BeanFactory的序列化ID值。
- 如果是基于Xml配置方式的Spring容器即实现为`AbstractRefreshableApplicationContext`，还会有额外的读取并解析对应的xml配置的过程。
  1. 创建BeanFactory并解析xml得到BeanDefinition注册到BeanFactory中
  2. XmlBeanDefinitionReader并持有DefaultListableBeanFactory的引用，通过AbstractBeanDefinitionReader将文件读取并封装成Resource对象，利用SAX读取xml文件流并生成Document，通过解析Document中的每一个Node最终封装成BeanDefinition注册到BeanFactory。

### 4. prepareBeanFactory(beanFactory)，初始化BeanFactory

AbstractApplicationContext#prepareRefresh

1. 创建并设置SPEL表达式解析器，StandardBeanExpressionResolver
2. 创建并设置默认的属性编辑器，ResourceEditorRegistrar
3. 注册ApplicationContextAwareProcessor，该PostProcessor作用为initializeBean时，进行ApplicationContextAware的设置。
4. 配置依赖注入忽略的接口ignoreDependencyInterface，如ApplicationContextAware

#### 扩展点：自定义属性编辑器，PropertyEditor

1. 定义属性编辑器继承PropertyEditorSupport并重写setAsText(String text)，增加自定义属性设置逻辑。
2. 定义编辑器的注册器，实现PropertyEditorRegistrar接口，并实现registerCustomEditors(PropertyEditorRegistry registry)，在该方法中通过registry将属性类型和属性编辑器实例进行绑定。
3. 在xml配置文件中，将自定义的PropertyEditorRegistrar注册到CustomEditorConfigurer中
4. CustomEditorConfigurer实现了BeanFactoryPostProcessor接口，在invokeBeanFactoryPostprocessors中会执行postProcessBeanFactory，将自定义的EditorRegistrar添加到BeanFactory中
5. 在Bean实例化时并封装到BeanWrapper时，通过自定义EditorRegistrar注册属性编辑器PropertyEditor
6. 最终在Bean属性填充阶段会调用PropertyEditor的setAsText(String text)

### 5. postProcessBeanFactory(beanFactory)，对BeanFactory后置处理

在BeanFactory初始化完成之后做一些后置处理，该方法为模板方法，默认是空实现，可交由子类进行重写扩展。

典型的实现，可以通过该方法中的beanFactory注册额外的BeanPostProcessor

```java
public class MyApplicationContext extends AnnotationConfigApplicationContext {

    public MyApplicationContext(Class<?>... annotatedClasses) {
        super(annotatedClasses);
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out.println("手工注册BeanPostProcessor前的数量：" + beanFactory.getBeanPostProcessorCount());

        // 手工注册 BeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyBeanPostProcessor());

        System.out.println("手工注册BeanPostProcessor前的数量：" + beanFactory.getBeanPostProcessorCount());
    }
}
```

### 6. invokeBeanFactoryPostProcessors(beanFactory)，注册并执行BeanFactoryPostProcessor

```
invokeBeanFactoryPostProcessors(beanFactory);
```

这一步是执行BeanFactory的后置处理器，主要分为两类：

- BeanDefinitionRegistryPostProcessor
- BeanFactoryPostProcessor

BeanDefinitionRegistryPostProcessor是BeanFactoryPostProcessor 的子接口，BeanDefinitionRegistryPostProcessor主要作用是注册额外的BeanDefinition，如ConfigurationClassPostProcessor就是对已经注册的BeanDefinition做进一步的解析，比如常用的配置注解（@ComponentScan，@Import），通过解析注解，注册更多的BeanDefinition到BeanFactory中。

BeanFactoryPostProcessor更多的是对已经加载完毕的BeanDefinition做一些额外的处理，比如对@Configuration的配置类进行增强（如：保证加了@Bean的方法的返回值对象永远是单例的）

两种后置处理器执行顺序不同：BeanDefinitionRegistryPostProcessor优先于BeanFactoryPostProcessor执行，并且分别还通过PriorityOrdered和Ordered进行优先级排序。

#### ConfigurationClassPostProcessor解析流程

在Spring当中如果仅仅利用基于xml方式进行配置，则会很繁琐，所以为了简便，Spring还提供了基于注解的形式来配置，ConfigurationClassPostProcessor就是用于解析带有注解的配置类（如@Configuration），然后加载一些额外的BeanDefinition，流程如下：

1. 确定具体需要解析的配置类，带有@Configuration为FULL类型的配置类，带有@Component,@ComponentScan@Import@ImportResource,或者带了@Bean注解的方法对应的类为LITE类型配置类。

2. 创建ConfigurationClassParser，开始对确定好的配置类进行循环解析。

   2.1 利用ConditionEvaluator判断是否跳过，如@Conditional注解来指定过滤规则

   2.2 判断ConfigurationClass是否是Imort进来的，如果是，则进行合并
   
3. 将配置类封装为SourceClass，开始递归进行解析配置类doProcessConfigurationClass

   3.1 解析@Component注解，判断是否有内部类也使用了@Component注解，如果有，则将内部类封装为ConfigurationClass进行递归解析。

   3.2 解析@PropertySource注解，加载对应的资源到Environment中的MutablePropertySources中。

   3.3 解析@ComponentScan注解，创建并利用ComponentScanAnnotationParser进行解析@ComponentScan注解元信息，如解析basePackages属性，来扫描指定的包路径下符合条件的配置类（默认规则为加了@Component注解的类，该规则在构造方法中默认指定，也可通过@ComponentScan的includeFilters和excludeFilters来自定义规则）

   3.4 解析@Import注解，如果Import进来的class也带有Import注解，则会递归进行收集Import进来的配置类，收集完Import进来的配置类后，会封装为SourceClass回到解析最初始的地方递归进行解析这些配置类。

   3.5 解析@ImportResource注解，将引入的资源名称进行占位符解析成真实的location后，添加到ImportedResources集合中，待后续步骤进行加载。

   3.6 解析@Bean注解，封装为BeanMethod添加到当前配置类中（这一步还未解析）。

   3.7 如果当前配置类还存在父类，则继续递归去解析父类上的注解。
   
4. 对基本的一些注解解析完成之后，接着对剩下的如@ImportResource进来的配置文件的中的Bean进行解析加载，对@Bean方法开始真正解析，对实现了ImportBeanDefinitionRegistrar的类，调用registerBeanDefinitions方法注册BeanDefinition（如AOP中的AnnotationAwareAspectJAutoProxyCreator就是在这一步注册进行来的）

   

   #### @Import注解解析

   @Import注解主要的作用是用来引入一些额外的配置类，主要分为以下几种：

   - @Configuration的配置类
   - @Component的普通类
   - 实现了ImportSelector的类
   - 实现了ImportBeanDefinitionRegistrar的类

   最后两种方式在Spring中运用比较广泛，比如@EnableXXX的各种注解，几乎都是配合@Import注解来引入对应的功能，像@EnableAspectJAutoProxy上就使用@Import，引入了AspectJAutoProxyRegistrar，在解析注解的过程中就会注册AnnotationAwareAspectJAutoProxyCreator，从而实现AOP的动态代理功能。

   除此之外，在Spring-boot中自动装配的机制EnableAutoConfiguration，也是基于@Import扩展实现的，引入了AutoConfigurationImportSelector（实现了ImportSelector），在解析该注解的过程中，通过SpringFactoriesLoader从META-INF/spring.factories配置文件中读取并加载自动装配的配置类。



### 7. registerBeanPostProcessors(beanFactory)，注册BeanPostProcessor

```java
// Register bean processors that intercept bean creation.
registerBeanPostProcessors(beanFactory);
```

注册BeanPostProcessor，Bean实例的后置处理，主要作用是在Spring创建完成Bean的实例之后，但是在初始化前后进行回调处理，BeanPostProcessor接口中定义了两个default方法如下：

```java
public interface BeanPostProcessor {

	// 在任何bean初始化回调(如InitializingBean的afterPropertiesSet或自定义初始化方法)之前，将此BeanPostProcessor应用于给定的新bean实例
	@Nullable
	default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	// 在任何bean初始化回调(如InitializingBean的afterPropertiesSet或自定义初始化方法)之后，将此BeanPostProcessor应用于给定的新bean实例
	@Nullable
	default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}
```

从方法签名可以很明显看出，一个是Before一个是After，由于BeanPostProcessor作用的时机是创建Bean实例，初始化前后，所以所有的BeanPostProssor就需要提前注册到容器中，然后统一的在Bean初始化的环节进行拦截处理。

#### 自定义BeanPostProcessor



### 8. initMessageSource()，初始化MessageSource

初始化国际化资源

### 9. 初始化事件广播器initApplicationEventMulticaster()

Spring中的事件发布机制采用的是观察者模式，与传统的观察者模式不同的点在于，传统的被观察者与观察者是紧耦合的，而Spring对传统的观察者模式中的角色做了进一步的细化，使观察者与被观察者之间的耦合度降低，主要分为以下几种角色：

- 事件广播器（通知观察者）：ApplicationEventMulticaster
- 监听器（观察者）：ApplicationListener
- 事件（通知）：ApplicationEvent
- 事件源（被观察者）：发布事件的触发点

Spring容器启动时，会默认初始化SimpleApplicationEventMulticaster广播器，并且在父类AbstractApplicationEventMulticaster.ListenerRetriever内部类中保存着当前容器中的所有监听器ApplicationListener，一旦通过容器ApplicationContext.publishEvent，则会根据当前事件类型去匹配监听器retrieveApplicationListeners，最终调用ApplicationListener#onApplicationEvent完成对监听器的触发。



### 10. onRefresh()，容器刷新自定义扩展

该方法为模板方法，提供给子类进行额外的扩展工作

### 11. registerListeners()，注册监听器

### 12. finishBeanFactoryInitialization(beanFactory)，实例化剩余的所有非懒加载的单例Bean

该方法含义是BeanFactory相关的初始化工作结束，开始实例化所有非懒加载的单例Bean，调用DefaultListableBeanFactory#preInstantiateSingletons，调用链如下：

1. 遍历在前置步骤中已经设置好的BeanDefinition。
2. 判断BeanDefinition是否是单例并且非懒加载，如果是，则调用AbstractBeanFactory#getBean(String beanName) -> AbstractBeanFactory#doGetBean
3. 在AbstractBeanFactory#doGetBean中，优先从DefaultSingletonBeanRegistry#getSingleton(String beanName)一级缓存中查找，如果能够查到，则直接返回，否则开始创建
4. 标记当前beanName对应的实例正在创建中， 通过markBeanAsCreated(beanName)将beanName添加到AbstractBeanFactory#alreadyCreated的集合中
5. 判断当前Bean是否存在父类，通过getMergedLocalBeanDefinition(beanName);将父类与子类的BeanDefinition进行合并。
6. 判断当前Bean是否存在依赖dependsOn，如果有，则先创建dependsOn对应的Bean，回到步骤2。
7. 创建当前Bean，DefaultSingletonBeanRegistry#getSingleton(beanName, objectFactory)，该方法内部还是会优先从一级缓存中查找，如果没有则调用第二个参数ObjectFactory.getObject()，ObjectFactory为函数式接口，这一步实际调用的是AbstractAutowireCapableBeanFactory#createBean(beanName, beanDefinition, args),
8. 解析当前BeanDefinition对应的Class，resolveBeanClass(mbd, beanName);
9. 判断当前BeanDefinition是否存在OverrideMethods，场景为：一个单例Bean引用了一个非单例Bean，为了保证每次获取单例Bean的同时，要求引用的非单例Bean都是最新的（不一样的），所以需要使用lookup-method，然后在后续实例化的时候会采用CGLIB动态代理来拦截对应的非单例Bean的获取方法。



### Spring中Bean创建实例的方式

- 由Spring使用反射机制创建，BeanUtil.instantiateClass,(如果对象的方法上有Lookup注解， 则会使用动态代理生成代理类)
2. 通过实现FactoryBean自定义创建Bean，FactoryBean#getObject()
3. 通过FactoryMethod自定义创建Bean，在xml中配置 factory-bean, factory-method，除此之外，基于注解配置，@Bean的方法也会被解析设置到BeanDefinition的factoryMethodName上
4. 通过Supplier自定义创建Bean，需在BeanDefinition中设置instanceSupplier属性，可以通过BeanFactoryPostProcessor中获取BeanDefiniton进行设置
5. 通过实现InstantiationAwareBeanPostProcessor来自定义创建Bean(通常采用动态代理)

### Spring 中 Bean实例化过程

Spring中默认创建Bean的方式为反射，而反射生成一个类的实例需要使用构造方法:

```java
Constructor ctor = Person.class.getContructors()[0];
ctor.newInstance("args");
```

而且，一个类的构造方法也是可以重载，即会存在多个参数列表不同的构造方法，所以Spring在通过反射创建类的实例之前就需要确定使用具体的构造方法：

- 第一次尝试利用SmartInstantiationAwareBeanPostProcessor来确定是否有匹配的构造方法（有且仅有一个参数的构造方法或者只有一个@Autowired的构造方法）
- 第二次，如果是基于xml定义的bean对象，支持`<constructor name="age" value="18"/>`标签，在解析成BeanDefinition的时候resolvedConstructorArguments就不为空，这时候通过标签指定的构造参数来确定对应的构造方法用于Bean的实例化。
- 第三次

Bean对象构造方法确定好之后会缓存到对应的BeanDefinition中的resolvedConstructorOrFactoryMethod，好处是对于prototype类型的Bean，后续再次创建对应的实例时就无需再次解析，直接通过缓存获取对应构造方法使用即可，提高效率。

## 1. `FactoryBean`

FactoryBean是Spring提供创建Bean的另外一个方式，通过实现该接口，可以创建自定的Bean（通常用于创建实例化过程比较复杂的对象），与Spring通过反射创建的Bean不同的是，由FactoryBean创建的Bean不会经历Spring中Bean的生命周期。

- FactoryBean，可以理解为单个对象的工厂类，和普通的Bean不一样，该工厂类所持有的对象引用是`getObject()`方法实际创建并返回的Bean而不是它本身。
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

根据官方文档介绍：

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
        System.out.println("当前操作系统为:" + osName);
    }
}
```

在容器启动时就会执行`setApplicationContext`，然后执行`doSomethingMore`完成我们想扩展的功能。

源码解析见：[(手把手玩转Spring 之 ApplicationContextAware)](https://blog.csdn.net/Ecilipse/article/details/105437086)

## 4. `BeanPostProcessor`

Spring Bean 创建的拦截器

## 5. `BeanFactoryPostProcessor`

Spring Bean Factory 拦截器
