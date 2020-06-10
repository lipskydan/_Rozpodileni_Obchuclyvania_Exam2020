package program;



import program.calc.CalculatorParams;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    private Socket sock;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Client(String ip, int port) throws IOException {
        sock = new Socket(ip, port);
        objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
        objectInputStream = new ObjectInputStream(sock.getInputStream());
    }

    public List<Double> calculate(double a, double xFrom, double xTo, double step) throws IOException, ClassNotFoundException {
        CalculatorParams params = new CalculatorParams(a,xFrom,xTo,step);
        objectOutputStream.writeObject(params);
        List<Double> result = (List<Double>)objectInputStream.readObject();
        return result;
    }

    public void disconnect() throws IOException {
        sock.close();
    }
    public static void main(String[] args) {
        System.out.println("This program can calculate function: a*sin(x) with the help of Server.");
        double a, xFrom, xTo, step;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a value: ");
        a = in.nextFloat();
        System.out.print("Enter start x value: ");
        xFrom = in.nextFloat();
        System.out.print("Enter last x value: ");
        xTo = in.nextFloat();
        System.out.print("Enter step: ");
        step = in.nextFloat();

        Client client = null;
        try {
            client = new Client("localhost",12345);
            List<Double> results = client.calculate(a, xFrom, xTo, step);
            int i = 0;
            for (double t = xFrom; t <= xTo; t += step) {
                System.out.println("|" + t + "|" + results.get(i) + "|");
                i++;
            }
            client.disconnect();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}