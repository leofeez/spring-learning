## 代理设计模式
代理设计模式就是对目标方法的执行提供了代理，在代理对象中才去真正的执行目标方法，在代理对象中我们可以增加自己的一些功能。
代理设计模式的目的就是对目标方法的增强，在不修改原来设计的情况下，对方法行为进行增强。

代理设计模式的具体实现表现为三种：

- 静态代理
- JDK动态代理
- Cglib动态代理

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
        System.out.println("static proxy before say hello! time now is: " + LocalDateTime.now());

        // 调用目标方法
        target.hello();

        System.out.println("static proxy after  say hello! time now is: " + LocalDateTime.now());
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
- 通过 `Proxy.newProxyInstance`创建代理类实例，需要传入目标类的ClassLoader，一组interfaces和invocationHandler。

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
        System.out.println("static jdk dynamic proxy before say hello! time now is: " + LocalDateTime.now());

        // 调用目标方法
        Object result = method.invoke(target, args);

        System.out.println("static jdk dynamic proxy after  say hello! time now is: " + LocalDateTime.now());
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

JDK动态代理要求使用动态代理的对象必须实现一个或多个接口。

### JDK动态代理源码分析

总体的流程如下：

- 由于创建代理类需要生成代理类的Class信息，所以第一步从WeakCache中根据ClassLoader和interfaces数据作为key去缓存中查找。
- 缓存中没有则利用ProxyClassFactory动态生成。
- 获取代理类Class中参数为`InvocationHandler`的构造器。
- 根据构造方法constructor.newInstance(handler)生成代理类的实例。

以`Proxy.newProxyInstance(ClassLoader cl, Class<?>[] interfaces, InvocationHandler handler)`作为入口方法，该方法定义了生成代理对象的实例的整体框架：

```java
public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h) throws IllegalArgumentException {
        Objects.requireNonNull(h);
				// 克隆目标类的接口
        final Class<?>[] intfs = interfaces.clone();
  			// 安全校验
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
        }

        /*
         * 重点方法！！！
         * Look up or generate the designated proxy class.
         * 获取对应的代理类Class，优先从缓存WeakCache中获取
         * 如果缓存中没有则利用ProxyClassFactory动态创建
         */
        Class<?> cl = getProxyClass0(loader, intfs);

        /*
         * Invoke its constructor with the designated invocation handler.
         */
        try {
            if (sm != null) {
                checkNewProxyPermission(Reflection.getCallerClass(), cl);
            }
						// 获取proxy class带有InvocationHandler的构造器
            final Constructor<?> cons = cl.getConstructor(constructorParams);
            final InvocationHandler ih = h;
          	// 设置构造方法的访问权限
            if (!Modifier.isPublic(cl.getModifiers())) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        cons.setAccessible(true);
                        return null;
                    }
                });
            }
          	// 利用构造器生成实例
          	// 这里的 h 就是 InvocationHandler 实例
            return cons.newInstance(new Object[]{h});
        }
  			......
    }
```

1. 在生成代理类Class信息之前会去缓存中`WeakCache`中查找

```java
		/**
     * Generate a proxy class.  Must call the checkProxyAccess method
     * to perform permission checks before calling this.
     * 根据classloader和接口去缓存中拿对应的代理类Class
     * 如果缓存中没有则利用ProxyClassFactory生成代理类Class
     */
    private static Class<?> getProxyClass0(ClassLoader loader,
                                           Class<?>... interfaces) {
        // 校验目标的接口数量是否超出限制
      	if (interfaces.length > 65535) {
            throw new IllegalArgumentException("interface limit exceeded");
        }

      	// 根据classloader和接口去缓存中拿对应的代理类Class
				// 如果缓存中没有则利用ProxyClassFactory生成代理类Class
        return proxyClassCache.get(loader, interfaces);
    }
```

2. 查找WeakCache缓存

   关于WeakCache的介绍：

   > WeakCache的key即`CacheKey` 继承了`WeakReference`，是一个弱引用的key，WeakCache缓存一共有两级，第一级的key以目标	类的ClassLoader作为生成规则，第二级的key生成规则是根据目标类的interfaces，具体生成规则见KeyFactory。WeakCache的二级缓存的value生成由ProxyClassFactory实现。

   ```java
   public class WeakCache {
     /**
    	 * @param key 为目标类的ClassLoader
    	 * @param parameter 为目标类的interfaces
    	 */
   	public V get(K key, P parameter) {
           Objects.requireNonNull(parameter);
   
           expungeStaleEntries();
   				// 生成一级缓存的key
           Object cacheKey = CacheKey.valueOf(key, refQueue);
   				// 二级缓存为延迟加载的，当第一次获取并且为null 才去初始化对应一级下的二级缓存
           // lazily install the 2nd level valuesMap for the particular cacheKey
           ConcurrentMap<Object, Supplier<V>> valuesMap = map.get(cacheKey);
     			// 二级缓存为null则开始new ConcurrentHashMap
           if (valuesMap == null) {
               ConcurrentMap<Object, Supplier<V>> oldValuesMap
                   = map.putIfAbsent(cacheKey,
                                     valuesMap = new ConcurrentHashMap<>());
               if (oldValuesMap != null) {
                   valuesMap = oldValuesMap;
               }
           }
   				// subKeyFactory根据parameter即目标类的接口生成对应二级缓存key
           // create subKey and retrieve the possible Supplier<V> stored by that
           // subKey from valuesMap
           Object subKey = Objects.requireNonNull(subKeyFactory.apply(key, parameter));
     			// 二级缓存的Value是一个Supplier
           Supplier<V> supplier = valuesMap.get(subKey);
           Factory factory = null;
   				
     			// 第一次生成代理类的时候二级缓存一定是空的
     			// 注意这里的while(true)
           while (true) {
             	// 第一次循环时，supplier为null
             	// 第二次循环时，由于二级缓存的value在下方代码进行了实例化，所以这里会执行Factory#get()
               if (supplier != null) {
                 	// 这一步在第一次还未生成Class的时候value是一个Factory实例
                 	// 当第一次生成代理类Class之后这里返回的就是最终的Class而不是工厂Factory
                   // supplier might be a Factory or a CacheValue<V> instance
                   V value = supplier.get();
                   if (value != null) {
                       return value;
                   }
               }
               // else no supplier in cache
               // or a supplier that returned null (could be a cleared CacheValue
               // or a Factory that wasn't successful in installing the CacheValue)
   						
             	// 这里开始生成二级缓存key对应的Value
               // lazily construct a Factory
               if (factory == null) {
                   factory = new Factory(key, parameter, subKey, valuesMap);
               }
   						// 将生成的二级缓存value放进缓存中
               if (supplier == null) {
                   supplier = valuesMap.putIfAbsent(subKey, factory);
                   if (supplier == null) {
                       // successfully installed Factory
                       supplier = factory;
                   }
                   // else retry with winning supplier
               } else {
                   if (valuesMap.replace(subKey, supplier, factory)) {
                       // successfully replaced
                       // cleared CacheEntry / unsuccessful Factory
                       // with our Factory
                       supplier = factory;
                   } else {
                       // retry with current supplier
                       supplier = valuesMap.get(subKey);
                   }
               }
           }
       }
   }
   ```

   二级缓存内部的实现：

   ```java
   private final class Factory implements Supplier<V> {
   				// 目标类的class loader
           private final K key;
     			// 目标类的interfaces
           private final P parameter;
     			// 二级缓存的key
           private final Object subKey;
     			// 二级缓存的value map
           private final ConcurrentMap<Object, Supplier<V>> valuesMap;
   
           Factory(K key, P parameter, Object subKey,
                   ConcurrentMap<Object, Supplier<V>> valuesMap) {
               this.key = key;
               this.parameter = parameter;
               this.subKey = subKey;
               this.valuesMap = valuesMap;
           }
   
           @Override
           public synchronized V get() { // serialize access
               // re-check
               Supplier<V> supplier = valuesMap.get(subKey);
               if (supplier != this) {
                   // something changed while we were waiting:
                   // might be that we were replaced by a CacheValue
                   // or were removed because of failure ->
                   // return null to signal WeakCache.get() to retry
                   // the loop
                   return null;
               }
               // else still us (supplier == this)
   						
             	// 核心方法！！！
             	// 这里开始利用valueFactory工厂生成Class
               // create new value
               V value = null;
               try {
                   value = Objects.requireNonNull(valueFactory.apply(key, parameter));
               } finally {
                   if (value == null) { // remove us on failure
                       valuesMap.remove(subKey, this);
                   }
               }
               // the only path to reach here is with non-null value
               assert value != null;
   						
             	// 将最终的生成的Class进行封装
               // wrap value with CacheValue (WeakReference)
               CacheValue<V> cacheValue = new CacheValue<>(value);
   
               // put into reverseMap
               reverseMap.put(cacheValue, Boolean.TRUE);
   
   						// 这里开始将原来map中的factory实例替换为真正的value
               // try replacing us with CacheValue (this should always succeed)
               if (!valuesMap.replace(subKey, this, cacheValue)) {
                   throw new AssertionError("Should not reach here");
               }
   
               // successfully replaced us with new CacheValue -> return the value
               // wrapped by it
               return value;
           }
       }
   ```

   

   在此步骤的总结如下：

   - 根据ClassLoader作为一级key查找二级缓存的ConcurrentMap
   - 二级缓存的Map为空则实例化空的Map
   - subKeyFactory根据parameter即目标类的接口生成对应二级缓存key
   - 在第一次while（true）循环中，supplier为null，则new Factory(......)作为二级缓存的value
   - 第二次while(true)循环，这时候supplier在上一步中实例化了，所以这里调用supplier.get()方法。
   - supplier.get()方法即Factory内部委托给valuesFactory（即ProxyClassFactory）真正的生成最终的value。

3. ProxyClassFactory 生成最终的代理Class

```java
private static final class ProxyClassFactory
        implements BiFunction<ClassLoader, Class<?>[], Class<?>>
    {
        // prefix for all proxy class names
  			// 代理类名称的前缀，类似于 $Proxy10,$Proxy20......
        private static final String proxyClassNamePrefix = "$Proxy";
        // next number to use for generation of unique proxy class names
        private static final AtomicLong nextUniqueNumber = new AtomicLong();

        @Override
        public Class<?> apply(ClassLoader loader, Class<?>[] interfaces) {

            Map<Class<?>, Boolean> interfaceSet = new IdentityHashMap<>(interfaces.length);
            for (Class<?> intf : interfaces) {
                /*
                 * Verify that the class loader resolves the name of this
                 * interface to the same Class object.
                 * 校验目标类的接口是否存在
                 */
                Class<?> interfaceClass = null;
                try {
                    interfaceClass = Class.forName(intf.getName(), false, loader);
                } catch (ClassNotFoundException e) {
                }
                if (interfaceClass != intf) {
                    throw new IllegalArgumentException(
                        intf + " is not visible from class loader");
                }
                /*
                 * Verify that the Class object actually represents an
                 * interface.
                 * 校验传入的 interface 是否是接口
                 */
                if (!interfaceClass.isInterface()) {
                    throw new IllegalArgumentException(
                        interfaceClass.getName() + " is not an interface");
                }
                /*
                 * Verify that this interface is not a duplicate.
                 */
                if (interfaceSet.put(interfaceClass, Boolean.TRUE) != null) {
                    throw new IllegalArgumentException(
                        "repeated interface: " + interfaceClass.getName());
                }
            }

            String proxyPkg = null;     // package to define proxy class in
            int accessFlags = Modifier.PUBLIC | Modifier.FINAL;

            /*
             * Record the package of a non-public proxy interface so that the
             * proxy class will be defined in the same package.  Verify that
             * all non-public proxy interfaces are in the same package.
             */
            for (Class<?> intf : interfaces) {
                int flags = intf.getModifiers();
                if (!Modifier.isPublic(flags)) {
                    accessFlags = Modifier.FINAL;
                    String name = intf.getName();
                    int n = name.lastIndexOf('.');
                    String pkg = ((n == -1) ? "" : name.substring(0, n + 1));
                    if (proxyPkg == null) {
                        proxyPkg = pkg;
                    } else if (!pkg.equals(proxyPkg)) {
                        throw new IllegalArgumentException(
                            "non-public interfaces from different packages");
                    }
                }
            }

            if (proxyPkg == null) {
                // if no non-public proxy interfaces, use com.sun.proxy package
                proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
            }

            /*
             * Choose a name for the proxy class to generate.
             * 根据 $Proxy + nextUniqueNumber 生成代理类的名称
             */
            long num = nextUniqueNumber.getAndIncrement();
            String proxyName = proxyPkg + proxyClassNamePrefix + num;

            /*
             * Generate the specified proxy class.
             * 生成代理类的字节码文件
             */
            byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);
            try {
              	// 返回代理类的定义信息
                return defineClass0(loader, proxyName,
                                    proxyClassFile, 0, proxyClassFile.length);
            } catch (ClassFormatError e) {
                /*
                 * A ClassFormatError here means that (barring bugs in the
                 * proxy class generation code) there was some other
                 * invalid aspect of the arguments supplied to the proxy
                 * class creation (such as virtual machine limitations
                 * exceeded).
                 */
                throw new IllegalArgumentException(e.toString());
            }
        }
    }
```

3. ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags)

## Cglib 动态代理

> CGLIB是一个强大的高性能的代码生成包。底层通过使用一个小而快的字节码生成框架ASM来转换字节码动态的生成类。
>
> 由于动态生成的类是继承于目标类，并重写目标方法来实现，所以Cglib不能代理final修饰的目标方法。
>
> 在Spring AOP中就同时用到了 JDK 动态代理和 Cglib动态代理，默认是JDK动态代理，实际情况具体用哪一种取决于目标Bean是否实现了接口。

Cglib动态代理和JDK动态代理的区别：

- JDK是基于接口进行代理，所以目标类必须实现接口，而Cglib是基于类，以继承的方法实现。
- JDK底层是利用InvocationHandler引入增强的逻辑，利用Proxy根据目标类的ClassLoader，目标类实现的接口，以及handler动态生成代理类。而Cglib底层利用ASM框架动态生成对应的子类字节码。

目标类，没有实现接口：

```java
public class Hallo {

    public void hello() {
        System.out.println("hello cglib proxy!");
    }
}
```

Cglib需要实现 `MethodInterceptor`：

```java
public class MyCglibInvocationHandler implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib dynamic proxy before say hello! time now is: " + LocalDateTime.now());

        Object result = methodProxy.invokeSuper(o, args);

        System.out.println("cglib dynamic proxy after  say hello! time now is: " + LocalDateTime.now());
        return result;
    }
}
```

调用代理类：

```java
		@Test
    public void testCglibProxy() {
        Enhancer enhancer = new Enhancer();
        // 设置代理类的父类
        enhancer.setSuperclass(Hallo.class);
        // 设置方法的回调
        enhancer.setCallback(new MyCglibInvocationHandler());
        // 创建代理对象
        Hallo proxyInstance = (Hallo) enhancer.create();
        // 执行代理方法
        proxyInstance.hello();
    }
```

