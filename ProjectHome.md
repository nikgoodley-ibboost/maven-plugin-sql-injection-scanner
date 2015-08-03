This plugin checks the list of provided websites whether sql injection vulnerability is there.

It checks only for mysql database (in the development, 70% ready) and planned for the next couple of months is oracle. And after that postgresql.

Configure in the list your website for vulnerability check! The plugin will look only among two layers of the website, if there are more than two layers, the next layers needs to be added to the configuration list in order to be checked.
For example if you go from index.jsp to myCity.jsp and from myCity.jsp to myTown.jsp, than myTown.jsp should be added as extra entry point for checking for sql injection vulnerability.

your configuration should look like:

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>


&lt;sites&gt;


> 

&lt;site&gt;


> > 

&lt;url&gt;

myFirstSiteToCheck

&lt;/url&gt;


> > 

&lt;authentication&gt;


> > > 

&lt;username&gt;

username

&lt;/username&gt;


> > > 

&lt;password&gt;

password

&lt;/password&gt;



> > 

&lt;/authentication&gt;



> 

&lt;/site&gt;


> 

&lt;site&gt;


> > 

&lt;url&gt;

mySecondSiteToCheck

&lt;/url&gt;


> > 

&lt;authentication&gt;


> > > 

&lt;username&gt;



&lt;/username&gt;


> > > 

&lt;password&gt;



&lt;/password&gt;



> > 

&lt;/authentication&gt;



> 

&lt;/site&gt;




&lt;/sites&gt;



This file can be set as -D system property for JVM when the application is started.
The property name is PATH\_TO\_CONFIG\_FILE, so for example set it with
-DPATH\_TO\_CONFIG\_FILE=/data/config/mySites.xml

The jar is packaged with assembly plugin from maven