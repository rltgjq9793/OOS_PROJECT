package org.oos.config;

import org.oos.interceptor.ProductViewInterceptor;
import org.oos.interceptor.StoreViewInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	@Qualifier(value = "storeViewInterceptor")
	private StoreViewInterceptor storeViewInterceptor;
	@Autowired
	@Qualifier(value = "productViewInterceptor")
	private ProductViewInterceptor productViewInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(productViewInterceptor)
		.addPathPatterns("/store/detail");
		
		
		registry.addInterceptor(storeViewInterceptor)
				.addPathPatterns("/store/list").addPathPatterns("/store/detail");
	
		
		
	}
}
