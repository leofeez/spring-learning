package anno.Import.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 返回需要注册到容器中bean 的类全名
        return new String[]{"Tiger", "Cat"};
    }
}
