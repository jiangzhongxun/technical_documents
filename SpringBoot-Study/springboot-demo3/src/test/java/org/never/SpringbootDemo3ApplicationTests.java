package org.never;

import org.junit.jupiter.api.Test;
import org.never.model.Apple;
import org.never.model.Balana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDemo3ApplicationTests {

    @Autowired
    Apple apple;
    @Autowired
    Balana balana;

    @Test
    void contextLoads() {
        System.out.println(apple);
        System.out.println(balana);
    }

}
