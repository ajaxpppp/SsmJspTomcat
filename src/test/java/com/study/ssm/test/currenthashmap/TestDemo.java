package com.study.ssm.test.currenthashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestDemo {

    @Test
    public void UserManagementTest(){
        HashMap<Integer, String> usermap = new HashMap<Integer, String>();

        usermap.put(101,"alice");
        usermap.put(102,"bob");
        usermap.put(103,"rick");


        System.out.println("遍历用户 = ");

        for (Map.Entry<Integer,String> entry:usermap.entrySet()){
            System.out.println("entry = " + entry.getKey()+"\t"+"value"+entry.getValue());
        }

    }
}
