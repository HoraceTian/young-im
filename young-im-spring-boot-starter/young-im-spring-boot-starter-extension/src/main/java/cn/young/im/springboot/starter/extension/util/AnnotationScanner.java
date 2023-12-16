package cn.young.im.springboot.starter.extension.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName AnnotatedEntityScanner
 * @Description 注解扫描器
 * @date 2023/12/11 10:09
 * @Author yanceysong
 * @Version 1.0
 */

@Slf4j
public class AnnotationScanner {
    /**
     * 扫描RefreshConfig注解的类
     *
     * @param basePackage 扫描包
     * @return 返回类与对应注解信息
     */
    public static Map<Class<?>, Annotation> scanClassByAnnotation(String basePackage, Class<? extends Annotation> annotationClas) {
        Map<Class<?>, Annotation> annotatedClassesMap = new HashMap<>();
        try {
            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
            scanner.addIncludeFilter(new AnnotationTypeFilter(annotationClas));
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(AnnotationScanner.class.getClassLoader());
            MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory(resourcePatternResolver);
            for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(Objects.requireNonNull(bd.getBeanClassName()));
                String className = metadataReader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(className);
                Annotation annotation = clazz.getAnnotation(annotationClas);
                annotatedClassesMap.put(clazz, annotation);
            }
        } catch (IOException | ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        }
        return annotatedClassesMap;
    }
}
