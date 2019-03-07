package com.spring.rest.example.springrestexample.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
/**
 * Configure component scan so that SpringContainer can scan packages which are 
 * not similar to or sub-packages of the package where Spring Boot Application class defined.
 */
@ComponentScan({"com.spring.rest.example"})
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
}
