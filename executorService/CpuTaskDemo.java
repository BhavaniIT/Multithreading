
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CpuTaskDemo {
    public static void main(String[] args) {
        int core = Runtime.getRuntime().availableProcessors();
        System.out.println("Creating CpuTask with available cores: " + core);
        try(ExecutorService service = Executors.newFixedThreadPool(core)){
            for(int i=0; i<20; ++i){
                service.execute(new CpuTask());
            }
        }
    }
}

class CpuTask implements Runnable {
    @Override
    public void run(){
        System.out.println("Thread is "+Thread.currentThread().getName());
    }
}
