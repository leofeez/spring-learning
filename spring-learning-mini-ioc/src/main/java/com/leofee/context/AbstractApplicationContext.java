package com.leofee.context;

import com.leofee.factory.BeanFactory;
import com.leofee.factory.ConfigurableBeanFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public abstract class AbstractApplicationContext implements ApplicationContext {

    protected ConfigurableBeanFactory beanFactory;

    private Boolean isActive = false;

    public AbstractApplicationContext() {
        this.beanFactory = new ConfigurableBeanFactory();
    }

    @Override
    public void refresh() {
        // 准备刷新
        prepareBeanFactory();

        // 解析配置

        finishRefresh();
    }

    protected void prepareBeanFactory() {
        System.out.println("mini application context start up at: "
                + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now()));
        isActive = true;
    }

    protected void finishRefresh() {
        System.out.println("mini application context start finish at: "
                + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(LocalDateTime.now()));

        System.out.println("\n" +
                "  __  __   _____   _   _   _____     _____    ____     _____ \n" +
                " |  \\/  | |_   _| | \\ | | |_   _|   |_   _|  / __ \\   / ____|\n" +
                " | \\  / |   | |   |  \\| |   | |       | |   | |  | | | |     \n" +
                " | |\\/| |   | |   | . ` |   | |       | |   | |  | | | |     \n" +
                " | |  | |  _| |_  | |\\  |  _| |_     _| |_  | |__| | | |____ \n" +
                " |_|  |_| |_____| |_| \\_| |_____|   |_____|  \\____/   \\_____|\n" +
                "                                                             \n" +
                "                                                             \n");
    }

    @Override
    public BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Supplier<T> supplier) {
        return getBeanFactory().getBean(beanName, supplier);
    }

    @Override
    public boolean containBean(String beanName) {
        return getBeanFactory().containBean(beanName);
    }
}