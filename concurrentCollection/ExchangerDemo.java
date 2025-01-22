
import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        Thread one = new Thread(new FirstThread(exchanger));
        Thread two = new Thread(new SecondThread(exchanger));
        one.start();
        two.start();

        
    }
}

class FirstThread implements Runnable{
    private final Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run(){
        Integer dataToSend = 10;
        try {
            System.out.println("Data sending from first thread: " + dataToSend);
            Integer received = exchanger.exchange(dataToSend);
            System.out.println("Data received from second thread: " + received);

            } catch (InterruptedException e) {
        }
    }
}

class SecondThread implements Runnable{
    private final Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run(){
        Integer dataToSend = 20;
        try {
            Thread.sleep(3000);
            System.out.println("Data sending from second thread: " + dataToSend);
            Integer received = exchanger.exchange(dataToSend);
            System.out.println("Data received from first thread: " + received);

            } catch (InterruptedException e) {
        }
    }
}
