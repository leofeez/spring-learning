package anno.Import.selector;

import anno.Import.pojo.Tiger;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author leofee
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 返回需要注册到容器中bean 的类全名
        return new String[]{Tiger.class.getName()};
    }
}
