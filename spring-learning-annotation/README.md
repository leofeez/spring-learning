# Spring 注解使用与解析

## 1. @Configuration

在容器启动时告诉容器加了该注解的类是一个配置类，和我们利用`xml`的方式是同样的效果

## 2. @Bean

作用在方法上，表示该方法生成的 Bean 交由 Spring 容器管理，常用属性如下

- `name`：指定该 Bean 在Spring 容器中的名称，如果不指定，则Bean的名称默认为方法的名称
- `initMethod`: 指定该 Bean在实例化时的初始化方法
- `destroyMethod`：指定该 Bean 在容器关闭时的销毁该 Bean 的方法

## 3. @Autowired

用于 构造方法，setter方法，属性，Spring容器会帮助我们注入相关的依赖。介绍如下：

- `required`: 标识注入的依赖是否必须，默认为 true。
- 由于`@Autowired`的`required`属性默认为true，如果通过构造方法注入，那么有且只能有一个构造使用`@Autowired(required = true)`。
- 如果`@Autowired(required = false)`用于多个构造参数的情况下，Spring会优先选择参数最多的那个构造方法用于依赖注入。
- 如果在一个类中，只声明了一个构造方法，我们可以省略注解，Spring也会帮助我们自动注入依赖。
- 在多参数构造方法上，默认情况下`required`是适用于所有参数的，如果希望其中某个参数不需要强制检查，则可以使用`@Nullable`。

## 3. @ComponentScan

指定容器的扫描规则，常用属性如下：

- `value`和`basePackages`: 指定扫描包的路径
- `useDefaultFilters`: 是否使用默认的扫描规则（如 `@Component, @Controller @Service `等）
- `includeFilters`: 指定自定义的扫描规则，如下，如果设置`useDefaultFilters = false`，
  则默认的`@Component`注解的类不会被装载到容器内，而自定义的 includeFilters 指定的类以及指定注解的类则会被装载到容器内
- `lazyInit` : 是否延迟加载所扫描的Bean
- `basePackageClasses`：指定的class所在的包下所有的class都会被扫描

```java
@ComponentScan(basePackages = "anno.componentscan.controller",
        useDefaultFilters = false,
        includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {HelloController.class}),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {MyController.class})})
@Configuration
public class IncludeFilterConfig {
}
```

- `excludeFilters`: 指定那些类不需要被容器自动转载，如下，加了 `@Controller` 注解的类以及为`WorldService`类型都不会被容器扫描到

```java
@ComponentScan(basePackages = {"anno.componentscan.controller", "anno.componentscan.service"},
        useDefaultFilters = true,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WorldService.class})})
@Configuration
public class ExcludeFilterConfig {
}
```

- `ComponentScan.Filter` 通过自定义的`TypeFilter`也可以去指定哪些类被自动装载到容器内

```java
public class MyTypeFilter implements TypeFilter {
    /**
     * 通过实现接口的match方法实现自定义规则
     * 
     * @param metadataReader 读取到当前正在扫描类的信息
     * @param metadataReaderFactory 可以获取到其他任何类信息
     * @return true / false
     */
    @Override
    public boolean match(MetadataReader metadataReader,
                         MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类的注解
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 这里会把所有扫描到的类输出
        System.out.println("classMetadata : " + classMetadata.getClassName());
        // 获取当前类的资源(类的路径)
        Resource resource = metadataReader.getResource();
        System.out.println("resource:" + resource.getFilename());
        // 此处可以指定自己的规则
        return classMetadata.getClassName().contains("User");
    }
}
```

## 4. @Conditional

作用在配置类的方法上，用于限定@Bean的实例化，只有满足对应的条件，该方法才会实例化Bean并交由Spring管理。

- 自定义 condition

```java
/**
 * 实现{@link org.springframework.context.annotation.Condition}
 */
public class LinuxCondition implements Condition {
    /**
     * 根据该方法返回值用于确定是否执行Bean 的实例化
     * 
     * @param context 应用上下文的信息
     * @param metadata 注解的信息
     * @return true 则实例化Bean，否则 false
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 判读当前系统是不是 linux
        String osName = context.getEnvironment().getProperty("os.name");
        return osName != null && osName.contains("Linux");
    }
}
```

- 配置 `@Conditional`

```java
@Configuration
public class ConditionConfig {

    /**
     * 当前系统为linux才会实例化
     */
    @Conditional(value = {LinuxCondition.class})
    @Bean("linux")
    public Environment getLinux() {
        System.out.println("开始创建 linux ......");
        Environment linux = new Environment();
        linux.setName("linux");
        return linux;
    }
}
```

## 5. @Import

该注解是表示可以引入一个或多个配置类，类似于 xml 方式配置中的 `<import/>`，引入的配置类包括
`@Configuration`的配置类， 实现了`ImportSelector`或者`ImportBeanDefinitionRegistrar`接口的实现类。
在Spring中很多`@EnableXXX`的注解都是利用这种方式导入对应的配置，例如 Spring-aop 中的`@EnableAspectJAutoProxy`。
自定义的`@EnableMyAnnotation`，使用Import注解导入的类对应的Bean注册到Spring容器中对应的beanName为类的全限定名。

```java
@EnableMyAnnotation
@EnableAspectJAutoProxy
@Import({MyImportSelector.class})
@Configuration
public class ImportConfig {
}
```

## 6. @Lookup

如果一个单例Bean引用了一个非单例Bean，为了保证每次获取单例Bean的同时，要求引用的非单例Bean都是最新的（不一样的），所以需要在获取非单例Bean的方法上使用@Lookup注解，加了该注解之后，Spring容器会使用动态代理拦截该方法，从而保证每次都能重新创建非单例Bean

```java

@Component
public class School {

    Teacher teacher;

    // 使用Lookup 注解保证每次调用该方法都是新的Bean
    @Lookup
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

// 设置为原型模式
@Scope(SCOPE_PROTOTYPE)
@Component
public class Teacher {
}

// 测试
@Test
public void test() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(LookUpConfig.class);
    School school = applicationContext.getBean(School.class);
    System.out.println(school.getTeacher());
    School school2 = applicationContext.getBean(School.class);
    System.out.println(school2.getTeacher());
    // 结果为false，两次获取的teacher都是不一样的
    System.out.println(school2.getTeacher() == school.getTeacher());
}
```
