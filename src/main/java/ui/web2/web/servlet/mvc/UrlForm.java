package ui.web2.web.servlet.mvc;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: nshevchenko
 * Date: 18.07.12
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
public class UrlForm {
    private String urlString;


    private String emailString;

    private Integer id = null;
    private String logFileName;
    private Date created;

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date createdDate) {
        this.created = createdDate;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public String getEmailString() {
        return emailString;
    }

    public void setEmailString(String emailString) {
        this.emailString = emailString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
