package src;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class RMIServer {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            int numOfTasks = 28;
            
            Service service = new Serviceimpl();
            // Init service

            for (int i=1;i<numOfTasks+1; i++){

                int leftLimit = 97;
                int rightLimit = 122; 
                int targetStringLength = 10;
                Random random = new Random();
            
                String generatedString = random.ints(leftLimit, rightLimit + 1)
                  .limit(targetStringLength)
                  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                  .toString();

                service.addElem(generatedString);
            }

            String serviceName = "Service";

            System.out.println("Initializing " + serviceName);

            Registry registry = LocateRegistry.createRegistry(port);
           
            registry.rebind(serviceName, service);

            System.out.println("Start " + serviceName);
            String result = service.returnRes();
            long endTime = System.nanoTime();
            System.out.println("Result: "+result+" Time it took to complete the task: "+(endTime-startTime)/1000000000+" seconds");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}