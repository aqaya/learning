package com.wujun.learning.beans;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component(value = "wujun")
public class Wujun implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("wujun destroy!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("wujun afterPropertiesSet");
    }

    @PostConstruct
    public void init(){
        System.out.println("wujun init!");
    }

    @Value(value = "${key}")
    private String name;

    @Value(value = "1")
    private int age;

    @Value(value = "1,2")
    private List<String> friends;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("set name: " + name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("set age = [" + age + "]");
        this.age = age;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Wujun{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friends=" + friends.size() +
                '}';
    }
}
