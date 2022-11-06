package src;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Implementation of the remote service.
 */
public class Serviceimpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<String> queue;
    private String result = "";
    private boolean isWorking = true;
    public Serviceimpl() throws RemoteException {
        super();
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public String pollElem() throws RemoteException, NullPointerException {
        return this.queue.poll();
    }

    @Override
    public void addElem(String str) throws RemoteException {
        this.queue.add(str);
        System.out.println("Added: " + str);
    }

    @Override
    public void addResult(String str){
        this.result+=str;
    }

    @Override
    public String getResult(){
        return this.result;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String returnRes() throws InterruptedException{
        while (!isEmpty()&&isWorking()){

        }
        Thread.sleep(1000); // клиент не успевает добавить уже обработанный резузльтат в конечный результат, поэтому и добавил таймер
        return getResult();
    }

    @Override
    public boolean isWorking(){
        return isWorking;
    }

    @Override
    public void setWorking(boolean isWorking) throws RemoteException {
        this.isWorking = isWorking;
    }
}