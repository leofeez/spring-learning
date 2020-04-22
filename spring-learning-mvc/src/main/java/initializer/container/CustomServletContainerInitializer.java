package initializer.container;

import initializer.servlet.CustomServletInitializerApi;
import org.springframework.beans.BeanUtils;
import org.springframework.web.SpringServletContainerInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的 {@code ServletContainerInitializer}
 *
 * <p>
 * 用于初始化 {@code servlet container}
 *
 * <p>
 * {@link HandlesTypes @HandlesTypes} 注解中指定的接口， tomcat在启动时会自动扫描
 * 到项目路径下所有实现了指定接口的实现类，并放入{@link #onStartup(Set, ServletContext)}
 * 方法的{@code classes}参数中。
 *
 * @author leofee
 * @see SpringServletContainerInitializer
 */
@HandlesTypes(CustomServletInitializerApi.class)
public class CustomServletContainerInitializer implements ServletContainerInitializer {

    /**
     * @param classes {@link HandlesTypes @HandlesTypes} 注解指定接口的实现类class
     */
    @Override
    public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {

        // 实例化接口实现类
        Set<CustomServletInitializerApi> initializers = instantiateInitializers(classes);

        // 执行接口方法
        invokeInitializers(initializers, servletContext);

    }

    private Set<CustomServletInitializerApi> instantiateInitializers(Set<Class<?>> classes) {
        Set<CustomServletInitializerApi> initializers = new HashSet<>();

        // 实例化@HandlesTypes指定的接口实现类
        classes.forEach(webAppInitializerClass -> {

            CustomServletInitializerApi initializer =
                    (CustomServletInitializerApi)BeanUtils.instantiateClass(webAppInitializerClass);
            initializers.add(initializer);

        });
        return initializers;
    }

    private void invokeInitializers(Set<CustomServletInitializerApi> initializers, ServletContext servletContext)
            throws ServletException{

        // 执行接口实现类的方法
        for (CustomServletInitializerApi initializer : initializers) {
            initializer.onStartup(servletContext);
        }
    }
}
