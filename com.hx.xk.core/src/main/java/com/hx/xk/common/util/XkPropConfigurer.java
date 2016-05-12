/**
 * 
 */
package com.hx.xk.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
/**
 *  
 * @author Hasan
 * @date 2014-1-2 下午5:06:52 
 *
 */
public class XkPropConfigurer extends
		PropertyPlaceholderConfigurer {

	private static Map<String, Object> properties;

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		properties = new HashMap<String, Object>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			properties.put(keyStr, value);
		}
	}

	public static Object getContextProperty(String name) {
		return properties.get(name);
	}


	public static String getProp(String name) {
		return properties.get(name)!=null?properties.get(name).toString():null;
	}
	public static Integer getIntvalueByProp(String name) {
		int re=0;
		if(properties.get(name)!=null){
			try {
				re=Integer.parseInt(properties.get(name).toString());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return re;
	}
	public static String getProp(String name,String def) {
		return properties.get(name)!=null?properties.get(name).toString():def;
	}
}
