package com.julong.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.julong.dao.entity.CityInfo;

/**
 * Rest模板    --- 城市模块
 * @author julong
 * @date 2020年3月10日 下午8:12:22
 * @desc 		
 * //必须遵循给定的点来使用API - 
   // 自动装配Rest模板对象。使用HttpHeaders设置请求标头。
   // 使用HttpEntity包装请求对象。为Exchange()方法提供URL，HttpMethod和Return类型。
 */
@RestController
public class CityAPI {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 查询集合信息
	 * @return
	 * @author julong
	 * @date 2020年3月10日 下午8:12:42
	 * @desc
	 */
	@RequestMapping("/api/citys")
	public String getCityList(){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange("http://localhost:8080/citys", HttpMethod.GET, entity, String.class).getBody();
	}
	
	/**
	 * 根据编号查询
	 * @param cityId
	 * @return
	 * @author julong
	 * @date 2020年3月10日 下午8:13:30
	 * @desc
	 */
	@RequestMapping("/api/city/{cityId}")
	public String getCity(@PathVariable("cityId") int cityId){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<CityInfo> entity = new HttpEntity<CityInfo>(headers);
		return restTemplate.exchange("http://localhost:8080/city/"+cityId, HttpMethod.GET, entity, String.class).getBody();
	}
	
	
}
