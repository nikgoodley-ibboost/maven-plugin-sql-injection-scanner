package de.nshevchenko.sqlinjection.type;


public class UnescapedNumericSQLInjection implements SQLInjection{

    //action=artikel AND 229=229&cat=8&id=17&artlang=de
    //action=artikel&cat=8&id=17&artlang=de AND 95=95

    //select review_content, review_author from reviews where id=5
    //and
    //select review_content, review_author from reviews where id=3+2 (10-5 or whatever)
    //and
    //select review_content, review_author from reviews where id=5+(Select 0/1)
    //produce the same result!

    public String createInjectionWithNumberOfBrackets(String oldValue, int numberOfBrackets){
        int randomInt = (int)(Math.random()*10000);
        StringBuffer myNewValue = new StringBuffer(oldValue);

        for(int i=0; i<numberOfBrackets; i++){
            myNewValue.append(")");
        }
        myNewValue.append(" AND ");
        for(int i=0; i<numberOfBrackets; i++){
            myNewValue.append("(");
        }

        myNewValue.append(randomInt);
        myNewValue.append("=");
        myNewValue.append(randomInt);
        return myNewValue.toString();
    }

    public String sqlInjectionType(){
        return "UnescapedNumericSQLInjection";
    }
}
