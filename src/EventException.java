//Created EventException Class which extends RuntimeException (unchecked exception)
public class EventException extends RuntimeException {

    //Constructor for EventException Class with one String parameter
    public EventException(String errorMessage) {
        super();
        System.out.println("Error!!!");
        System.out.println(errorMessage);
    }

}