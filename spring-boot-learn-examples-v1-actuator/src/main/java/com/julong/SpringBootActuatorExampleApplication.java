package com.julong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Actuator springboot的执行器 监视和管理应用
 * 通过  http://localhost:9999/beans 访问已经注入的bean有哪些
 * 
 * /env/{name:.*} 查看配置属性
 * /env || /env.json 查看配置属性
 * /metrics/{name:.*} 运行时度量
 * /metrics || /metrics.json
 * /heapdump || /heapdump.json
 * /info || /info.json 发布的应用信息
 * /beans || /beans.json 已注册的beans
 * /health || /health.json 监控健康状态
 * /mappings || /mappings.json 控制器的映射信息
 * /autoconfig || /autoconfig.json 自动配置的类
 * /configprops || /configprops.json 配置的文件
 * /trace || /trace.json web追踪请求
 * /dump || /dump.json 导出当前线程快照
 * @author julong
 * @date 2020年2月16日 下午7:38:33
 * @desc 
 */
@SpringBootApplication
public class SpringBootActuatorExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActuatorExampleApplication.class, args);
	}

}
