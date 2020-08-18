package com.tt.config;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.tt.ConverterAndFormatter.GoodsConverter;
import com.tt.ConverterAndFormatter.MyFormatter;

/*
 * @Controller，@Service，@Repository注解，查看其源码你会发现，
 * 他们中有一个共同的注解@Component，没错@ComponentScan注解默认就会装配标识了
 * @Controller，@Service，@Repository，@Component注解的类到spring容器中，
 *  * **/
@Configuration
@EnableWebMvc // 启用spring mvc将springmvc的各种 配置注入spring
@ComponentScan(basePackages = "com.tt.*")
public class WebConfig extends WebMvcConfigurerAdapter {

	// 配置视图的前后缀

	/*
	 * @Bean public InternalResourceViewResolver viewResolver() {
	 * InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	 * resolver.setPrefix("/WEB-INF/jsp/"); resolver.setSuffix(".jsp");
	 * resolver.setContentType("text/html; charset=UTF-8"); return resolver; }
	 */

	// 自定义类型转换器

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// 添加字符串转换list的自定义转换器
		registry.addConverter(new GoodsConverter());
		registry.addFormatter(new MyFormatter());

	}

	// 限制文件上传大小
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(500000l);
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		return commonsMultipartResolver;
	}

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

		return converter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		super.configureMessageConverters(converters);
		converters.add(responseBodyConverter());
		converters.add(new MappingJackson2HttpMessageConverter());
	}
	/*
	 * freemarker配置视图
	 **/

	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setCache(false);
		resolver.setPrefix("");
		resolver.setSuffix(".html");
		resolver.setContentType("text/html; charset=UTF-8");
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("/app/templates/manage/");
		Properties settings = new Properties();
		settings.put("default_encoding", "UTF-8");
		settings.put("output_encoding", "UTF-8");
		configurer.setFreemarkerSettings(settings);
		return configurer;
	}

	/*
	 * @Override public void configureViewResolvers(ViewResolverRegistry registry) {
	 * registry.freeMarker(); // registry.enableContentNegotiation(new
	 * MappingJackson2JsonView()); }
	 */

	// 拦截器
	/*
	 * @Bean MyIntercptor myIntercptor() { return new MyIntercptor(); }
	 * 
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * super.addInterceptors(registry); // 添加拦截器，可以有多个 InterceptorRegistration
	 * addInterceptor = registry.addInterceptor(myIntercptor()); // 定义拦截什么请求
	 * addInterceptor.addPathPatterns("/**"); // 定义不拦截什么请求
	 * addInterceptor.excludePathPatterns("/login"); }
	 */

}
