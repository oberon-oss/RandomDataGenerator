package eu.oberon.oss.util.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostCodeGeneratorTest {

    @Test
    void test1(){
        PostCodeGenerator generator = new PostCodeGenerator();
        assertNotNull(generator);
    }
}