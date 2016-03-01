package com.node.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * @Package com.clt.util
 * @Description: 后台返回json
 * @author hjx
 * @date 2014年7月18日 上午11:33:58
 * @version V1.0
 */
public class AjaxUtil {
	private static void rendText(HttpServletResponse response, String content)
			throws IOException

	{
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(content);
	}

	/**
	 * @Description: 封装成json,不建议使用，该方法替换为 getMap( boolean success , String
	 *               message ); 请注意： getMap这个方法会返回一个map对象。
	 * @param response
	 * @param success
	 *            是否成功
	 * @param message
	 *            返回的消息
	 * @throws IOException
	 *             void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月18日 上午11:45:48
	 * 
	 */
	public static void rendJson(HttpServletResponse response, boolean success,
			String message) {
		JSONObject json = new JSONObject();
		json.put("isSuccess", success);
		json.put("message", message);

		try {
			rendText(response, json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回一个是否成功，并带提示消息的map
	 * 
	 * @param success
	 * @param message
	 * @return
	 */
	public static Map<String, Object> getMap(boolean success, String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", success);
		map.put("message", message);
		return map;
	}

	/**
	 * @Description: 分装为map 方便返回数据给app，这是没异常的分装（分成功和未成功）
	 * @param isSuccess
	 *            是否获得数据，默认提示消息 成功！ 和 没有获取到相关数据！
	 * @param obj
	 *            这是对应数据
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2015年4月15日 下午2:17:23
	 */
	public static Map<String, Object> getMapByNotException(boolean isSuccess,
			Object obj) {
		Map<String, Object> appMap = new HashMap<String, Object>();
		appMap.put("isSuccess", isSuccess);
		if (!isSuccess) {
			appMap.put("message", "没有获取到相关数据！");
		} else {
			appMap.put("message", "获取成功！");
			appMap.put("data", obj);
		}
		return appMap;
	}

}
