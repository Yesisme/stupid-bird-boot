package com.epsoft.demo.bean.model;

import com.epsoft.demo.SpringBootDemoApplication;
import com.epsoft.demo.dao.SonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class Son {

    @Autowired
    private SonMapper sonMapper;

    @Test
    public void testSon(){
        sonMapper.selectById(1);
    }
}
