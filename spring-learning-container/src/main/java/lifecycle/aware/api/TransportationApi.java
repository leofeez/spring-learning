package lifecycle.aware.api;

import lifecycle.constants.TransportationType;

/**
 * @author leofee
 */
public interface TransportationApi {

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
