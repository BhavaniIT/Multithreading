
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newCachedThreadPool()){
            for(int i=0; i<10; ++i){
                service.execute(new TaskOne(i));
            }
        }
    }
}

class TaskOne implements Runnable {
    private int taskId;

    public TaskOne(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+":"+taskId);
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}