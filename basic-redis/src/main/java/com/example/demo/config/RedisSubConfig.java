package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.example.demo.componet.RedisMessage;

/**
 * redis队列 订阅配置
 * @author admin
 *
 */
@Configuration
public class RedisSubConfig {

	

	/**
	 * @description  创建连接工厂
	 * @param factory
	 * @param listenerAdapter
	 * @param topic
	 * @return
	 */
	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory factory,
			MessageListenerAdapter listenerAdapter) {
		
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(factory);
		container.addMessageListener(listenerAdapter, new PatternTopic("msg"));
		return container;
		
	}
	/**
	 * @description 实例化消息监听器
	 * @param message
	 * @return
	 */
	@Bean
	public MessageListenerAdapter listenerAdapter(RedisMessage  message) {
        // onMessage 如果RedisMessage 中 没有实现接口，这个参数必须跟RedisMessage中的读取信息的方法名称一样
		return new MessageListenerAdapter(message,"onMessage");
		
	}
}
