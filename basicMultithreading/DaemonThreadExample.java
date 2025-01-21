
public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread bgHelper = new DaemonHelper();
        Thread userHelper = new UserHelper();
        
        bgHelper.setDaemon(true);

        bgHelper.start();
        userHelper.start();
    }
}

class DaemonHelper extends Thread{
    @Override
    public void run(){
        int count = 0;
        while (count < 500){
            try {
                Thread.sleep(1000);
                System.out.println("Daemon is running");
            } catch (InterruptedException e){
                System.out.println("Interrupted Exception");
            }
        }
    }
}

class UserHelper extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println("UserHelper is running");
        } catch (InterruptedException e) {
        }
    }
}
