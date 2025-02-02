
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConcurrentCache {
    private static final Map<String, String> cache = new HashMap<>();

    public static void main(String[] args) {
        try(ExecutorService service = Executors.newFixedThreadPool(10)){
            for(int i=0; i<10; ++i){
                final int threadNum = i;
                service.execute(() ->{
                    String key = "Key @ "+threadNum;
                    for(int j=0; j<3; ++j){
                        String value = getCachedValue(key);
                        System.out.println("Thread "+Thread.currentThread().getName() + "Key:"+key + "Value:"+value);
                    }     
                });
            }
        }
    }

    private static String getCachedValue(String key){
        String value = cache.get(key);
        if(value == null){
            value = compute(key);
            cache.put(key, value);           
        }
        return value;
    }
    
    private static String compute(String key){
        System.out.println(key+"not present in cache so going to compute");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return "Value for"+key;
    }
}
