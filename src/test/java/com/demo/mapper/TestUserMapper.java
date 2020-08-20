package com.demo.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testFindOne(){
        String name=userMapper.findOneByDepartmentAndType("development","department_manager");
        System.out.println(name);
    }
    @Test
    public void testFindOneByType(){
        String name=userMapper.findOneByType("manager");
        System.out.println(name);
    }
}
