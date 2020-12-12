package org.never.anno;

import org.never.model.Apple;
import org.never.model.Balana;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/12 14:16
 */
public class EnableConfigSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Apple.class.getName(), Balana.class.getName()};
    }
}
