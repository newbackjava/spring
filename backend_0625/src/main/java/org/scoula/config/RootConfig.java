package org.scoula.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:/application.properties"})
//@MapperScan(basePackages = {"org.scoula.board.mapper", "org.scoula.member.mapper", "org.scoula.travel.mapper"})

@MapperScan(value = "org.scoula", annotationClass = Mapper.class)
//@ComponentScan(basePackages = {"org.scoula.board.service", "org.scoula.member.service"})
//@ComponentScan(
//        basePackages = "org.scoula",
//        includeFilters = @ComponentScan.Filter(
//                type = FilterType.ANNOTATION,
//                classes = {Service.class}
//        )
//)
//@ComponentScan(
//        basePackages = "org.scoula",
//        useDefaultFilters = false,
//        excludeFilters = {
//                @ComponentScan.Filter(
//                        type = FilterType.ASPECTJ,
//                        pattern = {"org.scoula.**.controller", "org.scoula.security.**"}
//                )
//        }
//)
@ComponentScan(
        basePackages = "org.scoula",
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Controller.class, RestControllerAdvice.class, ControllerAdvice.class}
                ),
                @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "org.scoula.security.*")
        }

)
@Log4j2
@EnableTransactionManagement
public class RootConfig {
    @Value("${jdbc.driver}")
    String driver;
    @Value("${jdbc.url}")
    String url;
    @Value("${jdbc.username}")
    String username;
    @Value("${jdbc.password}")
    String password;
    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        sqlSessionFactory.setDataSource(dataSource());

        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());

        return manager;
    }

}
