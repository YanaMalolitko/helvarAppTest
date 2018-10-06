package stepdefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class PerformanceStepDefs {

    Jedis jedis = new Jedis("127.0.0.1", 6379);
    Process process = null;

    @When("^I send JSON file is sent to Redis (\\d+) times$")
    public void i_send_JSON_file_is_sent_to_Redis_times(int replicationNumber) throws Throwable {

        for (int i = 0; i < replicationNumber; i++) {

            process = Runtime.getRuntime().exec("node ./src/main/helvar/node_modules/helvar-test/index.js  C:\\Users\\Yana\\IdeaProjects\\www\\helvarAppTest\\src\\main\\helvar\\test-data\\initialLoad.json\n");
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));
//            System.out.println("Error:" + stdError.readLine());
            String status = jedis.save();

            assertEquals("OK", status);
            assertFalse(stdInput == null);
            assertTrue(stdError != null);

        }
    }

    @Then("^it is successfully added$")
    public void it_is_successfully_added() throws Throwable {
        Long recordsNumber = jedis.hlen("helvar");
        System.out.println(jedis.info("CPU"));
        assertEquals("2", String.valueOf(recordsNumber));
    }
}
