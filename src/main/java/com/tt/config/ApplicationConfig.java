package com.tt.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.abel533.mapperhelper.MapperInterceptor;
import com.github.pagehelper.PageInterceptor;
import com.tt.utils.ResourceBundleUtil;

@Configuration // 声明配置类
@EnableAspectJAutoProxy(proxyTargetClass = true) // 开启aop代理
@ComponentScan(basePackages = "com.tt.*") // 开启包扫描...... 使用spring缓存时扫描实体类
@EnableTransactionManagement // 开启事务
@EnableAsync // 和@Async结合使用开启多线程任务({"com.kfit.demo","com.kfit.user"})
@MapperScan({ "com.tt.ui.repository", "com.tt.RESTful" }) // 使包下面的接口生成相应的实现类
@EnableCaching // 开启spring缓存
public class ApplicationConfig {
	@Bean(name = "dataSource")
	@Primary // 再有同名或同类型的bean注入是被@Primary修饰的会被优先选择
	public DataSource dataSource() {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(ResourceBundleUtil.getString("jdbc", "db.driverClassName"));
		ds.setUrl(ResourceBundleUtil.getString("jdbc", "db.url"));
		ds.setUsername(ResourceBundleUtil.getString("jdbc", "db.user"));
		ds.setPassword(ResourceBundleUtil.getString("jdbc", "db.password"));
		ds.setInitialSize(ResourceBundleUtil.getInteger("jdbc", "db.initialSize"));
		ds.setMinIdle(ResourceBundleUtil.getInteger("jdbc", "db.minIdle"));
		ds.setMaxActive(ResourceBundleUtil.getInteger("jdbc", "db.maxActive"));
		ds.setMaxWait(ResourceBundleUtil.getLong("jdbc", "db.maxWait"));
		ds.setTimeBetweenEvictionRunsMillis(ResourceBundleUtil.getLong("jdbc", "db.timeBetweenEvictionRunsMillis"));
		ds.setMinEvictableIdleTimeMillis(ResourceBundleUtil.getLong("jdbc", "db.minEvictableIdleTimeMillis"));
		ds.setValidationQuery("SELECT 'x' from dual");
		ds.setTestWhileIdle(true);
		ds.setTestOnBorrow(false);
		ds.setTestOnReturn(false);
		ds.setPoolPreparedStatements(ResourceBundleUtil.getBoolean("jdbc", "db.poolPreparedStatements"));
		ds.setMaxPoolPreparedStatementPerConnectionSize(
				ResourceBundleUtil.getInteger("jdbc", "db.maxPoolPreparedStatementPerConnectionSize"));
		return ds;
	}

	// 事务
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	/*
	 * public PlatformTransactionManager dataSourceTransactionManager1() { return
	 * new DataSourceTransactionManager(dataSource()); }
	 */
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	// 整合mybatis
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext ap) throws IOException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		sqlSessionFactory.setTypeAliasesPackage("com.tt.ui.pojo");// 配置一个包可以让包中的类名作为全路径的别名
		sqlSessionFactory.setMapperLocations(resourcePatternResolver.getResources("classpath:com/mapper/*.xml"));// 加载mapper.xml文件
		// 设置分页插件
		PageInterceptor pageInterceptor = this.initPageInterceptor();
		sqlSessionFactory.setPlugins(new Interceptor[] { pageInterceptor });

		// 设置通用mapper插件
		MapperInterceptor innitMapperInterceptor = this.innitMapperInterceptor();
		sqlSessionFactory.setPlugins(new Interceptor[] { innitMapperInterceptor });

		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setCacheEnabled(true);// 开启全局二级缓存，另外还需要在mapper.xml文件中加上 <cache></cache>标签
		configuration.setMapUnderscoreToCamelCase(true);// 开启驼峰命名
		configuration.setLazyLoadingEnabled(true);// 开启全局延迟加载，
		configuration.setUseGeneratedKeys(true);// 获取数据库自增主键值
		configuration.setLogImpl(Log4jImpl.class);
		sqlSessionFactory.setConfiguration(configuration);
		return sqlSessionFactory;
	}

	// 初始化pagehelper分页插件
	public PageInterceptor initPageInterceptor() {
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");// 指明数据库
		properties.setProperty("offsetAsPageNum", "true");//
		// 设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
		properties.setProperty("rowBoundsWithCount", "true");//
		// 设置为true时，使用RowBounds分页会进行count查询
		properties.setProperty("pageSizeZero", "true");// 相当于没有执行分页查询，但是返回结果仍然是Page类型
		properties.setProperty("reasonable", "true");//
		// 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
		properties.setProperty("supportMethodsArguments", "true");// 支持通过Mapper接口参数来传递分页参数
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}

	// 初始换abel533mybatis通用mapper
	public MapperInterceptor innitMapperInterceptor() {
		MapperInterceptor mapperInterceptor = new MapperInterceptor();
		Properties properties = new Properties();
		properties.setProperty("IDENTITY", "MYSQL");
		properties.setProperty("mappers", "com.github.abel533.mapper.Mapper");

		mapperInterceptor.setProperties(properties);
		return mapperInterceptor;
	}

	/*
	 * spring缓存
	 * 
	 **/
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("test"), new ConcurrentMapCache("test1")));
		return cacheManager;
	}

}
