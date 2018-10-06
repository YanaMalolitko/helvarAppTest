import org.junit.Test;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class test {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    Map<String, String> allRecords = null;
    //    String process = "Action";
    Executor executor;

    @Test
    public void action() throws IOException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            public void run() {
                try {

                    for (int i = 0; i < 4; i++) {
                        Runtime.getRuntime().exec("node ./src/main/helvar/node_modules/helvar-test/index.js  C:\\Users\\Yana\\IdeaProjects\\www\\helvarAppTest\\src\\main\\helvar\\test-data\\test"+i+".json\n");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();

    }

    @Test
    public void monitor() throws IOException {
//       process = Runtime.getRuntime().exec("node ./src/main/helvar/node_modules/helvar-test/index.js  C:\\Users\\Yana\\IdeaProjects\\www\\helvarAppTest\\src\\main\\helvar\\test-data\\initialLoad.json\n");
//        BufferedReader stdInput = new BufferedReader(new
//                InputStreamReader(process.getInputStream()));
//        BufferedReader stdError = new BufferedReader(new
//                InputStreamReader(process.getErrorStream()));
//        System.out.println("Error:" + stdError.readLine());
//
//        System.out.println(jedis.hlen("helvar"));
        System.out.println(jedis.hgetAll("helvar"));
//       System.out.println(jedis.del("helvar"));
//
//
//        assertEquals("OK", status);
//        assertFalse(stdInput == null);
//        assertFalse(stdError == null);
    }




}


