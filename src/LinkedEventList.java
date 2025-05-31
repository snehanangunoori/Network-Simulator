public class LinkedEventList implements FutureEventList{
    Node head; // instance variable for the head node of type Node
    int size;  // instance variable for size and capacity
    int simtime; //instance variable for simulation time

    public LinkedEventList()
    {
        this.head = null;           // assigning null to head instance variable
        size = 0;                   // assigning 0 to the size instance variable
        simtime = 0;                // assigning 0 to the simtime instance variable
    }

    @Override
    public Event removeFirst() {
            if (size <= 0 || this.head == null) {
                return null;
            }
        
            Node removeFirst = this.head;
            this.head = removeFirst.getNext();
            simtime = removeFirst.getE().getArrivalTime();
            size--;
        
            return removeFirst.getE(); 
    }

    @Override
    public boolean remove(Event e) {
        Node currentNode = this.head;

        // looping through the linked list till we find Event e and removing it once found
        while (currentNode != null){
            if (currentNode.getE() == e){
                currentNode.getPrev().next = currentNode.getNext();
                currentNode.getNext().prev = currentNode.getPrev();
                size--;
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public void insert(Event e) {
        Node newNode = new Node(e);
        e.setInsertionTime(simtime);

        if (head == null) {
            // List is empty
            head = newNode;
        }

        else if (e.getArrivalTime() < head.getE().getArrivalTime()) {
            // Insert new event at the beginning of the list if its arrival time is less than the head's event
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        else {
            // Find the correct position for insertion
            Node current = head;
            while (current.next != null && current.next.getE().getArrivalTime() <= e.getArrivalTime()) {
                current = current.next;
            }

            // Insert the new event in the correct position
            newNode.next = current.next;
            if (current.next != null) {
                current.next.prev = newNode;
            }
            current.next = newNode;
            newNode.prev = current;
        }
        // Incrementing the size of the linked event list after event insertion
        size++;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public int capacity() {

        return size;
    }

    @Override
    public int getSimulationTime() {

        return simtime;
    }
}
