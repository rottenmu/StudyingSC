package com.cn.demo.test01.service.impl.impl;

import com.cn.demo.test01.dao.AccountDao;
import com.cn.demo.test01.dao.UserInfoMapper;
import com.cn.demo.test01.pojo.UserInfo;
import com.cn.demo.test01.service.impl.UserService;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoMapper userDao;
    
    @Resource
    private AccountDao dao;
    
    @Autowired
    private RedissonClient redisson;
    @Override
    public List findAllUser(){
       return  userDao.findAllUser();
    }
    
    
	@Override
	//@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int update() {
		RLock lock = redisson.getFairLock(new UserInfo().getId());
	
		try {
				lock.lock();
			BigDecimal balance = dao.findBalance();
			  while (balance.compareTo(BigDecimal.valueOf(1.00)) >= 0) {
				int row = dao.update(balance.subtract(BigDecimal.valueOf(1.00)));
				System.out.println();
				return row;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		 lock.unlock();
		}

		return 1;
	}
}
