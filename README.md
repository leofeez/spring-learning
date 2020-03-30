# spring-learning

## 简介
Spring是一个轻量级控制反转(IOC)和面向切面(AOP)的容器框架，它主要是为了解决企业应用开发的复杂性而诞生的。利用Spring我们可以很方便实现业务
逻辑层和其他层的松耦合。

## 理念
Spring 之所以能够如此受欢迎还是离不开它自身的设计理念，IOC(Inversion of Control，控制反转) 和 AOP(Aspect Oriented Programming，
面向切面编程)。

- IOC(Inversion of Control，控制反转): 在以往的项目中，Bean的创建都是由我们程序本身去实现这就是所谓的正转，而在Spring中，Bean的的
生命周期都是交由Spring容器管理，包括创建和销毁，这就表现出对Bean的控制权的反转。


- AOP(Aspect Oriented Programming，面向切面编程)：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。
利用AOP可以让某些不影响实际业务逻辑的功能横向抽取成通用的一个切面，实现解耦。例如Spring 的事务处理 `@Transactional`，参数校验
 `Hibernate validator`。而实现AOP的底层原理就是利用动态代理，在程序运行期间通过代理动态的将某些功能切入到指定指定位置。


## 优点

- 低侵入性，低耦合性
- IOC 可以很好实现各层之间的解耦
- AOP 可以很好的管理通用功能模块
- 集成其他框架非常便捷
- 简化开发

## 学习目录

- [spring 注解的使用](https://gitee.com/leofee/spring-learning/tree/master/spring-learning-annotation)

- [spring 容器](https://gitee.com/leofee/spring-learning/tree/master/spring-learning-container)

- [spring AOP 原理](https://gitee.com/leofee/spring-learning/tree/master/spring-learning-aop)

- [spring 之 java SPI](https://gitee.com/leofee/spring-learning/tree/master/spring-learning-spi)