package APIStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class APIWorkflowSteps {
    RequestSpecification request;
    Response response;
    public static String employee_id;

    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        request = given().
                header("Content-Type", "application/json").
                header("Authorization",GenerateTokenSteps.token).body("{\n" +
                        "  \"emp_firstname\": \"Elvira\",\n" +
                        "  \"emp_lastname\": \"Bissengaliyeva\",\n" +
                        "  \"emp_middle_name\": \"EB\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2023-05-20\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"SDET\"\n" +
                        "}");


    }
    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response =request.when().post("/createEmployee.php");

    }
    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer int1) {
        response.prettyPrint();
        response.then().assertThat().statusCode(int1);


    }

    @Then("the employee contains key {string} and  value {string}")
    public void the_employee_contains_key_and_value(String massage, String value) {
        response.then().assertThat().body(massage, equalTo(value));


        }

    @Then("the employee id {string} is stored as a global variable to be used for other calls")
    public void the_employee_id_is_stored_as_a_global_variable_to_be_used_for_other_calls(String string) {

        employee_id = response.jsonPath().getString(string);
        System.out.println(employee_id);


    }

}
