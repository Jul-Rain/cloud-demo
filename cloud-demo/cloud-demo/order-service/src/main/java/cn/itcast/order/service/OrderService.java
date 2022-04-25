package cn.itcast.order.service;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class OrderService {
    //构造器注入
    private OrderMapper orderMapper;
    @Autowired
    public OrderService(OrderMapper orderMapper){
        this.orderMapper=orderMapper;
    }

    /*@Autowired    注入过时
    private RestTemplate restTemplate;*/

    //set注入
    private RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        //2.使用RestTemplate发送请求,从注册中心eureka访问user微服务查询用户信息
        String uri ="http://userserver/user/"+order.getUserId();
        //3.发送远程调用
        User user = restTemplate.getForObject(uri, User.class);
        //4.封装user到oder
        order.setUser(user);
        // 5.返回
        return order;
    }
}
