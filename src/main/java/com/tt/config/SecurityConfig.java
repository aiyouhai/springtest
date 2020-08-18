package com.tt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.tt.ui.service.CustomUserService.CustomUserService;
import com.tt.utils.MyAccessDecisionManager;
import com.tt.utils.MySecurityMetadataSource;

/**
 * 可以通过重载WebSecurityConfigurerAdapter的三个config方法来定制Security的规则
 **/
@Configuration
@EnableWebSecurity // 开启Security
@Import(ApplicationConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// 异常处理类
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPointImpl;
	@Autowired
	private AccessDeniedHandler accessDeniedHandlerImpl;

	// 自定义用户认证（就是从数据库查询用户进行认证）
	@Bean
	public CustomUserService customUserService() {
		return new CustomUserService();
	}

	// 密码加密规则
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * FilterSecurityInterceptor负责处理HTTP资源的安全性。 整个过程需要依赖AuthenticationManager、
	 * AccessDecisionManager和FilterInvocationSecurityMetadataSource。
	 * 
	 **/

	@Bean(name = "filtersecurityinterceptor")
	FilterSecurityInterceptor myFilter() {
		FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
		interceptor.setAccessDecisionManager(accessDecisionManager());
		interceptor.setSecurityMetadataSource(mySecurityMetadataSource());
		try {
			interceptor.setAuthenticationManager(authenticationManagerBean());
		} catch (Exception ex) {
		}
		return interceptor;
	}

	/*
	 * 认证管理器，实现用户认证的入口
	 */
	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		AuthenticationManager manager = super.authenticationManagerBean();
		return manager;
	}

	/*
	 * 访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源
	 */
	@Bean
	public MyAccessDecisionManager accessDecisionManager() {
		return new MyAccessDecisionManager();
	}

	/*
	 * 编写 MySecurityMetadataSource 类，获取当前 url 所需要的权限
	 */
	@Bean
	public MySecurityMetadataSource mySecurityMetadataSource() {
		return new MySecurityMetadataSource();
	}

//通过重载该方法，可配置user-detail（用户详细信息）服务。	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserService());
		provider.setPasswordEncoder(passwordEncoder());
		auth.authenticationProvider(provider);

	}

//通过重载该方法，可配置如何通过拦截器保护请求
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		/*
		 * http.exceptionHandling().authenticationEntryPoint(
		 * authenticationEntryPointImpl) .accessDeniedHandler(accessDeniedHandlerImpl);
		 */

		http.authorizeRequests()// 开启登录配置
				// .antMatchers("/**").hasRole("admin")// 表示访问 /hello 这个接口，需要具备
				.anyRequest().authenticated()// 表示剩余的其他接口，登录之后就能访问
				.and().formLogin()// 设置表单登录时
				// 定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
				.loginPage("/index/login")
				// 指定自定义的用户认证页面提交认证表单后发送请求的URL
				.loginProcessingUrl("/user/login").failureUrl("/index/login").permitAll()
				// 定义登录时，用户名的 key，默认为 username
				.usernameParameter("username")
				// 定义登录时，用户密码的 key，默认为 password指定默认认证成功后请求的URL
				.passwordParameter("password").defaultSuccessUrl("/index/index", true).and().logout()
				.logoutUrl("/index/logout").logoutSuccessUrl("/index/login").permitAll().invalidateHttpSession(true)
				.and().antMatcher("/**").addFilterBefore(myFilter(), FilterSecurityInterceptor.class);// 拦截所有的请求;

	}

//通过重载该方法，可配置Spring Security的Filter链
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**/*.jpg", "/**/*.png", "/**/*.gif", "/**/*.css", "/**/*.js", "/**/*.otf",
				"/**/*.eot", "/**/*.ttf", "/**/*.woff", "/**/*.woff2", "/**/*.svg", "/**/*.doc", "/**/*.docx",
				"/**/*.xls", "/**/*.xlsx", "/**/*.ppt", "/**/*.pptx", "/**/*.fla", "/**/*.jpeg", "/**/*.avi",
				"/**/*.bmp", "/**/*.eml", "/**/*.eps", "/**/*.html", "/**/*.ini", "/**/*.ind", "/**/*.jsf",
				"/**/*.midi", "/**/*.mov", "/**/*.mp3", "/**/*.mpeg", "/**/*.pdf", "/**/*.proj", "/**/*.psg",
				"/**/*.pst", "/**/*.pub", "/**/*.rar", "/**/*.tiff", "/**/*.txt", "/**/*.url", "/**/*.vsd", "/**/*.wav",
				"/**/*.wma", "/**/*.wmv", "/**/*.zip", "/**/*.json", "/index/login", "/page*/**", "/user/userExists",
				"/dept/**", "/confFilePath/**/*.gz", "/confFilePath/**/*.conf", "/runMonitor/detaildata", "/sysuser/**",
				"/validateCode/manage", "/confirmValidate/confirm", "/user/userPhoneExists",
				"/confirmValidate/sendPhoneValid", "/confirmValidate/confirmPhoneValid", "/user/getKeys",
				"/appWorkOrder/*", "/vulManage/dataTypeList", "/mainPageBusiness/*");
	}

}
