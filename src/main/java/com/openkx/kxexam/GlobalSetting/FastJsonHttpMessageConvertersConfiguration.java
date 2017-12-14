package com.openkx.kxexam.GlobalSetting;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

/**
 * Created by mickjoust on 2016/6/14.
 * com.hjf.boot.demo.boot_properties.httpConvert
 */
@Configuration
@ConditionalOnClass({ JSON.class })
public class FastJsonHttpMessageConvertersConfiguration {

	@Configuration
	@ConditionalOnClass({ FastJsonHttpMessageConverter.class })
	@ConditionalOnProperty(name = {
			"spring.http.converters.preferred-json-mapper" }, havingValue = "fastjson", matchIfMissing = true)
	protected static class FastJson2HttpMessageConverterConfiguration {
		protected FastJson2HttpMessageConverterConfiguration() {
		}

		@Bean
		@ConditionalOnMissingBean({ FastJsonHttpMessageConverter.class })
		public FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter() {
			FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();

			FastJsonConfig fastJsonConfig = new FastJsonConfig();
			fastJsonConfig.setSerializerFeatures(
					SerializerFeature.PrettyFormat,
					//SerializerFeature.WriteClassName,
					SerializerFeature.WriteMapNullValue);
			ValueFilter valueFilter = new ValueFilter() {
				// o 是class
				// s 是key值
				// o1 是value值
				public Object process(Object o, String s, Object o1) {
					if (null == o1) {
						o1 = "";
					}
					return o1;
				}
			};
			fastJsonConfig.setSerializeFilters(valueFilter);

			converter.setFastJsonConfig(fastJsonConfig);

			return converter;
		}
	}
}
