package com.bjpowernode.test;

import com.bjpowernode.bean.User;
import com.bjpowernode.service.SqlService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlService sqlService= (SqlService) applicationContext.getBean("sqlServiceImpl");
        System.out.println("-----------更新前数据-------------");
        System.out.println(sqlService.select());
        User user=new User();
        user.setName("我是Mybatis数据3");
        int count=sqlService.add(user);
        System.out.println("新增信息状态:"+count);
        System.out.println("-----------更新后数据-------------");
        System.out.println(sqlService.select());
    }
}
