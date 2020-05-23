package com.slq;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import com.slq.pojo.Role;
import com.slq.pojo.User;
/***
 * 主要是通过类 AutoConfigurationImportSelector来实现的自动配置
 * @author 孙陆泉
 *
 */
@SpringBootApplication

//@EnableCaching //是cache开启缓存  开启缓存再com.slq.config下的多配置缓存配置类中加入了
//enableCacheing
//@EnableWebSocket
public class WarehouseModuleApplication {

	public static void main(String[] args) {
//		Class [] objs=new Class[2];
//		objs[0]=PermissionModuleApplication.class;
//		objs[1]=WarehouseModuleApplication.class;
//		SpringApplication application=new SpringApplication();
//		application.run(objs, args);
		//SpringApplication.run
		SpringApplication application=new SpringApplication(WarehouseModuleApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}
}
