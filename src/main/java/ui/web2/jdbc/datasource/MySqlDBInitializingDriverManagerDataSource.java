package ui.web2.jdbc.datasource;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Driver;
import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.springframework.util.xml.SimpleSaxErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;


import javax.xml.XMLConstants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;



public class MySqlDBInitializingDriverManagerDataSource extends InitializingDriverManagerDataSource
        implements InitializingBean {

    protected static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    protected static String URL;
    protected static String USERNAME ;
    protected static String PASSWORD;
    private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    private static final String PERSISTENCE_UNIT = "persistence-unit";
    private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    private static final String SCHEMA_NAME = "persistence_1_0.xsd";
    private static final String[] SCHEMA_RESOURCE_LOCATIONS = {
            "classpath:persistence_1_0.xsd",
            "classpath:org/hibernate/ejb/persistence_1_0.xsd",
            "classpath:org/jpox/jpa/persistence_1_0.xsd"};

    protected Resource findSchemaResource() {

        for (String location : SCHEMA_RESOURCE_LOCATIONS) {
            Resource schemaLocation = new ClassPathResource(location);
            if (schemaLocation.exists()) {
                return schemaLocation;
            }
        }
        return null;
    }

    private void readPersistenceXml(){


    ErrorHandler handler = new SimpleSaxErrorHandler(logger);
    List infos = new LinkedList();
    String resourceLocation = null;
    try {

            Resource resource = new ClassPathResource("META-INF/persistence.xml");

                resourceLocation = resource.toString();
                InputStream stream = resource.getInputStream();
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    dbf.setNamespaceAware(true);

                    // Set schema location only if we found one inside the classpath.
                    Resource schemaLocation = findSchemaResource();
                    if (schemaLocation != null) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Found schema resource: " + schemaLocation.getURL());
                        }
                        dbf.setValidating(true);
                        dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, XMLConstants.W3C_XML_SCHEMA_NS_URI);
                        dbf.setAttribute(JAXP_SCHEMA_SOURCE, schemaLocation.getURL().toString());
                    }
                    else {
                        logger.debug("Schema resource [" + SCHEMA_NAME +
                                "] not found - falling back to XML parsing without schema validation");
                    }

                    DocumentBuilder parser = dbf.newDocumentBuilder();
                    parser.setErrorHandler(handler);
                    Document document = parser.parse(stream);
                    org.w3c.dom.Element persistence = document.getDocumentElement();


                    List<org.w3c.dom.Element> units = DomUtils.getChildElementsByTagName(persistence, PERSISTENCE_UNIT);
                    for (org.w3c.dom.Element unit : units) {
                        NodeList childNodes = unit.getChildNodes();
                        for(int i=0; i<childNodes.getLength(); i++){
                            Node childNode = childNodes.item(i);
                            if(childNode.getNodeName().equals("properties")) {
                                NodeList propertyNodes = childNode.getChildNodes();
                                for(int j=0; j<propertyNodes.getLength(); j++){
                                    NamedNodeMap attributes =  propertyNodes.item(j).getAttributes();
                                    if(attributes!=null && attributes.getLength()>0){
                                        if(attributes.getNamedItem("name").getNodeValue().equals("hibernate.connection.username")) {
                                            USERNAME = attributes.getNamedItem("value").getNodeValue();
                                        }
                                        if(attributes.getNamedItem("name").getNodeValue().equals("hibernate.connection.password")) {
                                            PASSWORD = attributes.getNamedItem("value").getNodeValue();
                                        }
                                        if(attributes.getNamedItem("name").getNodeValue().equals("hibernate.connection.url")) {
                                            URL = attributes.getNamedItem("value").getNodeValue();
                                        }
                                    }
                                }
                                break;
                            }
                        }



                    }
                }
                finally {
                    stream.close();
                }


    }
    catch (IOException ex) {
        throw new IllegalArgumentException("Cannot parse persistence unit from " + resourceLocation, ex);
    }
    catch (SAXException ex) {
        throw new IllegalArgumentException("Invalid XML in persistence unit from " + resourceLocation, ex);
    }
    catch (ParserConfigurationException ex) {
        throw new IllegalArgumentException("Internal error parsing persistence unit from " + resourceLocation);
    }

    //return infos.toArray(new SpringPersistenceUnitInfo[infos.size()]);
    }
    /**
     * Implementation of <code>InitializingBean</code>
     */
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        /*Resource resource = new ClassPathResource("META-INF/persistence.xml");
        XPathFactory  factory= XPathFactory.newInstance();
        XPath xPath=factory.newXPath();   */
        readPersistenceXml();
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