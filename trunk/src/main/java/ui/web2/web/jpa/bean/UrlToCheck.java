package ui.web2.web.jpa.bean;

import javax.persistence.*;
import java.util.Date;




import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="url")
public class UrlToCheck {
    private Integer id = null;
    private String logFileName = null;
    private String urlString= null;
    private String emailString= null;
    private Date created = null;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "created_date", columnDefinition="default now()")
    public Date getCreated() {
        return created;
    }


    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name = "log_file_name")
    public String getLogFileName() {
        return logFileName;
    }


    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    @Column(name = "url_string")
    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }
    @Column(name = "email_string")
    public String getEmailString() {
        return emailString;
    }

    public void setEmailString(String emailString) {
        this.emailString = emailString;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + id);
        sb.append("  logFileName=" + logFileName);
        sb.append("  created=" + created);
        sb.append("  url_String=" + urlString);
        return sb.toString();
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    /**
     * Indicates whether some other object is equal to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;

        if (id == null) {
            if (other.getId()!= null) {
                return false;
            }
        } else if (!id.equals(other.getId())) {
            return false;
        }

        return true;
    }




}
