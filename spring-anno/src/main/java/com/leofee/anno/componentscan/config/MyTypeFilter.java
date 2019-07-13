package com.leofee.anno.componentscan.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义 TypeFilter
 */
public class MyTypeFilter implements TypeFilter {

    /**
     *
     * @param metadataReader 读取到当前正在扫描类的信息
     * @param metadataReaderFactory 可以获取到其他任何类信息
     * @return true / false
     */
    public boolean match(MetadataReader metadataReader,
                         MetadataReaderFactory metadataReaderFactory) throws IOException {

        // 获取当前类的注解
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        // 获取当前正在扫描的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        // 这里会把所有扫描到的类输出
        System.out.println("classMetadata : " + classMetadata.getClassName());

        // 获取当前类的资源(类的路径)
        Resource resource = metadataReader.getResource();


        return classMetadata.getClassName().contains("User");
    }
}
