package org.never.anno;

import org.never.model.Apple;
import org.never.model.Balana;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/12 14:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// 1. @Import({Apple.class, Balana.class})
// 2. @Import({EnableConfigSelector.class})
@Import({EnableConfigRegistrar.class})
public @interface EnableConfig {
}

