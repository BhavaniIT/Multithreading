
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ScheduledExecutorDemo {
    public static void main(String[] args) {
        try(ScheduledExecutorService service = Executors.newScheduledThreadPool(0)){
            service.scheduleAtFixedRate(new ScheduledTask(), 1000, 2000, TimeUnit.MILLISECONDS);
            try {
                if(!service.awaitTermination(10000, TimeUnit.MILLISECONDS)){
                service.shutdownNow();
                }
            } catch (InterruptedException e) {
                    service.shutdownNow();
                    throw new RuntimeException(e);
            }
        }
    }
}

class ScheduledTask implements Runnable {
    @Override
    public void run(){
        System.out.println("Executing the task...");
    }
}
