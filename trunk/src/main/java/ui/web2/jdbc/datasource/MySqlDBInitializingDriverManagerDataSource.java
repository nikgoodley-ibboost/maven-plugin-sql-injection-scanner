package ui.web2.jdbc.datasource;

import java.sql.Driver;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;




public class MySqlDBInitializingDriverManagerDataSource extends InitializingDriverManagerDataSource
        implements InitializingBean {

    protected static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    protected static String URL = "jdbc:mysql://localhost:3306/test";
    protected static String USERNAME = "nshevchenko";
    protected static String PASSWORD = "";

    /**
     * Implementation of <code>InitializingBean</code>
     */
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        if (getDriver() == null && !StringUtils.hasText(driverClassName)) {
            Class currentDriverClass = (Class<? extends Driver>) ClassUtils.forName(DRIVER_CLASS_NAME, ClassUtils.getDefaultClassLoader());
            setDriverClass(currentDriverClass);
            setDriverClassName(currentDriverClass.getName());

        }

        if (!StringUtils.hasText(getUrl())) {
            setUrl(URL);
        }

        if (!StringUtils.hasText(getUsername())) {
            setUsername(USERNAME);
        }

        if (!StringUtils.hasText(getPassword())) {
            setPassword(PASSWORD);
        }

        super.afterPropertiesSet();
    }

}