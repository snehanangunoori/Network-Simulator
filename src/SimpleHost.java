public class SimpleHost extends Host{
    int durationTimer;
    int destAddress;
    int interval;
    int duration;


    // Message Handling Method
    @Override
    protected void receive(Message msg) {
        String messageType = msg.getMessage();
        switch (messageType) {
            case "ping request":

                // Handle ping request at the destination host
                System.out.println("[" + msg.getArrivalTime() + "ts] Host " + msg.getDestAddress() + ": Ping request from host " + msg.getSrcAddress());
                Message pingResponse = new Message("ping response", msg.getDestAddress(), msg.getSrcAddress());
                //pingResponse.setInsertionTime(this.getCurrentTime());
                this.sendToNeighbor(pingResponse);
                break;

            case "ping response":
                // Handle ping response at the source host
                int rttTime = (msg.getArrivalTime() - msg.getInsertionTime()) * 2;
                System.out.println("[" + msg.getArrivalTime() + "ts] Host " + msg.getDestAddress() + ": Ping response from host " + msg.getSrcAddress() + " (RTT = " + rttTime + "ts)");
                break;

            default:
                System.out.println("Unknown message type: " + messageType);
        }

    }

    @Override
    protected void timerExpired(int eventId) {
        if (eventId == durationTimer){
            System.out.println("[" + this.getCurrentTime() + "ts] Host " + this.getHostAddress() + ": Stopped sending pings");
        }

        // New message added to the linked list if the event is not the duration timer
        else{
            Message msg = new Message("ping request", this.getHostAddress(), this.destAddress );
            msg.setInsertionTime(this.getCurrentTime());
            sendToNeighbor(msg);
            System.out.println("[" + this.getCurrentTime() + "ts] Host " + this.getHostAddress() + ": Sent ping to host " + this.destAddress);

            // New interval timer created only if current time + interval is less than duration
            if ((this.getCurrentTime() + interval) < duration) {
                newTimer(interval);
            }
        }
    }

    // Never used this method
    @Override
    protected void timerCancelled(int eventId) {
        this.cancelTimer(eventId);
        System.out.println("[" + this.getCurrentTime() + "ts] Timer " + eventId + " Cancelled");
    }


    // Call this function from main
    public void sendPings(int destAddr, int interval, int duration){
        this.destAddress = destAddr;
        this.interval = interval;
        this.duration = duration;

        // Making two new timers: 1)interval 2)duration
        newTimer(interval);
        durationTimer = newTimer(duration);
    }

}
