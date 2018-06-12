package com.unifonic.util;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Java config for Springfox swagger documentation plugin
 *
 * @author Shareef Hiasat
 *
 */

@Configuration
@EnableSwagger2
@ComponentScan(basePackages="com.unifonic.rest")
public class ApplicationSwaggerConfig {

   @Bean
   public Docket customDocket(){
      return new Docket(DocumentationType.SWAGGER_2)
    		  .select()
              .apis(RequestHandlerSelectors.any())
              .paths(Predicates.not(PathSelectors.regex("/error"))) // Exclude Spring error controllers
              .build()
              .apiInfo(getApiInfo());
   }

   private ApiInfo getApiInfo() {
	   return new ApiInfo(
		"REST Unifonic backend Api Documentation",
		"This is REST API documentation of the Spring Unifonic backend",
		"1.0",
		"Unifonic backend terms of service",
		new Contact(
				"Shareef Hiasat",
				"https://www.google.com",//@TODO shareef please add your url of github
				"shareef.hiasat@gmail.com"),
		"Apache 2.0",
		"http://www.apache.org/licenses/LICENSE-2.0");
   }


}
