# Spring 注解使用与解析

## 1. `@Configuration`
在容器启动时告诉容器加了该注解的类是一个配置类，和我们利用`xml`的方式是同样的效果

## 2. `@Bean` 
作用在方法上，表示该方法生成的 Bean 交由 Spring 容器管理，`@Bean`注解可以指定返回的bean的名称是什么
如果不指定，则Bean的名称默认为方法的名称

## 3. `@ComponentScan`
指定容器的扫描规则，该注解包含以下几个常用属性：
- `value`和`basePackages`: 指定扫描包的路径
- `useDefaultFilters`: 是否使用默认的扫描规则（如 `@Component, @Controller @Service `等）
- `includeFilters`: 指定自定义的扫描规则，如下，如果设置`useDefaultFilters = false`，
则默认的`@Component`注解的类不会被装载到容器内，而自定义的 includeFilters 指定的类以及指定注解的类则会被装载到容器内

```
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

```
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
```
public class MyTypeFilter implements TypeFilter {
    /**
     * 通过实现接口的match方法实现自定义规则
     * 
     * @param metadataReader 读取到当前正在扫描类的信息
     * @param metadataReaderFactory 可以获取到其他任何类信息
     * @return true / false
     */
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

