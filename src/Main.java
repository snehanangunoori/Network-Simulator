import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String filename = args[0];
        Scanner scanner;

        LinkedEventList mainList = new LinkedEventList();

        // Caching Hosts into an Arraylist
        ArrayList<SimpleHost> hostList = new ArrayList<>();

        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Instantiating Simple Host Object
        int firsthostaddress = scanner.nextInt();
        SimpleHost firsthost = new SimpleHost();
        firsthost.setHostParameters(firsthostaddress, mainList);

        // Adding firsthost to the hostList arraylist
        hostList.add(firsthost);

        int address = scanner.nextInt();

        // Instantiating and caching the other hosts into an arraylist
        while (address != -1) {

            SimpleHost host = new SimpleHost();
            host.setHostParameters(address, mainList);

            // Adding other hosts to the hostList arraylist
            hostList.add(host);

            int distance = scanner.nextInt();
            host.addNeighbor(firsthost, distance);
            firsthost.addNeighbor(host, distance);

            address = scanner.nextInt();

        }

        // Calling sendPings method for each line in the simulation.txt
        while (scanner.hasNextLine()) {
            int hostReq = scanner.nextInt();
            int hostRes = scanner.nextInt();
            int timeInterval = scanner.nextInt();
            int duration = scanner.nextInt();

            for (SimpleHost simpleHost : hostList) {
                if (simpleHost.getHostAddress() == hostReq) {
                    simpleHost.sendPings(hostRes, timeInterval, duration);
                    break;
                }
            }
        }

        // Handling all the timers and messages
        while (mainList.size() > 0) {
            Event event = mainList.removeFirst();
            event.handle();
        }

        scanner.close(); // closing the scanner object
    }
}