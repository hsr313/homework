package com.hsr.blog;

import com.hsr.blog.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class BlogApplicationTests {


    @Autowired
    private BlogRepository blogRepository;

    @Test
    void contextLoads() {
        Optional optional = blogRepository.findById(1);
        System.out.println(optional.get());
    }

}
