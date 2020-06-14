
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTests {
    Random random = new Random(System.currentTimeMillis());

    @Test
    public void testRunnable() throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            System.out.println("Thread " + Thread.currentThread() + " enter ...");
            try {
                Thread.sleep(random.nextInt(3000) + 5000);
            }
            catch (InterruptedException e) {
            }
            System.out.println("Thread " + Thread.currentThread() + " leave ...");
        };
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(runnable);
        CompletableFuture<Void> future2 = future1.thenRunAsync(runnable);
        CompletableFuture<Void> future3 = future2.thenRunAsync(runnable);
        future3.get();
    }
}
