package lifecycle.dependcycle.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 测试循环依赖
 *
 * @author leofee
 */
@ComponentScan(basePackages = "lifecycle.dependcycle")
@Configuration
public class DependCycleConfig {
}
