package ui.web2.jdbc.datasource;

import java.sql.Driver;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;




public class MySqlDBInitializingDriverManagerDataSource extends InitializingDriverManagerDataSource
        implements InitializingBean {

    protected static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    protected static String URL = "";
    protected static String USERNAME = "";
    protected static String PASSWORD = "";

    /**
     * Implementation of <code>InitializingBean</code>
     */
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        if (getDriver() == null && !StringUtils.hasText(driverClassName)) {
            setDriverClass((Class<? extends Driver>) ClassUtils.forName(DRIVER_CLASS_NAME, ClassUtils.getDefaultClassLoader()));
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