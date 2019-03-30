package com.example.demo.componet;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * Redis OPstions Class API
 * @author XiuWood
 *
 */
@Component
public class RedisComponet {


	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * @description 获取 redis val
	 * @param key
	 * @return
	 */
	public String getVal(Object key) {
		return  redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * @description 通过redis 获取 对象集合
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> List<T> getVal(String key,Class<T> clazz){
		
		return JSON.parseArray(redisTemplate.opsForValue().get(key), clazz);
	}

	/**
	 * @description 通过redis 获取 某个对象
	 * @param key
	 * @param clazz
	 * @return
	 */
	public  <T> T  getClazz(Object key,Class<T> clazz){
		return  JSON.parseObject(redisTemplate.opsForValue().get(key),clazz);
	}

	/**
	 * @description 删除某个key
	 * @param key
	 */
	public boolean deleteKey(String key) {
		
		return redisTemplate.delete(key);
	}
	
	/**
	 * @description 删除多组 key
	 * @param keys
	 * @return
	 */
	public Long deleteKeyList(Collection<String> keys) {
		return redisTemplate.delete(keys);
	}
	
	public void subscribe() {
	}
	
	/**
	 * @description redis 发布消息
	 * @param channel
	 * @param msgCtx
	 */
	public void  publish(String channel,String msgCtx) {
		 redisTemplate.convertAndSend(channel, msgCtx);
	}
	
	/**
	 * @description redis 订阅消息
	 * @param message
	 * @param pattern
	 * @param clazz
	 * @return
	 */
	public <T> T subscribe(Message message,byte[] pattern,Class<T> clazz) {
		RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
		String msg  = redisSerializer.deserialize(message.getBody());
		return JSON.parseObject(msg, clazz);
	}
	
	/**
	 * @description 
	 * @return
	 */
	public ValueOperations<String,String> getValueOperations() {

		return redisTemplate.opsForValue();
	}
}
