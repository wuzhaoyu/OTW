package com.wzy.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	public static boolean isEmpty(String s) {
		return (null == s || s.trim().length() == 0);
	}

	public static <T> boolean isNotEmpty(List<T> list) {
		return !isEmpty(list);
	}

	public static <T> boolean isEmpty(List<T> list) {
		return null == list || list.isEmpty();
	}

	public static <T> boolean isEmpty(Set<T> set) {
		return null == set || set.isEmpty();
	}

	public static final boolean isEmpty(Object[] str) {
		return str == null || str.length == 0;
	}

	public static final boolean isEmpty(java.util.Collection<?> coll) {
		return coll == null || coll.isEmpty();
	}

	public static final boolean isEmpty(Map<?, ?> coll) {
		return coll == null || coll.isEmpty();
	}

	public static String response(boolean success, String msg) {
		return response(success, msg, null);
	}

	public static String response(boolean success, String msg, Object result) {
		JSONObject resultObj = new JSONObject();
		resultObj.put("success", success);
		resultObj.put("msg", msg);
		resultObj.put("result", result);
		return resultObj.toString();
	}

	public static String toEncoding(String src, String fromCharsetName,
			String toCharsetName) {
		String result = "";
		if (src != null && !src.trim().equals("")) {
			try {
				result = new String(src.getBytes(fromCharsetName),
						toCharsetName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
/*	public static String getList2String(List childList, String propertyName)
			throws exception {
		StringBuffer stringBuffer = new StringBuffer();
		if (childList != null) {
			for (int i = 0; i < childList.size(); i++) {
				Object dest = childList.get(i);
				String propertyValue = BeanUtils
						.getProperty(dest, propertyName);
				if (propertyValue == null || "".equals(propertyValue)) {
					stringBuffer.append("," + propertyValue);
				}
			}
			stringBuffer.deleteCharAt(0);
		}
		return stringBuffer.toString();
	}*/

	public static String getUUID() {
		// 取两组UUID拼接字符串
		String s = UUID.randomUUID().toString() + UUID.randomUUID().toString();
		// 去除UUID中的'-',并截取成48位
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24, 44)
				+ s.substring(45, 49) + s.substring(50, 54);
	}

	public static Date string2Date(String date, String s) {
		if (isEmpty(date)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(s);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public static Long parseLong(Object o) {
		if (o != null) {
			if (o.toString() != "null") {
				return Long.parseLong(o.toString());
			} else {
				return null;
			}

		} else {
			return null;
		}
	}

	public static String parseString(Object o) {
		if (o != null) {
			if (o.toString() != "null") {
				return o.toString();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Integer parseInteger(Object o) {
		if (o != null) {
			if (o.toString() != "null") {
				return Integer.parseInt(o.toString());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Double parseDouble(Object o) {
		if (o != null) {
			if (o.toString() != "null") {
				return Double.parseDouble(o.toString());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public static String listToString(List<Long> ids) {
		if (ids.isEmpty()) {
			return "";
		}
		StringBuffer idsString = new StringBuffer();
		for (Long id : ids) {
			idsString.append(",").append(id);
		}
		return idsString.substring(1);
	}

	public static Double getAgencyAccount(Double dzfdj, String dzzqmc) {
		Double fee = 0.0;
		if ("年付".equals(dzzqmc)) {
			fee = dzfdj * 12;
		} else if ("半年付".equals(dzzqmc)) {
			fee = dzfdj * 6;
		} else if ("季付".equals(dzzqmc)) {
			fee = dzfdj * 3;
		} else if ("月付".equals(dzzqmc)) {
			fee = dzfdj * 1;
		}
		return fee;
	}

	public static int getMonthNumByDzzq(String dzzqmc) {
		int num = 0;
		if ("年付".equals(dzzqmc)) {
			num = 12;
		} else if ("半年付".equals(dzzqmc)) {
			num = 6;
		} else if ("季付".equals(dzzqmc)) {
			num = 3;
		} else if ("月付".equals(dzzqmc)) {
			num = 1;
		}
		return num;
	}

	public static int str2Int(String str) {
		return str2Int(str, 0);
	}

	public static int str2Int(String str, int defaultValue) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public static boolean str2Boolean(String str) {
		try {
			return Boolean.parseBoolean(str);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 函数功能说明 数字按照格式转换 不足位补0 xurun 2016年5月23日 修改者名字 修改日期 修改内容
	 * 
	 * @param @param num
	 * @param @param str_format
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String numToString(Integer num, String str_format) {
		DecimalFormat df = new DecimalFormat(str_format);
		return df.format(num);
	}

	/**
	 * 函数功能说明 Double 值根据num值保留小数点位 xurun 2016年8月3日 修改者名字 修改日期 修改内容
	 * 
	 * @param @param value
	 * @param @param num
	 * @param @return
	 * @return Double
	 * @throws
	 */
	public static Double formatNum(Double value, Integer num) {
		String str_format = "0";
		if (num == 1) {
			str_format = "0.0";
		} else if (num == 2) {
			str_format = "0.00";
		} else if (num == 3) {
			str_format = "0.000";
		} else if (num == 4) {
			str_format = "0.0000";
		} else if (num == 5) {
			str_format = "0.00000";
		} else if (num == 6) {
			str_format = "0.000000";
		}
		DecimalFormat df = new DecimalFormat(str_format);
		String str = df.format(value);
		return Double.valueOf(str);
	}

	public static String formatNumStr(Double value, Integer num) {
		String str_format = "0";
		if (num == 1) {
			str_format = "0.0";
		} else if (num == 2) {
			str_format = "0.00";
		} else if (num == 3) {
			str_format = "0.000";
		} else if (num == 4) {
			str_format = "0.0000";
		}
		DecimalFormat df = new DecimalFormat(str_format);
		String str = df.format(value);
		return str;
	}

	public static String formatNumStr2(Double value, Integer num) {
		String str_format = "###";
		if (num == 1) {
			str_format = "###.#";
		} else if (num == 2) {
			str_format = "###.##";
		} else if (num == 3) {
			str_format = "###.###";
		} else if (num == 4) {
			str_format = "###.####";
		}
		DecimalFormat df = new DecimalFormat(str_format);
		String str = df.format(value);
		return str;
	}

	public static String formatMoneyStr(Double value) {
		DecimalFormat a = new DecimalFormat("###.##");
		String str = a.format(value);
		return str;
	}

	/**
	 * 从map里面取出key为str的值，判断是否为null,为null则赋null值；不为null时，将取出的值转化为string类型
	 * 
	 * @param map
	 *            目标map
	 * @param str
	 *            map对应的key
	 * @return map根据key取出的value
	 */
	@SuppressWarnings("rawtypes")
	public static String mapStr(Map map, String str) {
		return map.get(str) == null ? null : map.get(str).toString();
	}

	/**
	 * 从map里面取出key为str的值，判断是否为null,为null则赋""值；不为null时，将取出的值转化为string类型
	 * 
	 * @param map
	 *            目标map
	 * @param str
	 *            map对应的key
	 * @return map根据key取出的value
	 */
	@SuppressWarnings("rawtypes")
	public static String mapStr2(Map map, String str) {
		return map.get(str) == null ? "" : map.get(str).toString();
	}

	/**
	 * 从map里面取出key为str的值，判断是否为null,为null则赋0值；不为null时，将取出的值转化为Bigdecimal类型
	 * 
	 * @param map
	 *            目标map
	 * @param str
	 *            map对应的key
	 * @return map根据key取出的value
	 */
	@SuppressWarnings("rawtypes")
	public static BigDecimal mapDecimal(Map map, String str) {
		return map.get(str) == null ? BigDecimal.ZERO : (BigDecimal) map
				.get(str);
	}

	/**
	 * 从map里面取出key为str的值，判断是否为null,为null则赋0值；不为null时，将取出的值转化为Double类型
	 * 
	 * @param map
	 *            目标map
	 * @param key
	 *            map对应的key
	 * @return map根据key取出的value
	 */
	@SuppressWarnings("rawtypes")
	public static Double mapDouble(Map map, String key) {
		return Double.valueOf(Utils.isEmpty(mapStr(map, key)) ? "0" : mapStr(
				map, key));
	}

	/**
	 * 从map里面取出key为str的值，判断是否为null,为null则赋0值；不为null时，将取出的值转化为Double类型
	 * 
	 * @param map
	 *            目标map
	 * @param key
	 *            map对应的key
	 * @return map根据key取出的value
	 */
	@SuppressWarnings("rawtypes")
	public static BigDecimal mapBigDecimal(Map map, String key) {
		return new BigDecimal(Utils.isEmpty(mapStr(map, key)) ? "0" : mapStr(
				map, key));
	}

	public static BigDecimal Double2BigDecimal(Double d) {
		return new BigDecimal(Double.toString(d));
	}

	@SuppressWarnings("rawtypes")
	public static Double mapDoubleToNull(Map map, String key) {
		return Utils.isEmpty(mapStr(map, key)) ? null : Double.valueOf(mapStr(
				map, key));
	}

	@SuppressWarnings("rawtypes")
	public static Double mapDouble(Map map, Long key) {
		return Double.valueOf(map.containsKey(key) ? Utils.isEmpty(map.get(key)
				.toString()) ? "0" : map.get(key).toString() : "0");
	}

	/**
	 * 从map里面取出key为str的值，判断是否为null,为null则赋0值；不为null时，将取出的值转化为Long类型
	 * 
	 * @param map
	 *            目标map
	 * @param str
	 *            map对应的key
	 * @return map根据key取出的value
	 */
	@SuppressWarnings("rawtypes")
	public static Long mapLong(Map map, String str) {
		return Long.valueOf(Utils.isEmpty(mapStr(map, str)) ? "0" : mapStr(map,
				str));
	}

	/**
	 * 从map里面取出key为str的值，判断是否为null,为null则赋0值；不为null时，将取出的值转化为Integer类型
	 * 
	 * @param map
	 *            目标map
	 * @param str
	 *            map对应的key
	 * @return map根据key取出的value
	 */
	@SuppressWarnings("rawtypes")
	public static Integer mapInteger(Map map, String str) {
		return Integer.valueOf(Utils.isEmpty(mapStr(map, str)) ? "0" : mapStr(
				map, str));
	}

	/**
	 * 从map里面取出key为str的值，判断是否为null,为null则赋0值；不为null时，将取出的值转化为Integer类型
	 * 
	 * @param map
	 *            目标map
	 * @param str
	 *            map对应的key
	 * @return map根据key取出的value
	 */
	@SuppressWarnings("rawtypes")
	public static Boolean mapBoolean(Map map, String str) {
		return Boolean.valueOf(mapStr(map, str));
	}

	/**
	 * 函数功能说明 修改者名字 fanzy 修改日期 2016年12月15日 修改内容
	 * 
	 * @param @param map map
	 * @param @param str key
	 * @param @param format 日期格式 * @param @param format2 要转换成的日期格式
	 * @param @return
	 * @return Date
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static String mapDateStr(Map map, String str, String format,
			String format2) {
		Date date = string2Date(mapStr(map, str), format);
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format2);
		return sdf.format(date);
	}

	/**
	 * 函数功能说明 日期格式与要转换的格式一致 修改者名字 fanzy 修改日期 2016年12月15日 修改内容
	 * 
	 * @param @param map map
	 * @param @param str key
	 * @param @param format 日期格式
	 * @param @return
	 * @return Date
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public static String mapDateStr(Map map, String str, String format) {
		return mapDateStr(map, str, format, format);
	}

	@SuppressWarnings("rawtypes")
	public static Date mapDate(Map map, String str) {
		return map.get(str) == null ? null : (Date) map.get(str);
	}

	public static String nullToExcelValue(Object obj) {
		if (obj == null) {
			return "";
		} else if (obj instanceof Double) {
			Double v = Double.parseDouble(obj.toString());
			if (v == 0) {
				return "";
			} else {
				return v.toString();
			}
		} else {
			return obj.toString();
		}
	}

	public static Double addDouble(Double num1, Double num2) {
		if (num1 == null) {
			num1 = 0d;
		}
		if (num2 == null) {
			num2 = 0d;
		}
		return num1 + num2;
	}

	public static Double multipleDouble(Double num1, Double num2) {
		if (num1 == null) {
			num1 = 0d;
		}
		if (num2 == null) {
			num2 = 0d;
		}
		return num1 * num2;
	}

	public static Integer addInteger(Integer num1, Integer num2) {
		if (num1 == null) {
			num1 = 0;
		}
		if (num2 == null) {
			num2 = 0;
		}
		return num1 + num2;
	}

	public static String getDxsz(int num) {
		String str = String.valueOf(num);
		String xh = "";
		Map<String, String> numMap = new HashMap<String, String>();
		Map<Integer, String> dwMap = new HashMap<Integer, String>();
		numMap.put("0", "零");
		numMap.put("1", "一");
		numMap.put("2", "二");
		numMap.put("3", "三");
		numMap.put("4", "四");
		numMap.put("5", "五");
		numMap.put("6", "六");
		numMap.put("7", "七");
		numMap.put("8", "八");
		numMap.put("9", "九");

		numMap.put("10", "十");
		numMap.put("11", "十一");
		numMap.put("12", "十二");
		numMap.put("13", "十三");

		dwMap.put(2, "十");
		dwMap.put(3, "百");
		dwMap.put(4, "千");
		dwMap.put(5, "万");

		while (str.length() > 0) {
			int len = str.length();
			String sz = numMap.get(str.substring(0, 1));
			if (len > 1 || !"零".equals(sz)) {
				xh += sz;
				if (len > 1) {
					xh += dwMap.get(len);
				}
			}
			str = str.substring(1);
		}
		return xh;
	}

	public static String getDxszByNum(int num) {
		String str = String.valueOf(num);
		String xh = "";
		Map<String, String> numMap = new HashMap<String, String>();
		Map<Integer, String> dwMap = new HashMap<Integer, String>();
		numMap.put("0", "零");
		numMap.put("1", "一");
		numMap.put("2", "二");
		numMap.put("3", "三");
		numMap.put("4", "四");
		numMap.put("5", "五");
		numMap.put("6", "六");
		numMap.put("7", "七");
		numMap.put("8", "八");
		numMap.put("9", "九");

		numMap.put("10", "十");
		numMap.put("11", "十一");
		numMap.put("12", "十二");
		numMap.put("13", "十三");

		numMap.put("14", "十四");
		numMap.put("15", "十五");
		numMap.put("16", "十六");
		numMap.put("17", "十七");

		numMap.put("18", "十八");
		numMap.put("19", "九");

		dwMap.put(2, "十");
		dwMap.put(3, "百");
		dwMap.put(4, "千");
		dwMap.put(5, "万");

		if (num < 20) {
			xh = numMap.get(str);
		} else {
			while (str.length() > 0) {
				int len = str.length();
				String sz = "";
				if (num < 20) {
					sz = numMap.get(str);
				} else {
					sz = numMap.get(str.substring(0, 1));
				}

				if (num >= 20 && len > 1 || !"零".equals(sz)) {
					xh += sz;
					if (len > 1) {
						xh += dwMap.get(len);
					}
				}
				str = str.substring(1);
			}
		}

		return xh;
	}

	public static String getMailList(String[] mailArray) {
		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}

			}
		}
		return toList.toString();

	}

	public static String handleNullStr(String str) {
		return (str == null || "null".equals(str)) ? "" : str;
	}

	public static String Double2Strlim2(Double d) {
		if (d == null || d == 0) {
			return "0";
		} else {
			DecimalFormat df = new DecimalFormat("######0.00");
			return df.format(d);
		}
	}

	/**
	 * 函数功能说明 比较2个map，如果值相同，则将map值替换为空 修改者名字 fanzy 修改日期 2016年11月8日 修改内容
	 * 
	 * @param @param compMap 存放临时值的map
	 * @param @param map 要替换的map
	 * @param @param keys 要比较的key
	 * @return void
	 * @throws
	 */
	public static void mergeSameValue(Map<String, Object> compMap,
			Map<String, Object> map, String keys) {
		String[] keyset = keys.split(",");
		boolean flag = false;
		for (String key : keyset) {
			if (Utils.handleNullStr(Utils.mapStr(compMap, key)).equals(
					Utils.handleNullStr(Utils.mapStr(map, key)))) {
				if (!flag) {
					map.put(key, "");
				}
			} else {
				compMap.put(key, Utils.mapStr(map, key));
				flag = true;
			}
		}
	}

	public static String formatDoubleZero(double d) {
		if (d == 0) {
			return "";
		} else if (d % 1.0 == 0) {
			return String.valueOf((long) d);
		} else {
			return String.valueOf(d);
		}
	}

	public static String formatDoubleZero2(double d) {
		if (d == 0) {
			return "0";
		} else if (d % 1.0 == 0) {
			return String.valueOf((long) d);
		} else {
			return String.valueOf(d);
		}
	}

	/**
	 * 将任意vo转化成map
	 * 
	 * @param t
	 *            vo对象
	 * @return
	 */
	public static <T> Map<String, Object> convert2Map(T t) {
		Map<String, Object> result = new HashMap<String, Object>();
		Method[] methods = t.getClass().getMethods();
		try {
			for (Method method : methods) {
				Class<?>[] paramClass = method.getParameterTypes();
				if (paramClass.length > 0) { // 如果方法带参数，则跳过
					continue;
				}
				String methodName = method.getName();
				if (methodName.startsWith("get")) {
					Object value = method.invoke(t);
					result.put(methodName, value);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}


	public static String date2String(Date date, String formate) {
		try {
			SimpleDateFormat sDateFormat = new SimpleDateFormat(formate);
			return sDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	/**
	* 函数功能说明	将list中的字段拼接成split分隔的字符串
	* fannzy  2017年9月29日
	* 修改者名字 修改日期
	* 修改内容
	* @param @param list
	* @param @param key
	* @param @param split
	* @param @return    
	* @return String   
	* @throws
	*/
	public static String listStr(List<Map<String, Object>> list,
			String key, String split) {
		StringBuffer sb = new StringBuffer("");
		if(Utils.isNotEmpty(list)){
			for(Map<String, Object> map : list){
				sb.append(split).append(Utils.mapStr2(map, key));
			}
		}
		if(Utils.isNotEmpty(sb.toString())){
			return sb.substring(1);
		}
		return sb.toString();
	}
/*
    *//**
     * 将 json 中 指定key放置到list中
     * @param json json 字符串
     * @param key
     * @param cls
     * @param <T>
     * @return
     *//*
	public static <T> List<T> toList(String json, String key, Class<T> cls) {
		return JSON.parseObject(json).getJSONArray(key).toJavaList(cls);
	}*/

	/**
	 * 将 json 转换成 Map<String, Object>
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(String json) {
		return JSONObject.parseObject(json, new TypeReference<Map<String, Object>>(){});
	}

	public static <T> T toObject(String args, Class<T> clz) {
		return JSONObject.parseObject(args, clz);
	}
}
