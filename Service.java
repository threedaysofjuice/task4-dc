package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    String pollElem() throws RemoteException;
    void addElem(String str) throws RemoteException;
    public void addResult(String str) throws RemoteException;
    public String getResult() throws RemoteException;
    public boolean isEmpty() throws RemoteException;
    public void setWorking(boolean isWorking) throws RemoteException;
    public boolean isWorking() throws RemoteException;
    public String returnRes() throws RemoteException, InterruptedException;

}