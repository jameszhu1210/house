package com.sysco.house.web.utils;

import com.sysco.house.common.exception.ValidationException;
import com.sysco.house.common.request.RegisterUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public class ValidateUtils {

	public static boolean validRegisterUser(RegisterUser account) {
		if(StringUtils.isBlank(account.getEmail())){
			throw new ValidationException(HttpStatus.OK, "Email 不能为空");
		}
		if(StringUtils.isBlank(account.getPasswd())){
			throw new ValidationException(HttpStatus.OK, "PassWood 不能为空");
		}
		if(account.getPasswd().length() < 6){
			throw new ValidationException(HttpStatus.OK, "PassWood 不能小于6位");
		}
		return true;
	}
}
