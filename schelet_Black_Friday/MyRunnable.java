import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class MyRunnable implements Runnable {
    String path;
    ExecutorService tpe;
    AtomicInteger inQueue;
    ConcurrentHashMap<String, Integer> orders_map;
    ConcurrentHashMap<String, ArrayList<String>> multiValueMap;
    String drumfisier;
    int nrtreduri;


    public MyRunnable(String path, ExecutorService tpe, AtomicInteger inQueue, ConcurrentHashMap<String, Integer> orders_map, ConcurrentHashMap<String, ArrayList<String>> multiValueMap, int nrtreduri, String drumfisier) {
        this.path = path;
        this.tpe = tpe;
        this.inQueue = inQueue;
        this.orders_map = orders_map;
        this.multiValueMap = multiValueMap;
        this.nrtreduri = nrtreduri;
        this.drumfisier = drumfisier;

    }

    @Override
    public void run() {
        System.out.println(path);
        File file = new File(path);
        String s2 = drumfisier + "/order_products.txt";
        AtomicInteger inQueue2 = new AtomicInteger(0);
        ExecutorService tpe2 = Executors.newFixedThreadPool(nrtreduri);
        inQueue2.incrementAndGet();
        if (file.isFile()) {
            try {
                File myObj = new File(path);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] arr = data.split(",");
                    System.out.println(arr[0]);
                    orders_map.put(arr[0], Integer.parseInt(arr[1]));
                    tpe2.submit(new MyRunnable2(s2, tpe2, inQueue2, orders_map, multiValueMap, arr[0], Integer.parseInt(arr[1]), nrtreduri));

                }
                myReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int left = inQueue.decrementAndGet();
        if (left == 0) {
            tpe.shutdown();
        }
    }


}