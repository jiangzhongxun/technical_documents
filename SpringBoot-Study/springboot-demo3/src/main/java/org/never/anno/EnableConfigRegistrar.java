package org.never.anno;

import org.never.model.Apple;
import org.never.model.Balana;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/12 14:19
 */
public class EnableConfigRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registry.registerBeanDefinition("apple", new RootBeanDefinition(Apple.class));
        registry.registerBeanDefinition("balana", new RootBeanDefinition(Balana.class));
    }
}
