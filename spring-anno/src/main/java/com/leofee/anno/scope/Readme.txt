Spring 中 @Scope 扫描规则

ConfigurableBeanFactory#SCOPE_SINGLETON : 单实例
IOC 容器启动时就会创建bean
比如 session , 因为多次请求都是用同一个实例

ConfigurableBeanFactory#SCOPE_PROTOTYPE : 多实例
IOC 容器启动时不会创建bean 而是在使用时才会创建
比如 request , 因为一次请求就必须创建一个实例
