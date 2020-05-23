package com.slq;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ProductionModuleApplication {
	public static void main(String[] args) {
		//System.out.println(IDGenerateUtil.getID(Constant.PRODUCT_DESIGN));
		SpringApplication application=new SpringApplication(PermissionModuleApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}
}
