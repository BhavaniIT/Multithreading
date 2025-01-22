
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    public static void main(String[] args) {
        try(ExecutorService executorService = Executors.newFixedThreadPool(2)){
            Future<Map<Integer, Integer>> future = executorService.submit(new ReturnValueTask(5));

            try{
                System.out.println(future.get());
            } catch(InterruptedException e){
                throw new RuntimeException(e);
            } catch(ExecutionException e){
                throw new RuntimeException(e);

            }
        }
        System.out.println("Main Thread Continues");
    }
}


class ReturnValueTask implements Callable<Map<Integer, Integer>>{
    Map<Integer, Integer> map;
    int id;

    public ReturnValueTask(int id){
        this.map = new HashMap<>();
        this.id = id;
    }
    @Override
    public Map<Integer, Integer> call() throws Exception{
        for(int i=0; i<id; ++i){
            System.out.println("Executing thread:"+Thread.currentThread().getName());
            map.put(i, i+10);
        }
        return map;
    }
}