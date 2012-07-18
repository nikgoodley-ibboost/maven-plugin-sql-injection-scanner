package ui.web2.web.servlet.mvc;

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
