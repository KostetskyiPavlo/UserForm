package com.logos;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;

import com.logos.formatter.UserFormatter;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/resourcse/**")
			.addResourceLocations("/resources/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry
			.jsp()
			.prefix("/WEB-INF/views/")
			.suffix(".jsp")
			.viewClass(JstlView.class);
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new UserFormatter());
	}

	
}
