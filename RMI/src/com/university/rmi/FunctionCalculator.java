package com.university.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class FunctionCalculator extends UnicastRemoteObject implements ICalculator {
    public FunctionCalculator() throws RemoteException {
        super();
    }

    private double function(double a,double x){
        return Math.sin(x)*a;
    }

    @Override
    public synchronized List<Double> calculate(double a, double xFrom, double xTo, double step) throws RemoteException {
        ArrayList<Double> results = new ArrayList<>();
        for(double t = xFrom; t <=xTo; t+=step){
            results.add(function(a,t));
        }
        return results;
    }
}
