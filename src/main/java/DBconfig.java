import com.sun.org.glassfish.external.statistics.Statistic;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DBconfig {

    private static int REQUESTS;
    private static int NUM_THREADS;
    private static String URL;
    private static ArrayList<Statistic> result = new ArrayList<Statistic>();
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    private static class ThreadTask implements Runnable {

        private int tid;

        public ThreadTask(int tid) {
            this.tid = tid;
        }

        @Override
        public void run() {

//            Statistic stat = new Statistic();
            for(int i = 0; i < REQUESTS; i++) {
                // make request
                try {
                    Runtime.getRuntime().exec("node ./src/main/helvar/node_modules/helvar-test/index.js  C:\\Users\\Yana\\IdeaProjects\\www\\helvarAppTest\\src\\main\\helvar\\test-data\\test"+i+".json\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                stat.getDescription()
                // add results to stat
            }
          //  result.add(tid); // no need to lock because each
            // thread writes to a dedicated index
        }
    }

    public static void main(String[] args) {

        // take command line arguments
        REQUESTS = Integer.parseInt(args[3]);
        NUM_THREADS = Integer.parseInt(args[3]);

        Thread[] threads = new Thread[NUM_THREADS];

        // start threads
        for(int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new ThreadTask(i));
            threads[i].start();
        }

        // wait for threads to finish
        for(int i = 0; i < NUM_THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
