package com.wzh.utils.others;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MapUtils {

	public static final String error_info = "内部错误";
	public static final String param_error = "参数错误";
	public static final String login_error = "未登录";
	public static final String success_info = "成功";
	public static final String failed_info = "失败";
	public static final int error_code = 99;
	
	public static Map<String, Object> generateReturnMap(int errcode, String info, String method, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", errcode);
		map.put("info", info);
		map.put("method", method);
		map.put("data", data);
		return map;
	}

	public static Map<String, Object> loginErrorReturn() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", 2);
		map.put("info", login_error);
		map.put("method", "");
		map.put("data", new HashMap<>());
		return map;
	}

	public static Map<String, Object> successReturn(String info, Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", 0);
		map.put("info", info);
		map.put("method", "");
		map.put("data", data);
		return map;
	}

	public static Map<String, Object> paramErrorReturn() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", 1);
		map.put("info", param_error);
		map.put("method", "");
		map.put("data", null);
		return map;
	}
	public static Map<String, Object> successReturn() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", 0);
		map.put("info", success_info);
		map.put("method", "");
		map.put("data", new HashMap<>());
		return map;
	}


	public static Map<String, Object> successReturn(Map<String, Object> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", 0);
		map.put("info", success_info);
		map.put("method", "");
		map.put("data", data);
		return map;
	}

	public static Map<String, Object> failedReturn() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", 3);
		map.put("info", failed_info);
		map.put("method", "");
		map.put("data", null);
		return map;
	}
	public static Map<String, Object> failedReturn(String erro_msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", 3);
		map.put("info", erro_msg);
		map.put("method", "");
		map.put("data", null);
		return map;
	}


	/**
	 * 跟账户中心对接返回的参数格式
	 * @param errcode

	 * @return
	 */
	public static Map<String, Object> generateAccountReturnMap(String errcode, String errmsg, String cmd) {
		String seq = generateSeq();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq", seq);
		map.put("errcode", errcode);
		map.put("errmsg", errmsg);
		map.put("cmd", cmd);
		return map;
	}

	public static String generateSeq(){
		return String.valueOf(new Random().nextInt(10000000)) + System.currentTimeMillis();
	}

    public static Map<String, Object> dealBindResult(BindingResult bindingResult, HttpServletRequest request) {
		Map<String, Object> map = null;
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for(ObjectError error : allErrors){
				map =  MapUtils.generateReturnMap(3, error.getDefaultMessage(), request.getRequestURI(), null);
			}
		}
		return map;
    }

    static String auth_failed = "没有权限";
	public static Map<String, Object> auth_failed(String method) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errcode", 4);
		map.put("info", auth_failed);
		map.put("method", method);
		return map;
	}
}
