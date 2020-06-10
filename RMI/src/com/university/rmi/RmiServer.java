package com.university.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiServer {
    public static void main(String[] args) throws RemoteException {
        FunctionCalculator calc = new FunctionCalculator();
        Registry registry = LocateRegistry.createRegistry(1234);
        registry.rebind("calc",calc);
    }
}
