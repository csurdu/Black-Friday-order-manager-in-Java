import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Tema2 {

    public static void main(String[] args) throws IOException {
        AtomicInteger inQueue = new AtomicInteger(0);
        String s1 = args[0] + "/orders.txt";
        ExecutorService tpe = Executors.newFixedThreadPool(Integer.parseInt(args[1]));
        inQueue.incrementAndGet();
        ConcurrentHashMap<String, Integer> orders_map = new ConcurrentHashMap<String, Integer>();
        ConcurrentHashMap<String, ArrayList<String>> multiValueMap = new ConcurrentHashMap<String, ArrayList<String>>();
        MyRunnable r1 = new MyRunnable(s1, tpe, inQueue, orders_map, multiValueMap, Integer.parseInt(args[1]), args[0]);
        tpe.submit(r1);
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("orders_out.txt"));
        writer.flush();
        writer = Files.newBufferedWriter(Paths.get("order_products_out.txt"));
        writer.flush();
    }
}
