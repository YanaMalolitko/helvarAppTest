import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DBconfig {

    public static void main(String[] args) throws Exception {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //jedis.auth("password");
        System.out.println("Connected to Redis");
        System.out.println("Server ping:" + jedis.ping());
//        System.out.println("Server info" + jedis.info());

//
//        Process process = Runtime.getRuntime().exec("node ./src/main/helvar/node_modules/helvar-test/index.js  C:\\Users\\Yana\\IdeaProjects\\www\\helvarAppTest\\src\\main\\helvar\\test-data\\headerDeleted.json\n");
//        BufferedReader stdInput = new BufferedReader(new
//                InputStreamReader(process.getInputStream()));
//            System.out.println("Input:" + stdInput.readLine());
//
//        BufferedReader stdError = new BufferedReader(new
//                InputStreamReader(process.getErrorStream()));
//        System.out.println("Error:" + stdError.readLine());
//
//        Assert.assertFalse(stdInput==null);
//        Assert.assertFalse(stdError==null);

//        Map<String, String> allRecords=  jedis.hgetAll("helvar");
//        System.out.println(allRecords);
//                System.out.println("DB response:" +jedis.del("helvar"));
//        System.out.println("DB response:" +jedis.hlen("helvar"));

        System.out.println("DB response:" +jedis.hgetAll("helvar"));
//        System.out.println("DB response:" +jedis.hget("helvar","1000"));
//        System.out.println("DB response:" +jedis.hlen("helvar"));
    }

 @Test
    public void runCommand () throws Exception
 {
     Jedis jedis = new Jedis("127.0.0.1", 6379);
     Process process = Runtime.getRuntime().exec("node ./src/main/helvar/node_modules/helvar-test/index.js  C:\\Users\\Yana\\IdeaProjects\\www\\helvarAppTest\\src\\main\\helvar\\test-data\\payloadNotArray.json\n");
     BufferedReader stdInput = new BufferedReader(new
             InputStreamReader(process.getInputStream()));
        System.out.println("Input:" + stdInput.readLine());

     BufferedReader stdError = new BufferedReader(new
             InputStreamReader(process.getErrorStream()));
       System.out.println("Error:" + stdError.readLine());
     assertTrue(stdError !=null);
     System.out.println( stdError.readLine().contains("type"));
 }


}
