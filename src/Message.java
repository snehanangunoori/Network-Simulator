public class Message extends Event {

    private final String message;       // Holds two values: "ping request" or "ping response"
    private final int srcAddress;
    private final int destAddress;
    private Host h;
    private int distance;

    public Message(String message, int srcAd, int destAdd)
    {
        this.message = message;
        this.srcAddress = srcAd;
        this.destAddress = destAdd;

    }
    @Override
    public void handle() {
        this.h.receive(this);
    }


    @Override
    public void setInsertionTime(int currentTime) {
        this.insertionTime = currentTime;               // setting message insertion time to the current simulation time
        this.arrivalTime = insertionTime + distance;    // setting arrival time to the sum of the insertion time and the distance between the hosts

    }

    // Handle message cancellation if necessary -- No implementation
    @Override
    public void cancel() {
    }

    public String getMessage() {

        return this.message;
    }

    public int getSrcAddress() {

        return this.srcAddress;
    }

    public int getDestAddress() {

        return this.destAddress;
    }

    public void setNextHop(Host destination, int distance) {
        this.h = destination;
        this.distance = distance;
    }


}