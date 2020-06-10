package program;

import program.calc.CalculatorParams;
import program.calc.FunctionCalculator;
import program.calc.ICalculator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private ServerSocket server = null;
    private Socket sock = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private ICalculator calculator;

    public void start(int port) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        calculator = new FunctionCalculator();
        while (true) {
            sock = server.accept();
            objectInputStream = new ObjectInputStream(sock.getInputStream());
            objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
            while (true) {
                processQuery();
            }
        }
    }

    private void processQuery() throws IOException, ClassNotFoundException {
        CalculatorParams params = (CalculatorParams) objectInputStream.readObject();
        List<Double> result = calculator.calculate(params.a,params.xFom,params.xTo,params.step);
        objectOutputStream.writeObject(result);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        server.start(12345);
    }

}