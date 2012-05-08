package de.sqlinjection;







import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;




/**
 * This goal will say a message.
 *
 * @goal testsqlinjection
 *
 * execute mvn clean package to make sure the goal has been executed!
 */
public class SQLIMaven extends AbstractMojo {

    public static void main(String [] args){
        SQLIMaven sqlInjectionMojo = new SQLIMaven();
        try{
            sqlInjectionMojo.execute();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void execute() throws MojoExecutionException {




    }


}

