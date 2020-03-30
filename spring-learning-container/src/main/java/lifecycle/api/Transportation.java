package lifecycle.api;

import lifecycle.constants.TransportationType;

/**
 * @author leofee
 */
public interface Transportation {

    /**
     * 启动
     */
    void run();

    /**
     * 交通工具类型
     *
     * @return 交通工具类型
     */
    TransportationType getTransportationType();
}
