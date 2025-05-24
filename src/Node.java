public class Node{

    Node prev;
    Node next;
    private final Event e;

    public Node(Event e)
    {
        this.prev = null;
        this.next = null;
        this.e = e;
    }

    public Node getPrev(){

        return prev;
    }

    public Node getNext(){

        return next;
    }

    public Event getE() {
        return e;
    }
}
