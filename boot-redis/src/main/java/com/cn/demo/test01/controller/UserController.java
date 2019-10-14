package com.cn.demo.test01.controller;

import com.cn.demo.test01.dao.AccountDao;
import com.cn.demo.test01.pojo.UserInfo;
import com.cn.demo.test01.service.impl.UserService;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = { "/user" })
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/findAll" }, produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
	public List getAllUsers() {
		List list = userService.findAllUser();
		return list;
	}

	@PostMapping("/update")
	public int update() {

		// RReadWriteLock readWriteLock = redisson.getReadWriteLock(new
		// UserInfo().getId());
		// UUID.randomUUID().toString() redisson.getFairLock(new UserInfo().getId());
		// 60tps
		// RLock lock = redisson.getFairLock(new UserInfo().getId());;
//		RReadWriteLock readWriteLock = redisson.getReadWriteLock(new UserInfo().getId());
//		RLock rlock = readWriteLock.writeLock();
//		try {
//			BigDecimal balance = dao.findBalance();
//			 rlock.lock();
//			  while (balance.compareTo(BigDecimal.valueOf(1.00)) >= 0) {
//				
//				int row = dao.update(balance.subtract(BigDecimal.valueOf(1.00)));
//				System.out.println();
//				return row;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//		 rlock.unlock();
//		}

		return userService.update();

	}
}
