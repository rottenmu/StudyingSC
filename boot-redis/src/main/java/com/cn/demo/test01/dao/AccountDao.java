package com.cn.demo.test01.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountDao {

	@Select("select balance from account where user_id='100000'")
	BigDecimal findBalance();
	
	
	@Update("update account set balance = #{balance} where user_id='100000'")
	int update(@Param("balance") BigDecimal balance);
}
