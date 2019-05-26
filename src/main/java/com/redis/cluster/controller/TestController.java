package com.redis.cluster.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author liuboren
 * @Title:
 * @Description:
 * @date 2019/5/26 17:02
 */
@RestController
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("insert")
    public boolean insert(String key,String value){
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisClusterConnection clusterConnection = connectionFactory.getClusterConnection();

        return clusterConnection.set(key.getBytes(), value.getBytes());
    }

    @GetMapping("get_value")
    public byte[] getValue(String key){
        byte[] bytes = Objects.requireNonNull(redisTemplate.getConnectionFactory()).
                getClusterConnection().get("lbr".getBytes());

        String newString = new String(bytes);
        System.out.println("newString = " + newString);
        return bytes;

        // return JSON.parseObject(result, String.class);
    }
}
