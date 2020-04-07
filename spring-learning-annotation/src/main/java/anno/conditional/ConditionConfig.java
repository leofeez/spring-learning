package anno.conditional;

import anno.componentscan.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {

    /**
     * 当前系统为windows才会实例化
     */
    @Conditional(value = {WinCondition.class})
    @Bean("windows")
    public Environment getWindows() {
        System.out.println("开始创建 windows ......");
        Environment windows = new Environment();
        windows.setName("windows");
        return windows;
    }

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
