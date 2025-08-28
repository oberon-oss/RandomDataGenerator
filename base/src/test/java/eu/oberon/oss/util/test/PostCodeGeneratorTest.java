package eu.oberon.oss.util.test;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostCodeGeneratorTest {

    @Test
    void test1(){
        PostCodeGenerator generator = new PostCodeGenerator();

        List<String> postCode;


        postCode = generator.generateValueList(10000,true);
        postCode = generator.generateValueList(10000,false);

        assertNotNull(generator);
    }
}