package de.nshevchenko.sqlinjection.check;


import de.nshevchenko.config.Site;

public class ScanResult {

    private boolean sqlInjectionVulnerable = false;
    private Site site;
    private String vulnerableParamName;
    private String vulnerableUrl;
    private String sqlInjectionType;
    
    public ScanResult(Site site){
        this.site = site;
    }
    
    public boolean isSqlInjectionVulnerable() {
        return sqlInjectionVulnerable;
    }

    public void setSqlInjectionVulnerable(boolean sqlInjectionVulnerable) {
        this.sqlInjectionVulnerable = sqlInjectionVulnerable;
    }

    public String getVulnerableParamName() {
        return vulnerableParamName;
    }

    public void setVulnerableParamName(String vulnerableParamName) {
        this.vulnerableParamName = vulnerableParamName;
    }

    public String getVulnerableUrl() {
        return vulnerableUrl;
    }

    public void setVulnerableUrl(String vulnerableUrl) {
        this.vulnerableUrl = vulnerableUrl;
    }

    public String getSqlInjectionType() {
        return sqlInjectionType;
    }

    public void setSqlInjectionType(String sqlInjectionType) {
        this.sqlInjectionType = sqlInjectionType;
    }

    @Override
    public String toString() {
        return "ScanResult{" +
                "sqlInjectionVulnerable=" + sqlInjectionVulnerable +
                ", site=" + site +
                ", vulnerableParamName='" + vulnerableParamName + '\'' +
                ", vulnerableUrl='" + vulnerableUrl + '\'' +
                ", sqlInjectionType='" + sqlInjectionType + '\'' +
                '}';
    }
}