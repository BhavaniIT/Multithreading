
import java.util.concurrent.CountDownLatch;


public class Restaurant {
    public static void main(String[] args) throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(new Chef("Chef A","Idly", latch)).start();
        new Thread(new Chef("Chef B","Dosa", latch)).start();
        new Thread(new Chef("Chef C","Sambar", latch)).start();
        latch.await();
    }
    
}

class Chef implements Runnable {

    private final String chefName;
    private final String dishName;
    private final CountDownLatch latch;


    public  Chef(String  chefName, String dishName, CountDownLatch latch) {
        this.chefName = chefName;
        this.dishName = dishName;
        this.latch = latch;
    }

    @Override
    public void run(){
        try {
            System.out.println(chefName + " is prearing " + dishName);
            System.out.println("Thread doing it:"+Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println(dishName + " is ready to serve.");
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
