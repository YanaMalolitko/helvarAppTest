package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Test;
import redis.clients.jedis.Jedis;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static stepdefs.Constants.*;


public class IntegrationStepDefs {


    Jedis jedis = new Jedis(HOST, PORT);
    Map<String, String> allRecords = null;
    Process process = null;


    @Given("^The JSON \"([^\"]*)\" file is sent to Redis$")
    public void the_JSON_file_is_sent_to_Redis(String testFile) throws Exception {

        process = Runtime.getRuntime().exec(RUN_TEST_COMMAND+TEST_DATA_PATH  + testFile + "\n");
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));
        System.out.println("Error:" + stdError.readLine());

        String status = jedis.save();

        assertEquals("OK", status);
        assertFalse(stdInput == null);
        assertFalse(stdError == null);
    }

    @When("^I see that (\\d+) devices are in the DB$")
    public void i_see_that_devices_are_in_the_DB(String expectedNumber) throws Exception {
        String actualNumber = String.valueOf(jedis.hlen(KEY));
        assertEquals("Return number of devices ", expectedNumber, actualNumber);
    }

    @Then("^I request the information about all devices$")
    public void i_request_the_information_about_all_devices() throws Throwable {
        allRecords = jedis.hgetAll(KEY);
    }

    @Then("^receive full \"([^\"]*)\" \"([^\"]*)\" data$")
    public void receive_full_data(String id, String information) throws Exception {

        assertTrue(allRecords.containsKey(id));
        assertTrue(allRecords.containsValue(information));
        System.out.println("Actual data: " + allRecords);
        System.out.println("Expected Id: " + id + " and expected Value: " + information);

    }

    @Then("^for concrete \"([^\"]*)\" the device \"([^\"]*)\" is correct$")
    public void device_device_for_concrete_is_correct(String id, String information) throws Throwable {

        String actualDataById = jedis.hget(KEY, id);
        System.out.println(actualDataById);
        assertEquals(information, actualDataById);

    }


    @When("^I send the devices information in a wrong format$")
    public void i_send_the_devices_information_in_a_wrong_format(List<String> fileName) throws Throwable {

        for (int i = 0; i < fileName.size(); i++) {
            process = Runtime.getRuntime().exec(RUN_TEST_COMMAND+TEST_DATA_PATH  + fileName.get(i) + "\n");
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));
            System.out.println(fileName.get(i));
            System.out.println(stdError.readLine());

            assertTrue(stdError.readLine() != null);

        }
    }

    @Then("^I get the error message$")
    public void i_get_the_error_message() throws Throwable {

    }

    @When("^I delete the data by \"([^\"]*)\" key from DB$")
    public void i_delete_the_data_by_key_from_DB(String key) throws Throwable {
        String deletionRequest = String.valueOf(jedis.del(key));
        Thread.sleep(2000);
        assertEquals(String.valueOf(deletionRequest), "1");
    }

}
