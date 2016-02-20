package ThreadPools_5;

/**
 * ThreadPool ("number of workers in a factory")
 * <br><br>
 * Codes with minor comments are from
 * <a href="http://www.caveofprogramming.com/youtube/">
 * <em>http://www.caveofprogramming.com/youtube/</em>
 * </a>
 * <br>
 * also freely available at
 * <a href="https://www.udemy.com/java-multithreading/?couponCode=FREE">
 *     <em>https://www.udemy.com/java-multithreading/?couponCode=FREE</em>
 * </a>
 *
 * @author Z.B. Celik <celik.berkay@gmail.com>
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
        System.out.println("Completed: " + id);
    }
}

public class App {

    public static void main(String[] args) {
        /**
         * Created 2 threads, and assign tasks (Processor(i).run) to the threads
         * Basically what we have here is 2 threads working for us, using this 2 threads we are going
         * process all jobs. 
         */
        ExecutorService executor = Executors.newFixedThreadPool(2);//2 Threads, it means that 2 threads will work simultaneously 
        for (int i = 0; i < 2; i++) { // call the (Processor(i).run) 2 times with 2 threads
            executor.submit(new Processor(i)); //queue of jobs that will process in 2 threads 
        }
        executor.shutdown(); // shutdown when finish
        System.out.println("All tasks submitted.");
        try {
            executor.awaitTermination(1, TimeUnit.DAYS); // limit of time to run the pool
        } catch (InterruptedException ignored) {
        }
        System.out.println("All tasks completed.");
    }
}
