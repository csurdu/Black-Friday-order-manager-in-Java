import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

class MyRunnable2 implements Runnable {
    String path;
    ExecutorService tpe;
    AtomicInteger inQueue;
    ConcurrentHashMap<String, Integer> orders_map;
    ConcurrentHashMap<String, ArrayList<String>> multiValueMap;
    String arr0;
    int arr1;
    int nrtreduri;

    public MyRunnable2(String path, ExecutorService tpe, AtomicInteger inQueue, ConcurrentHashMap<String, Integer> orders_map, ConcurrentHashMap<String, ArrayList<String>> multiValueMap, String arr0, int arr1, int nrtreduri) {
        this.path = path;
        this.tpe = tpe;
        this.inQueue = inQueue;
        this.orders_map = orders_map;
        this.multiValueMap = multiValueMap;
        this.arr0 = arr0;
        this.arr1 = arr1;
        this.nrtreduri = nrtreduri;

    }

    @Override
    public void run() {
        File file = new File(path);

        if (file.isFile()) {
            try {
                File myObj = new File(file.getPath());
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] arr = data.split(",");
                    String str1 = arr0 + "," + arr1 + "," + "shipped";
                    if (arr0.equals(arr[0]) && arr1 != 0) {
                        orders_map.put(arr0, (orders_map.get(arr0) - 1));
                        if (orders_map.get(arr0) == 0) {

                            try {

                                BufferedWriter writer = new BufferedWriter(new FileWriter("orders_out.txt", true));
                                writer.append(str1);
                                writer.append("\n");

                                writer.close();
                            } catch (FileNotFoundException e) {
                                System.out.println("An error occurred.");
                                e.printStackTrace();
                            }

                        }

                        String str = arr[0] + "," + arr[1] + "," + "shipped";
                        try {

                            BufferedWriter writer = new BufferedWriter(new FileWriter("order_products_out.txt", true));
                            writer.append(str);
                            writer.append("\n");

                            writer.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                    }

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