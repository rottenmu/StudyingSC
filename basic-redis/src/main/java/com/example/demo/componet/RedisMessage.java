package com.example.demo.componet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
  *       接受消息的实体
 * @author admin
 *
 */
@Component
public class RedisMessage implements MessageListener{
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public void onMessage(Message message, byte[] pattern) {
		
		RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
		String msg = redisSerializer.deserialize(message.getBody());
		System.out.println("msg=======>>>>>>>"+msg);
		
	}

}
