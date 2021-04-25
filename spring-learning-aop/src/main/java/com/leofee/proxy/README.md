## 代理设计模式
代理设计模式就是对目标方法的执行提供了代理，在代理对象中才去真正的执行目标方法，在代理对象中我们可以增加自己的一些功能。
代理设计模式的目的就是对目标方法的增强，在不修改原来设计的情况下，对方法行为进行增强。

代理设计模式的具体实现表现为三种：

- 静态代理
- JDK动态代理
- Cglig动态代理

比如有这样一个场景：
> 在执行一个方法前和执行之后打印系统的当前时间。

接口如下：

```java
/** 接口*/
public interface Hallo {

    /**
     * hello
     */
    void hello();
}
/** 实现类 */
public class HalloImpl implements Hallo {

    @Override
    public void hello() {
        System.out.println("hello world!");
    }
}
```

## 静态代理

静态代理的规则就是代理类为了**保持行为的一致性**需要和具体的实现类需要实现同样的接口`interface Hello`，代理类实例化的时候需要传入目标类的实例，在代理类的方法中才去真正执行目标方法。

静态代理类：

```java
/**
 * 代理类
 * @author leofee
 * @date 2020/11/29
 */
public class StaticProxyHandler implements Hallo {

    /** 真正的目标类实例*/
    private final Hallo target;

    public StaticProxyHandler(Hallo target) {
        this.target = target;
    }

    /**
     * 实现同样接口方法，在这里对目标方法进行增强
     */
    @Override
    public void hello() {
        System.out.println("static jdk proxy before say hello! time now is " + LocalDateTime.now());

        // 调用目标方法
        target.hello();

        System.out.println("static jdk proxy after say hello!" + LocalDateTime.now());
    }
}

public class ProxyTest {

    @Test
    public void testStaticProxy() {
        // 目标类实例
        Hallo target = new HalloImpl();
        // 获取代理类对象
        StaticProxyHandler handler = new StaticProxyHandler(target);
        // 通过代理对象调用目标方法即可实现增强
        handler.hello();
    }
}
```

静态代理的代理类是在编译期就已经生成了.class 。

静态代理的缺点：

- 一个代理类只能代理一个目标类，如果需要为多个目标类进行代理，则势必需要创建多个代理类的实例。

## JDK动态代理

动态代理和静态代理的区别：

- 动态代理的代理类.class文件是在程序运行期间通过反射机制动态生成。
- 动态代理会代理接口下的所有实现类。

JDK的动态代理需要满足一定的规则：

- 代理类的处理器实现 `InvocationHandler`接口，并实现 `public Object invoke(Object proxy, Method method, Object[] args)`方法。
- 代理类的处理器须持有目标接口的实例。
- 通过 `Proxy.newProxyInstance`创建代理类实例。

`InvocationHandler` 实例如下：

```java
public class MyProxyInvocationHandler implements InvocationHandler {
		
  	// 目标类的实例
    private final Object target;

    public MyProxyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("JDK 动态代理方法执行开始......proxy: {} , method: {}", proxy.getClass(), method.getName());
        Object result = method.invoke(target, args);
        log.info("JDK 动态代理方法执行结束......");
        return result;
    }

    /**
     * 创建代理类实例
     * 
     * @param target 目标类实例
     * @return 代理类实例
     */
    public static Object newProxyInstance(Object target) {
        InvocationHandler handler = new MyProxyInvocationHandler(target);
      	// 目标类的ClassLoader, 目标类实现的接口, 代理
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}
```

单元测试：

```java
 		@Test
    public void testJdkDynamicProxy() {
        // 目标类实例
        Hallo target = new HalloImpl();
        // 获取代理类对象
        Hallo proxyInstance = (Hallo)MyProxyInvocationHandler.newProxyInstance(target);
        // 通过代理对象调用目标方法即可实现增强
        proxyInstance.hello();
    }
```

从JDK的动态代理中可以发现，Proxy代理对象并没有亲自去做一些代理的动作，而是交由`InvocationHandler` 实例去实现代理行为。

## Cglib 动态代理

