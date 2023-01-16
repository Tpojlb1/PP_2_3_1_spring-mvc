package web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan(value = "web")
public class AppConfig {
@Resource
private Environment env;

@Bean
public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    em.setJpaProperties(getHibernateProperties());
    return em;
}

@Bean
public DataSource dataSource() {
    BasicDataSource bs = new BasicDataSource();
    bs.setDriverClassName(env.getRequiredProperty("db.driver"));
    bs.setUrl(env.getRequiredProperty("db.url"));
    bs.setUsername(env.getRequiredProperty("db.username"));
    bs.setPassword(env.getRequiredProperty("db.password"));
    return bs;
}

private Properties getHibernateProperties() {
    try {


        Properties properties = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
        properties.load(is);
        return properties;
    } catch (IOException e) {
        throw new IllegalArgumentException("Can't find 'hibernate.properties' in classpath!", e);
    }

}

@Bean
public PlatformTransactionManager platformTransactionManager() {
    JpaTransactionManager manager = new JpaTransactionManager();
    manager.setEntityManagerFactory(entityManagerFactory().getObject());
    return manager;
}

}
