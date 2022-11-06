package src;

import java.rmi.Naming;


public class RMIClient {
    static String handleInput(String input){
        return input.toUpperCase();
    }

    public static void main(String[] args) {
        
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            Thread.sleep(5000);
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);

            while (service.isWorking()) {
                if (service.isEmpty()) {
                    System.out.println("Result: " + service.getResult());
                    service.setWorking(false); // если очередь пуста, то клиент оканчивает работу и показывает результат
                    return;
                }

                String str = service.pollElem();
                System.out.println("Received: " + str);
                String result = handleInput(str);
                service.addResult(result);
                System.out.println("Sent: " + result);
                Thread.sleep(1000);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}