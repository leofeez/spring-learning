package lifecycle.initializing;

import java.io.Serializable;

/**
 * 通过自定义的{@link #init()} 和 {@link #destroy()} 方法进行
 * 初始化和销毁。
 *
 * @author leofee
 */
public class MyCustomInitMethodBean implements Serializable {

    public void init() {
        System.out.println("通过自定义init方法 初始化......init");
    }

    public void destroy() {
        System.out.println("通过自定义destroy方法 销毁......destroy");
    }
}
