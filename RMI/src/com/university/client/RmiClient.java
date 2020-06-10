package com.university.client;

import com.university.rmi.ICalculator;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class RmiClient {
    private ICalculator calculator;
    private String remoteURL = "//127.0.0.1:1234/calc";

    public RmiClient() throws RemoteException, NotBoundException, MalformedURLException {
        calculator = (ICalculator) Naming.lookup(remoteURL);
        System.out.println("RMI object found");
    }

    public List<Double> calculate(double a, double xFrom, double xTo, double step) throws RemoteException {
        return calculator.calculate(a,xFrom,xTo,step);
    }
}
