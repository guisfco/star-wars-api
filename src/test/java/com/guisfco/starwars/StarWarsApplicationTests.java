package com.guisfco.starwars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class StarWarsApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }
}
