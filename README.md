# 				spring-boot-learn-examples
此文件 为 自己 学习spring- boot 全家桶 的例子 



### 单体应用：

​	多机单服务 采用负载均衡技术实现 ，改动复杂牵一发动全身

### 微服务应用：

​	模块功能进行服务封装，每一个服务为可独立替换升级的服务单元



JDK版本 1.7+

spring-boot 版本 1.4.7+

## spring-boot-learn-examples

#### springBoot 自动配置原理

1. SpringBoot启动的时候加载主配置类，通过 @EnableAutoConfiguration 来进行自动配置

2.  @EnableAutoConfiguration作用：

   ```java
   @SuppressWarnings("deprecation")
   @Target(ElementType.TYPE)
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @Inherited
   @AutoConfigurationPackage
   @Import(EnableAutoConfigurationImportSelector.class)
   public @interface EnableAutoConfiguration {
   
   	String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";
   
   	/**
   	 * Exclude specific auto-configuration classes such that they will never be applied.
   	 * @return the classes to exclude
   	 */
   	Class<?>[] exclude() default {};
   
   	/**
   	 * Exclude specific auto-configuration class names such that they will never be
   	 * applied.
   	 * @return the class names to exclude
   	 * @since 1.3.0
   	 */
   	String[] excludeName() default {};
   
   }
   ```

   1. 利用 EnableAutoConfigurationImportSelector  类给容器中导入组件 

   2. 通过AutoConfigurationImportSelector类中的方法 selectImports 进行插件扫描
   3. getCandidateConfigurations(annotationMetadata,attributes);获取候选的配置 "META-INF/spring.factories"

   HttpEncodingAutoConfiguration 配置解释

```java
@Configuration //标识为一个配置类
@EnableConfigurationProperties(HttpEncodingProperties.class) //启动类指定的HttpEncodingProperties功能，将配置文件中的值和 HttpEncodingProperties绑定
@ConditionalOnWebApplication //spring 底层 @Conditiona 注解 根据不同的条件如果满足条件则配置生效 此注解判断当前是否为 web应用 如果是 当前配置生效
@ConditionalOnClass(CharacterEncodingFilter.class) //判断当前是否存在 CharacterEncodingFilter 类 
@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)// 判断配置文件中是否存在某一个配置  spring.http.encoding.enabled 如果不存在 判断也是成立的 
public class HttpEncodingAutoConfiguration {
    
    //已经映射的配置
    private final HttpEncodingProperties properties;

	public HttpEncodingAutoConfiguration(HttpEncodingProperties properties) {
		this.properties = properties;
	}

	@Bean //容器中添加一个组件 
	@ConditionalOnMissingBean(CharacterEncodingFilter.class)
	public CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
		filter.setEncoding(this.properties.getCharset().name());
		filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
		filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
		return filter;
	}
```

HttpEncodingProperties 类

```java
/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure.web;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for http encoding.
 *
 * @author Stephane Nicoll
 * @author Brian Clozel
 * @since 1.2.0
 */
@ConfigurationProperties(prefix = "spring.http.encoding")
public class HttpEncodingProperties {

	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	/**
	 * Charset of HTTP requests and responses. Added to the "Content-Type" header if not
	 * set explicitly.
	 */
	private Charset charset = DEFAULT_CHARSET;

	/**
	 * Force the encoding to the configured charset on HTTP requests and responses.
	 */
	private Boolean force;

	/**
	 * Force the encoding to the configured charset on HTTP requests. Defaults to true
	 * when "force" has not been specified.
	 */
	private Boolean forceRequest;

	/**
	 * Force the encoding to the configured charset on HTTP responses.
	 */
	private Boolean forceResponse;

	/**
	 * Locale to Encoding mapping.
	 */
	private Map<Locale, Charset> mapping;

	public Charset getCharset() {
		return this.charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public boolean isForce() {
		return Boolean.TRUE.equals(this.force);
	}

	public void setForce(boolean force) {
		this.force = force;
	}

	public boolean isForceRequest() {
		return Boolean.TRUE.equals(this.forceRequest);
	}

	public void setForceRequest(boolean forceRequest) {
		this.forceRequest = forceRequest;
	}

	public boolean isForceResponse() {
		return Boolean.TRUE.equals(this.forceResponse);
	}

	public void setForceResponse(boolean forceResponse) {
		this.forceResponse = forceResponse;
	}

	public Map<Locale, Charset> getMapping() {
		return this.mapping;
	}

	public void setMapping(Map<Locale, Charset> mapping) {
		this.mapping = mapping;
	}

	boolean shouldForce(Type type) {
		Boolean force = (type == Type.REQUEST ? this.forceRequest : this.forceResponse);
		if (force == null) {
			force = this.force;
		}
		if (force == null) {
			force = (type == Type.REQUEST);
		}
		return force;
	}

	enum Type {

		REQUEST, RESPONSE

	}

}

```





## spring-boot-learn-examples-v1- jar

#### 以jar 的方式打包项目 

```xml
<groupId>com.julong</groupId>
<artifactId>spring-boot-learn-examples-v1-jar</artifactId>
<version>0.0.1-SNAPSHOT</version>
<packaging>jar</packaging>
<description>spring-boot-learn-examples-v1-jar</description>
```

增加 <packaging>jar</packaging> 属性 即可

#### 打包方式

![image-20230227214037621](D:\Workspaces\OWER\spring-boot-learn-examples-v1\images\image-20230227214037621.png)

![image-20230227214142681](D:\Workspaces\OWER\spring-boot-learn-examples-v1\images\image-20230227214142681.png)

### 入门

#### 导入项目依赖 pom.xml

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.4.7.RELEASE</version>
</parent>
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

#### 启动注解

```java
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="com.julong") //需要扫描的包
```

等同于

```java
@SpringBootApplication
```

以jar的方式运行的项目 此方式自带tomcat 

使用命令 java -jar 运行

#### spring boot版本控制仲裁中心

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.4.7.RELEASE</version>
</parent>
它的父项目
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>1.4.7.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
</parent>
```

项目导入依赖，必须有此依赖 web项目正常依赖的所有依赖都存在于此依赖中

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

#### 主程序入库

程序启动类

```java
package com.julong.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * springboot 启动文件
 * @author julong
 * @date 2020年1月16日 下午5:25:52
 * @desc 
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="com.julong") //需要扫描的包
public class SpringBootApplicationStarter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SpringBootApplicationStarter.class, args);
	}

}

```



##### @SpringBootApplication

Spring Boot 应用标注在某一个类上说明这个类是 SpringBoot 的主配置类

```java
/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.AliasFor;

/**
 * Indicates a {@link Configuration configuration} class that declares one or more
 * {@link Bean @Bean} methods and also triggers {@link EnableAutoConfiguration
 * auto-configuration} and {@link ComponentScan component scanning}. This is a convenience
 * annotation that is equivalent to declaring {@code @Configuration},
 * {@code @EnableAutoConfiguration} and {@code @ComponentScan}.
 *
 * @author Phillip Webb
 * @author Stephane Nicoll
 * @since 1.2.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class))
public @interface SpringBootApplication {

	/**
	 * Exclude specific auto-configuration classes such that they will never be applied.
	 * @return the classes to exclude
	 */
	Class<?>[] exclude() default {};

	/**
	 * Exclude specific auto-configuration class names such that they will never be
	 * applied.
	 * @return the class names to exclude
	 * @since 1.3.0
	 */
	String[] excludeName() default {};

	/**
	 * Base packages to scan for annotated components. Use {@link #scanBasePackageClasses}
	 * for a type-safe alternative to String-based package names.
	 * @return base packages to scan
	 * @since 1.3.0
	 */
	@AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
	String[] scanBasePackages() default {};

	/**
	 * Type-safe alternative to {@link #scanBasePackages} for specifying the packages to
	 * scan for annotated components. The package of each class specified will be scanned.
	 * <p>
	 * Consider creating a special no-op marker class or interface in each package that
	 * serves no purpose other than being referenced by this attribute.
	 * @return base packages to scan
	 * @since 1.3.0
	 */
	@AliasFor(annotation = ComponentScan.class, attribute = "basePackageClasses")
	Class<?>[] scanBasePackageClasses() default {};

}

```

##### @SpringBootConfiguration

Spring Boot 的配置类

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface SpringBootConfiguration {

}
```

此注解标注在类上标识此类为spring boot 配置类

##### @EnableAutoConfiguration

​	Spring Boot 的自动配置功能

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {

}
```

##### @AutoConfigurationPackage

将主配置类 main 方法 所在包以及附属包下的所有的扫描到配置中



## spring-boot-learn-examples-v1-config

### 配置文件

springBoot 使用全局配置文件 ，配置文件名是固定的 

​	application.prpoerties

​	application.yml

YAML 为标记语言 以数据为中心 

#### yaml基本语法

K：(空格)V ：表示一对键值对 其中空格必须有；

以空格的缩进方式来进行控制层级关系

属性和值大小写敏感

```yam
### 项目名称
spring:
  application:
    name: spring-boot-learn-examples-v1-config
server:
  port: 8080                #端口号
  
debug: true                 #是否启用debug模式
logging:
  level:
    root: debug

```



属性值的写法

```yaml

user:
  userName: zhangsan
  userAge: 15
  userSex: true
  userBirth: 2019/01/01 # 日期类型不可用 2019-09-09 这种形式
  maps:
    1: julong
    2: zhangsan
  list:
    - wangwu
    - zhaoliu
    
  ##行内写法示例
user1: {name: zhangsan,age: 30}
```



#### 类注入容器

```java
@Component //加载到容器中
@ConfigurationProperties(prefix = "user") 
```



执行结果：

```txt
User [userName=zhangsan, userAge=15, userBirth=Tue Jan 01 00:00:00 CST 2019, userSex=true, maps={1=julong, 2=zhangsan}, list=[wangwu, zhaoliu]]

```

获取值的另一种方式：

```java
//	@Value("${userName}") //此种方式 类似于 配置文件中的 <bean class="user"><property name="userName" value="aaa" /> </bean>
	private String userName;
```

#### @ConfigurationProperties与@Value比较

|                | @ConfigurationProperties | @Value       |
| -------------- | ------------------------ | ------------ |
| 功能           | 批量属性注入             | 一个一个绑定 |
| 松散语法       | 支持                     | 不支持       |
| SpEL语法       | 不支持                   | 支持         |
| JSR303数据校验 | 支持                     | 不支持       |
| 复杂数据类型   | 支持                     | 不支持       |

如果说：我们只是获取值可以用@Value 实现

如果说：专门编写一个javaBean来配置文件进行映射使用 @ConfigurationProperties

#### 数据校验

```java
package com.julong.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 
 * @author julong
 * @date 2020年3月7日 下午11:09:31
 * @desc @ConfigurationProperties 此种方式为 对赋值的方式 支持松散绑定 类似 user-name 对应类中的 userName
 */
@Component //加载到容器中
@ConfigurationProperties(prefix = "user") 
@Validated //JSR303数据校验
public class User {

//	@Value("${userName}") //此种方式 类似于 配置文件中的 <bean class="user"><property name="userName" value="aaa" /> </bean>
	private String userName;
	
//	@Email  // 数据格式校验
	private int userAge;
	
	private Date userBirth;
	
	
	private boolean userSex;
	
	private Map<String,Object> maps ;
	
	private List<String> list;

	...省略getter和setter方法
}

```



#### @PropertySource

加载指定属性配置文件

#### @ImportResource

导入spring配置文件

## spring-boot-learn-examples-v1-war

以war的形式运行的项目 此方式打包或者编译会自动剔除tomcat 如果放在独立的tomcat webapp目录下 访问需要带项目名称

### pom文件引入配置

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!-- 编译的时候去掉 tomcat -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```

### 启动类配置

```java
package com.julong.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * springboot 启动文件
 * @author julong
 * @date 2020年1月16日 下午5:25:52
 * @desc SpringBootServletInitializer 如果使用war包 部署 需要引入 并且将此启动类作为源文件映射进去
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="com.julong")//扫描的包
public class SpringBootApplicationStarter extends SpringBootServletInitializer{

	

	/* (non-Javadoc) 需要扩展类SpringBootServletInitializer以支持WAR文件部署
	 * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SpringBootApplicationStarter.class);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SpringBootApplicationStarter.class, args);
	}

}

```



## spring-boot-learn-examples-v1-servlet

#### 注册三大组件

```java
package com.julong.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.julong.filter.MyFilter;
import com.julong.listener.MyListener;
import com.julong.servlet.MyServlet;

/**
 * 注册三大组件
 * @author julong
 * @date 2023年2月15日 上午10:29:44
 * @desc 
 */
@Configuration
public class MyServerConfig {

	
	/**
	 * 注册servlet
	 * @return
	 * @author julong
	 * @date 2023年2月15日 上午10:29:49
	 * @desc
	 */
	@Bean
	public ServletRegistrationBean mySerlvet(){
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
		return servletRegistrationBean;
	}
	
	/**
	 * 注册filter
	 * @return
	 * @author julong
	 * @date 2023年2月15日 上午10:39:49
	 * @desc
	 */
	@Bean
	public  FilterRegistrationBean myFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new MyFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/myServlet"));
		return filterRegistrationBean;
	}
	
	
	/**
	 * 注册监听器
	 * @return
	 * @author julong
	 * @date 2023年2月15日 上午10:54:49
	 * @desc
	 */
	@Bean
	public ServletListenerRegistrationBean<MyListener> myListener(){
		ServletListenerRegistrationBean<MyListener> listenerRegistration = new ServletListenerRegistrationBean<MyListener>(new MyListener());
		return listenerRegistration;
	}
}

```



## spring-boot-learn-examples-v1-jdbc

使用JDBC调用数据库连接 其实和spring jdbc一样的方式 ，本示例实现了简单的CRUD操作

#### pom 引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

#### application.properties配置

```properties

spring.application.name=spring-boot-learn-examples-v1-jdbc
###jdbc数据库配置
### 数据库连接地址URL
spring.datasource.url=jdbc:mysql://localhost:3306/test
### 数据库驱动名称
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
### 连接账号
spring.datasource.username=root
### 连接密码
spring.datasource.password=root
### 数据源名称
#spring.datasource.name=
### 数据DML脚本资源名称
#spring.datasource.data=
### schema DDL数据资源脚本名称
#spring.datasource.schema=
### 是否执行 data.sql脚本 默认为true
#spring.datasource.initialize=
### 读取外部sql脚本的字符集 
#spring.datasource.sql-script-encoding=
### 读取 schema 所使用的的平台
#spring.datasource.platform=
### 如果初始化失败是否要继续 默认为false
#spring.datasource.continue-on-error=
### sql脚本的分割符 默认 为 ；
#spring.datasource.separator=
### 数据源的名称
#spring.datasource.jndi-name=
### 最大活跃数 默认为
#spring.datasource.dbcp.max-active=
### 最大闲置连接数量
#spring.datasource.dbcp.max-idle=
### 最小闲置连接数量
#spring.datasource.dbcp.min-idle=
### 连接池的初始大小
#spring.datasource.dbcp.initial-size=
### 用来验证数据连接是否成功的查询语句
#spring.datasource.dbcp.validation-query=
### 从连接池借用连接时候是否需要检查连接 默认为false
#spring.datasource.dbcp.test-on-borrow=
### 向连接池归还连接的时候是否需要检查连接 默认为false
#spring.datasource.dbcp.test-on-return=
### 连接空闲的时候是否需要测试连接 默认为false
#spring.datasource.dbcp.test-while-idle=
### 多久可以清理一次数据库连接 默认为 5000毫秒
#spring.datasource.dbcp.time-between-eviction-runs-millis=
### 连接被清理之前 连接最多可以空闲多久 默认为 60000毫秒
#spring.datasource.dbcp.min-evictable-idle-time-millis=
### 最大等待时间 默认为 30000毫秒
#spring.datasource.dbcp.max-wait=
### 连接池是否可以通过jmx来进行管理 默认为false
#spring.datasource.jmx-enabled=

server.port=8080


```

#### 基于jdbc的操作

```java
package com.julong.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.julong.dao.UserDao;
import com.julong.dao.entity.UserInfo;
import com.julong.service.dto.UserDTO;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserDTO getUser(String userName) throws Exception {
		// TODO Auto-generated method stub
		UserDTO userDTO = this.jdbcTemplate.query("select * from user where user_name = '"+userName+"'", new ResultSetExtractor<UserDTO>(){
			@Override
			public UserDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				UserDTO user = new UserDTO();
				while (rs.next()) {
					user.setUserName(rs.getString("user_name"));
					user.setUserAge(rs.getInt("user_age"));
					user.setPassword(rs.getString("password"));
					user.setUserCreateTime(rs.getDate("user_create_time"));
				}
				return user;
			}
			
		});
		return userDTO;
	}

	@Override
	public int saveUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.update("insert into user(user_name,password,user_age,user_create_time) "
				+ "value('"+userInfo.getUserName()+"','"+userInfo.getPassword()+"',"+userInfo.getUserAge()+",now())");
	}

	@Override
	public int deleteUser(String userName) throws Exception {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.update("delete from user where user_name= '"+userName+"'");
	}

	@Override
	public int updateUser(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.update("update user set password = '"+userInfo.getPassword()+"' where user_name = '"+userInfo.getUserName()+"'");
	}


}

```

主要引用 注解配置

​    @Autowired
​	private JdbcTemplate jdbcTemplate; 



## spring-boot-learn-examples-v1-data-jpa

使用spring-data-jpa 的方式实现数据库操作，本示例实现了简单的CRUD操作

#### pom引入配置

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

#### application.properties配置

```pr
spring.application.name=spring-boot-learn-examples-v1-data-jpa
server.port=8080

spring.datasource.url=jdbc:mysql://192.168.10.222:3306/test
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver


debug=true


spring.jpa.show-sql=true
spring.data.jpa.repositories.enabled=true
```

#### 实体类配置

注解解释：

```java
package com.julong.dao.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 数据库映射类
 * @author julong
 * @date 2020年2月17日 下午10:35:13
 * @desc 
 */
@Entity
@Table(name="city") //指定对应的表结构
public class CityInfo implements Serializable{

	/**
	 * @author julong
	 * @date 2020年2月17日 下午8:53:16
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 * @author julong
	 * @date 2020年2月17日 下午8:51:54
	 */
	@Id //主键
	@GeneratedValue //自动序列
	@Column(name="city_id") //对应的列对应的数据库的列
	private int cityId;
	
	/**
	 * 城市名称
	 * @author julong
	 * @date 2020年2月17日 下午8:52:04
	 */
	@Column(name="city")
	private String city;
	
	/**
	 * 国家编号
	 * @author julong
	 * @date 2020年2月17日 下午8:52:21
	 */
	@Column(name="country_id")
	private int countryId;
	
	/**
	 * 最后更新时间
	 * @author julong
	 * @date 2020年2月17日 下午8:52:41
	 */
	@Column(name="last_update")
	private Date lastUpdate;

	/**
	 * @return int the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return String the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return int the countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return Date the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CityInfo [cityId=" + cityId + ", city=" + city + ", countryId="
				+ countryId + ", lastUpdate=" + lastUpdate + "]";
	}

}

```

#### 日志配置log4j2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARE" name="log4j2-example" packages="">
	  <Properties>
	    <Property name="baseDir">./logs</Property>
	  </Properties>
	<!-- 打印到控制台的日志 -->
	<Appenders>
		<!-- 控制台打印 -->
		<!-- target Either "SYSTEM_OUT" or "SYSTEM_ERR". The default is "SYSTEM_OUT". -->
		<Console name="STDOUT" target="SYSTEM_OUT">
			<PatternLayout>
				<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50}	- %msg%n</pattern>
			</PatternLayout>
		</Console>
		<!-- 生产环境打印方式 -->
		<RollingFile name="FILE_INFO" fileName="${baseDir}/info.log" filePattern="${baseDir}/$${date:yyyy-MM}/app-%d{yyyy-MM-dd-HH}.info.gz">
			<PatternLayout>
				<Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50}	- %msg%n</Pattern>
			</PatternLayout>
		   	<!-- 打印级别 -->
			<ThresholdFilter level="INFO" onMatch="ACCEPT" onMismatch="DENY"/>
			<Policies>
				<!-- 参数配置为1 标识一小时一个文件  interval 此处填写 几就是 几小时滚动一次-->
				<TimeBasedTriggeringPolicy interval="1" modulate="true"/>
				<!-- 每个文件的大小  如果使用这个配置 如果和时间的配置一起使用 需要在 生成的文件上加入 %i  参数 用户日志产生序列 -->
				<!-- <TimeBasedTriggeringPolicy />
				<SizeBasedTriggeringPolicy size="100 KB" /> -->
			</Policies>
		</RollingFile>
		<RollingFile name="FILE_ERROR" fileName="${baseDir}/error.log" filePattern="${baseDir}/$${date:yyyy-MM}/app-%d{yyyy-MM-dd-HH}.error.gz">
			<PatternLayout>
				<Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50}	- %msg%n</Pattern>
			</PatternLayout>
			<!-- 打印级别 -->
			 <ThresholdFilter level="ERROR" onMatch="ACCEPT" onMismatch="DENY"/>
			<Policies>
				<!-- 参数配置为1 标识一小时一个文件 -->
				<TimeBasedTriggeringPolicy interval="1" modulate="true"/>
			</Policies>
		</RollingFile>
	</Appenders>
	<Loggers>
		<!-- 打印debug日志 -->
		<Root level="DEBUG">
			<AppenderRef ref="STDOUT" />
			<!-- 生产环境放开此配置-->
			<AppenderRef ref="FILE_INFO" />
			<AppenderRef ref="FILE_ERROR" /> 
		</Root>
	</Loggers>
</Configuration>

```







## spring-boot-learn-examples-v1-profiles

使用多个配置文件来区分环境，例如 开发 测试 生产 

#### application-{profile}.properties

application.properties

​	application-prod.properties

​	application-dev.properties

```properties
# Application name. spring 项目的名称
spring.application.name=spring-boot-learn-examples-v1-profiles

#通过这个参数来配置加载的配置文件 可以切换开发环境和生产环境 如： dev or prod
spring.profiles.active=prod

```

#### application.yml写法

```yam
# Application name. spring \u9879\u76ee\u7684\u540d\u79f0
spring: 
  application:
    name: spring-boot-learn-examples-v1-profiles

  profiles:
    active: prod


---
server:
  port: 9090
spring:
  profiles:
    active: dev
    
 
---
server:
  port: 9091
spring:
  profiles:
    active: prod
    
   
```



#### 配置文件加载优先级

```txt
1.A /config subdirectory of the current directory.  -file:./config/
2.The current directory								-file:./
3.A classpath /config package						-classpath:/config/
4.The classpath root								-classpath:/
```
优先级从高到低顺序 高优先级的配置会覆盖低优先级的配置

1.命令行参数

```txt
java -jar myproject.jar --server.port=8088
```

多个参数配置用空格隔开

2.JNDI attributes from java:comp/env. JDNI属性

3.Java System properties (System.getProperties()). java 系统属性

4.OS environment variables. 操作系统环境变量配置

5.A RandomValuePropertySource that only has properties in random.*.  

​	RandomValuePropertySource  配置的 random.* 属性

6.Profile-specific application properties outside of your packaged jar (application-{profile}.properties and YAML variants)

​	jar包外部的 配置文件 带 profiles的配置文件

7.Profile-specific application properties packaged inside your jar (application-{profile}.properties and YAML variants)

​	jar包内部的 配置文件 带 profiles的配置文件

8.Application properties outside of your packaged jar (application.properties and YAML variants).

9.Application properties packaged inside your jar (application.properties and YAML variants)



## spring-boot-learn-examples-v1-log4j

#### 使用log4j来进行日志记录功能

spring boot 默认排除 commons-logging

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <version>1.4.7.RELEASE</version>
    <exclusions>
        <exclusion>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

#### log4j.properties配置

```properties
# LOGGING
# Location of the logging configuration file. For instance `classpath:logback.xml` for Logback
logging.config= 
# Conversion word used when logging exceptions.
logging.exception-conversion-word=%wEx 
# Log file name. For instance `myapp.log` 
# 指定文件名 和存储路径
logging.file=
# Log levels severity mapping. For instance `logging.level.org.springframework=DEBUG`
# 日志级别
logging.level.*= 
# Location of the log file. For instance `/var/log` 
# 在当前磁盘下创建文件夹 使用默认文件名作为日志文件
logging.path= /var/log
# Appender pattern for output to the console. Only supported with the default logback setup. 控制台输出的日志格式
logging.pattern.console=
# Appender pattern for output to the file. Only supported with the default logback setup.
# 在指定文件中输出的日志格式
logging.pattern.file= 
# Appender pattern for log level (default %5p). Only supported with the default logback setup.日志级别
logging.pattern.level= 
# Register a shutdown hook for the logging system when it is initialized.
logging.register-shutdown-hook=false 

```

#### 引入日志文件

```java
package com.julong.main;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 引入log4j日志模块
 * @author julong
 * @date 2020年2月12日 下午9:54:00
 * @desc 
 */
@Configuration
@ConfigurationProperties("classpath:log4j.properties")//引入 log4j 配置文件
public class Log4jConfiguration {

}

```



## spring-boot-learn-examples-v1-log4j2

使用log4j2日志记录功能

#### pom文件引入配置

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

#### log4j2-spring.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARE" name="log4j2-example" packages="">
	  <Properties>
	    <Property name="baseDir">./logs</Property>
	  </Properties>
	<!-- 打印到控制台的日志 -->
	<Appenders>
		<!-- 控制台打印 -->
		<!-- target Either "SYSTEM_OUT" or "SYSTEM_ERR". The default is "SYSTEM_OUT". -->
		<Console name="STDOUT" target="SYSTEM_OUT">
			<PatternLayout>
				<Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS}  [%thread] %-5level %logger{50}	- %msg%n</Pattern>
			</PatternLayout>
		</Console>
		<!-- 生产环境打印方式 -->
		<RollingFile name="FILE_INFO" fileName="${baseDir}/info.log" filePattern="${baseDir}/$${date:yyyy-MM}/app-%d{yyyy-MM-dd-HH}.info.gz">
			<PatternLayout>
				<Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50}	- %msg%n</Pattern>
			</PatternLayout>
		   	<!-- 打印级别 -->
			<ThresholdFilter level="INFO" onMatch="ACCEPT" onMismatch="DENY"/>
			<Policies>
				<!-- 参数配置为1 标识一小时一个文件  interval 此处填写 几就是 几小时滚动一次-->
				<TimeBasedTriggeringPolicy interval="1" modulate="true"/>
				<!-- 每个文件的大小  如果使用这个配置 如果和时间的配置一起使用 需要在 生成的文件上加入 %i  参数 用户日志产生序列 -->
				<!-- <TimeBasedTriggeringPolicy />
				<SizeBasedTriggeringPolicy size="100 KB" /> -->
			</Policies>
		</RollingFile>
		<RollingFile name="FILE_ERROR" fileName="${baseDir}/error.log" filePattern="${baseDir}/$${date:yyyy-MM}/app-%d{yyyy-MM-dd-HH}.error.gz">
			<PatternLayout>
				<Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50}	- %msg%n</Pattern>
			</PatternLayout>
			<!-- 打印级别 -->
			 <ThresholdFilter level="ERROR" onMatch="ACCEPT" onMismatch="DENY"/>
			<Policies>
				<!-- 参数配置为1 标识一小时一个文件 -->
				<TimeBasedTriggeringPolicy interval="1" modulate="true"/>
			</Policies>
		</RollingFile>
	</Appenders>
	<Loggers>
		<!-- 打印debug日志 -->
		<Root level="DEBUG">
			<AppenderRef ref="STDOUT" />
			<!-- 生产环境放开此配置-->
			<AppenderRef ref="FILE_INFO" />
			<AppenderRef ref="FILE_ERROR" /> 
		</Root>
	</Loggers>
</Configuration>

```



## spring-boot-learn-examples-v1-exception

错误信息的定义和处理

配置模板后 访问 resources/templates/error

未配置模板访问 resources/error



springboot 中自动配置 ErrorMvcAutoConfiguration 使用此类

#### ErrorMvcAutoConfiguration

```java
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
// Load before the main WebMvcAutoConfiguration so that the error View is available
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(ResourceProperties.class)
public class ErrorMvcAutoConfiguration {

    //默认错误属性
    @Bean
	@ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
	public DefaultErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes();
	}

    //错误处理控制器
	@Bean
	@ConditionalOnMissingBean(value = ErrorController.class, search = SearchStrategy.CURRENT)
	public BasicErrorController basicErrorController(ErrorAttributes errorAttributes) {
		return new BasicErrorController(errorAttributes, this.serverProperties.getError(),
				this.errorViewResolvers);
	}
    
    //找不到页面会显示以下方法拼接的信息
    @Configuration
	@ConditionalOnProperty(prefix = "server.error.whitelabel", name = "enabled", matchIfMissing = true)
	@Conditional(ErrorTemplateMissingCondition.class)
	protected static class WhitelabelErrorViewConfiguration {

		private final SpelView defaultErrorView = new SpelView(
				"<html><body><h1>Whitelabel Error Page</h1>"
						+ "<p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p>"
						+ "<div id='created'>${timestamp}</div>"
						+ "<div>There was an unexpected error (type=${error}, status=${status}).</div>"
						+ "<div>${message}</div></body></html>");

		@Bean(name = "error")
		@ConditionalOnMissingBean(name = "error")
		public View defaultErrorView() {
			return this.defaultErrorView;
		}

		// If the user adds @EnableWebMvc then the bean name view resolver from
		// WebMvcAutoConfiguration disappears, so add it back in to avoid disappointment.
		@Bean
		@ConditionalOnMissingBean(BeanNameViewResolver.class)
		public BeanNameViewResolver beanNameViewResolver() {
			BeanNameViewResolver resolver = new BeanNameViewResolver();
			resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
			return resolver;
		}

	}
```





#### BasicErrorController

```java
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BasicErrorController extends AbstractErrorController {
    //响应 请求头为 text/html 的请求
    @RequestMapping(produces = "text/html")
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
	}
    
	//响应非页面的请求
	@RequestMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,
				isIncludeStackTrace(request, MediaType.ALL));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Map<String, Object>>(body, status);
	}
```



#### 错误页面存放路径：

```java
"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/"
```

#### DefaultErrorViewResolver

```java
public class DefaultErrorViewResolver implements ErrorViewResolver, Ordered {

	private static final Map<Series, String> SERIES_VIEWS;

	static {
		Map<Series, String> views = new HashMap<Series, String>();
		views.put(Series.CLIENT_ERROR, "4xx"); //默认查找页面 
		views.put(Series.SERVER_ERROR, "5xx"); //默认查找页面 
		SERIES_VIEWS = Collections.unmodifiableMap(views);
	}
    
    
    //运行此方法 
	private ModelAndView resolve(String viewName, Map<String, Object> model) {
		String errorViewName = "error/" + viewName;
		TemplateAvailabilityProvider provider = this.templateAvailabilityProviders
				.getProvider(errorViewName, this.applicationContext);
		if (provider != null) {
			return new ModelAndView(errorViewName, model);
		}
		return resolveResource(errorViewName, model);
	}
	
	private ModelAndView resolveResource(String viewName, Map<String, Object> model) {
		for (String location : this.resourceProperties.getStaticLocations()) {
			try {
				Resource resource = this.applicationContext.getResource(location);
				resource = resource.createRelative(viewName + ".html");
				if (resource.exists()) {
					return new ModelAndView(new HtmlResourceView(resource), model);
				}
			}
			catch (Exception ex) {
			}
		}
		return null;
	}

```



#### 异常信息返回 DefaultErrorAttributes

```java
timestamp - The time that the errors were extracted  时间
status - The status code  状态码
error - The error reason 错误提示
exception - The class name of the root exception 异常对象
message - The exception message 异常消息
errors - Any ObjectErrors from a BindingResult exception JSR303数据校验错误
trace - The exception stack trace 
path - The URL path when the exception was raised 

```

#### 修改状态码

```java
protected HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try {
			return HttpStatus.valueOf(statusCode);
		}
		catch (Exception ex) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}


Integer statusCode = (Integer) request
				.getAttribute("javax.servlet.error.status_code");
```





## spring-boot-learn-examples-v1-logback

使用logback来进行日志记录

#### pom文件引入配置

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <!-- 编译的时候去掉 tomcat 打war包时候使用 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

#### logback.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <!--设置输出格式-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>
	 <!--文件输出,时间窗口滚动-->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--日志名,指定最新的文件名，其他文件名使用FileNamePattern -->
        <File>./logs/info.log</File>
        <!-- 过滤日志级别 -->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
      		<level>INFO</level>
   		</filter>
        <!--文件滚动模式-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名,可设置文件类型为gz,开启文件压缩-->
            <FileNamePattern>./logs/info.%d{yyyy-MM-dd}.%i.log.gz</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>30</MaxHistory>
            <!--按大小分割同一天的-->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
		
        <!--输出格式-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>
	 <!--文件输出,时间窗口滚动-->
    <appender name="FILE_ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--日志名,指定最新的文件名，其他文件名使用FileNamePattern -->
        <File>./logs/error.log</File>
        <!-- 过滤日志级别 -->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
      		<level>ERROR</level>
   		</filter>
        <!--文件滚动模式-->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名,可设置文件类型为gz,开启文件压缩-->
            <FileNamePattern>./logs/error.%d{yyyy-MM-dd}.%i.log.gz</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>30</MaxHistory>
            <!--按大小分割同一天的-->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
		
        <!--输出格式-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>
	<root level="DEBUG">
		<appender-ref ref="console" />
		<appender-ref ref="FILE" />
		<appender-ref ref="FILE_ERROR" />
	</root>
</configuration>
```



## spring-boot-learn-examples-v1-quartz

使用spring-boot中的定时任务,其实和spring中的定时任务是一样的 

## spring-boot-learn-examples-v1-mybatis-pagehelper

使用mybatis和pageHelper实现 数据库访问和分页功能

#### pom文件引入依赖

```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper-spring-boot-starter -->
		<dependency>
			<groupId>com.github.pagehelper</groupId>
			<artifactId>pagehelper-spring-boot-starter</artifactId>
			<version>1.1.0</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.mybatis.spring.boot/mybatis-spring-boot-starter -->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.2.0</version>
		</dependency>

```



#### 分页配置

```properties
spring.application.name=spring-boot-learn-examples-v1-mybatis-pagehelper

spring.datasource.url=jdbc:mysql://localhost:3306/test
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver




server.port=8080

mybatis.check-config-location=true
mybatis.type-aliases-package=classpath*:com.julong.**.entity
## 配置加载mapper文件路径
mybatis.mapper-locations=classpath*:com/julong/**/mapper/*Mapper.xml
#mybatis.config-location=
### mybaits使用的日志记录方式
mybatis.configuration.log-impl=org.apache.ibatis.logging.slf4j.Slf4jImpl
### 使全局的映射器启用或禁用缓存
mybatis.configuration.cache-enabled=false
#########################
##分页
#########################
### 数据库方言
#pagehelper.auto-dialect=mysql
### 设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果
pagehelper.page-size-zero=true
### 启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
pagehelper.reasonable=false
### 3.5.0版本可用 - 为了支持startPage(Object params)方法 -->
### 增加了一个`params`参数来配置参数映射，用于从Map或ServletRequest中取值 
### 可以配置pageNum,pageSize,count,pageSizeZero,reasonable,orderBy,不配置映射的用默认值 
### 不理解该含义的前提下，不要随便复制该配置 
pagehelper.params=pageNum=pageHelperStart;pageSize=pageHelperRows;
### 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
pagehelper.offset-as-page-num=true
### 设置为true时，使用RowBounds分页会进行count查询
pagehelper.row-bounds-with-count=true
### 支持通过Mapper接口参数来传递分页参数
pagehelper.support-methods-arguments=false



```



#### 主启动类配置

@MapperScan 指定扫描

```java
package com.julong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot 启动类
 * @author julong
 * @date 2020年2月16日 下午2:41:10
 * @desc 
 */
@MapperScan(basePackages="com.julong.dao") //mybaits 的扫描
@SpringBootApplication
public class SpringBootPaghelperExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPaghelperExampleApplication.class, args);
    }

}

```









## spring-boot-learn-examples-v1-actuator

服务运行监控配置

#### pom文件引入依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

#### 可以通过页面访问的监控属性

```txt
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
```

http://192.168.10.128:9999/health

![image-20230227213536173](D:\Workspaces\OWER\spring-boot-learn-examples-v1\images\image-20230227213536173.png)

http://192.168.10.128:9999/mappings

![image-20230227213658392](D:\Workspaces\OWER\spring-boot-learn-examples-v1\images\image-20230227213658392.png)

## spring-boot-learn-examples-v1-jsp

springboot中集成jsp

```java 
package com.julong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 启动类
 * @author julong
 * @date 2020年4月28日 下午3:19:01
 * @desc 
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringBootWebJspApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebJspApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebJspApplication.class, args);
	}

}

```





## spring-boot-learn-examples-v1-quartz

定时任务的使用

## spring-boot-learn-examples-v1-restful

restful风格接口请求返回



## julong-spring-boot-v1-starters

#### 自定义starters 

```xml
<dependencies>
    <dependency>
        <groupId>com.julong</groupId>
        <artifactId>julong-spring-boot-v1-autoconfiguration</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```



### julong-spring-boot-v1-autoconfiguration

#### 配置工程

![image-20230227215209874](D:\Workspaces\OWER\spring-boot-learn-examples-v1\images\image-20230227215209874.png)

需要增加的配置 在 resources 下 创建 MATE-INF /spring.factories 文件

```properties
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
## 引入自定义配置
org.julong.spring.boot.v1.configuration.JulongServiceAutoConfiguration
```

#### 属性定义

##### JulongProperties.java

```java
package org.julong.spring.boot.v1.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置属性类
 * @author julong
 * @date 2023年2月16日 上午9:37:20
 * @desc 
 */
@ConfigurationProperties(prefix="julong")
public class JulongProperties {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

```

##### JulongServiceAutoConfiguration.java

```java
package org.julong.spring.boot.v1.configuration;

import org.julong.spring.boot.v1.service.JulongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 * @author julong
 * @date 2023年2月16日 上午9:41:11
 * @desc 
 */
@Configuration
@ConditionalOnWebApplication //web 环境生效
@EnableConfigurationProperties(JulongProperties.class)
public class JulongServiceAutoConfiguration {

	@Autowired
	private JulongProperties julongProperties;
	
	@Bean
	public JulongService julongService(){
		JulongService service = new JulongService();
		service.setJulongProperties(julongProperties);
		return service;
	}
}

```



### julong-spring-boot-v1-starter-test

测试工程

#### pom文件引入配置

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.julong</groupId>
        <artifactId>julong-spring-boot-v1-starters</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```



## spring-boot-learn-examples-v1-admin-server

服务管理

#### pom文件引入配置

```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server</artifactId>
			<version>1.5.0</version>
		</dependency>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server-ui</artifactId>
			<version>1.5.0</version>
		</dependency>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server-ui-hystrix</artifactId>
			<version>1.5.0</version>
		</dependency>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server-ui-turbine</artifactId>
			<version>1.5.0</version>
		</dependency>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-server-ui-login</artifactId>
			<version>1.5.0</version>
		</dependency>
		<dependency>
			<groupId>com.hazelcast</groupId>
			<artifactId>hazelcast</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```

#### admin配置

```java
package com.julong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ListConfig;
import com.hazelcast.config.MapConfig;

import de.codecentric.boot.admin.config.EnableAdminServer;


/**
 * Spring Boot执行器(Actuator)提供安全端点，用于监视和管理Spring Boot应用程序。 默认情况下，所有执行器端点都是安全的
 * @author julong
 * @date 2020年3月11日 下午8:23:13
 * @desc 
 * 使用Spring Boot Actuator Endpoint监控应用程序有点困难。 因为，如果有’n’个应用程序，每个应用程序都有单独的执行器端点，从而使监控变得困难。 
 * Spring Boot Admin Server是一个用于管理和监控Microservice应用程序的程序。
 */
@SpringBootApplication
@EnableAdminServer
public class SpringBootAdminServerExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminServerExampleApplication.class, args);
	}

	@Bean
	public Config hazelcastConfig() {
		return new Config().setProperty("hazelcast.jmx", "true")
				.addMapConfig(new MapConfig("spring-boot-admin-application-store").setBackupCount(1)
						.setEvictionPolicy(EvictionPolicy.NONE))
				.addListConfig(new ListConfig("spring-boot-admin-event-store").setBackupCount(1)
						.setMaxSize(1000));
	}
}

```

服务访问地址 ：http://192.168.10.128:8080



![image-20230227221441198](D:\Workspaces\OWER\spring-boot-learn-examples-v1\images\image-20230227221441198.png)





## spring-boot-learn-examples-v1-admin-client

监控客户端

#### pom文件引入配置

```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>de.codecentric</groupId>
			<artifactId>spring-boot-admin-starter-client</artifactId>
			<version>1.5.0</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```



#### application.properties配置

```properties
#服务名称
spring.application.name=spring-boot-learn-examples-v1-admin-client
server.port=9090

debug=true
trace=true
logging.level.root=debug

#启用客户客户端
spring.boot.admin.client.enabled=true
#设置服务端地址
spring.boot.admin.url=http://localhost:8080


```

服务监控成功如下图：

![image-20230227221727876](D:\Workspaces\OWER\spring-boot-learn-examples-v1\images\image-20230227221727876.png)