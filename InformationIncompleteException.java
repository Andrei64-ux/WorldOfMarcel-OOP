package exceptions;

public class InformationIncompleteException extends Exception{
    public InformationIncompleteException(String str){
        super(str);
        System.out.println("exceptions.InformationIncompleteException");
    }
}
