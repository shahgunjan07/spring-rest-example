package com.spring.rest.example.springrestexample.configuration;

import java.util.Locale;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
/**
 * Configure component scan so that SpringContainer can scan packages which are 
 * not similar to or sub-packages of the package where Spring Boot Application class defined.
 */
@ComponentScan({"com.spring.rest.example"})
@EnableSwagger2
@EntityScan(basePackages="com.spring.rest.example.domain")
@ImportResource(locations = {"classpath*:spring/application-context.xml"})
public class SpringRestExampleConfiguration {

	/**
	 * Configure a bean to local resolver.
	 * Set default locale to US
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
	/**
	 * Create a bean to configure ResourceBundleMessageSource
	 * Set Base Name to read all files whose name starts with 'messages'
	 * @return
	 */
	public ResourceBundleMessageSource bundleMessageSource( ) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	/**
	 * Bean of swagger Docket
	 * @return
	 */
	@Bean
	public Docket  api() {
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
					.build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
					.contact(contact())
					.description("Restful and Micro Service Example")
					.title("Restful and Micro Service Example")
					.build();
	}
	
	private Contact contact() {
		return new Contact("Gunjan Shah", "https://github.com/shahgunjan07/spring-rest-example","shahgunjan07@gmail.com");
	}
	
	
}
