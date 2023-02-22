package com.example.springbootjpa;

import com.example.springbootjpa.domain.TestClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootJpaApplicationTests {

    @Test
    void contextLoads() {

        TestClass testClass = TestClass.builder()
                .name("asdf")
                .build();

        System.out.println(testClass.getName());
    }

}
