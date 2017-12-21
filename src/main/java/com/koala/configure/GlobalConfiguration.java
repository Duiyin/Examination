package com.koala.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class GlobalConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		
		/*registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/**")
		.excludePathPatterns("/vericode.jpg","/","/login","/register","/email/verify","/email/verify/confirm","/info/{id}/items/details/",
				"/info/{id}/exam","/info/{id}/random","/info/{classifyid}/exam/{id}","/info/{examid}/answer/{id}","/info/{classifyid}/exam/{id}/answer");*/
		
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/personal");
		
		registry.addInterceptor(new ReceptionInterceptor()).addPathPatterns("/info/{classifyId}/random","/info/{userid}/{classifyId}/random/create");
		
		registry.addInterceptor(new BackstageInterceptor()).addPathPatterns("/backstage");
		
		super.addInterceptors(registry);
	}
}
