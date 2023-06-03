package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
        public class HardCodedExamples {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODQ5NjkyNTgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTAxMjQ1OCwidXNlcklkIjoiNTIxOCJ9.uI2upFWnlrD9qAKx_NhRE57cEgs8GlKR3JGJWAnMBek";
    static String employee_id;

    @Test
    public void bgetCreatedEmployee() {
        RequestSpecification preparedRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);

        // hitting the endpoint
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        // verify the response
        response.then().assertThat().statusCode(200);

        String tempEmpId = response.jsonPath().getString("employee.employee_id");

        //we have 2 emp id, one is global and second is local
        Assert.assertEquals(employee_id, tempEmpId);

    }

    @Test
    public void acreatedEmployee() {
        //prepare the request
        RequestSpecification prepareRequest = given().
                header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Elvira\",\n" +
                        "  \"emp_lastname\": \"Bissengaliyeva\",\n" +
                        "  \"emp_middle_name\": \"EB\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2023-05-20\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"SDET\"\n" +
                        "}");

        //hitting the endpoint

        Response response = prepareRequest.when().post("/createEmployee.php");


        //it will print the output in the console

        response.prettyPrint();

        //verifying the assertions

        response.then().assertThat().statusCode(201);

        // we are capturing employee id from the response
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);

        //verifying the firstname in the response body

        response.then().assertThat().body("Employee.emp_firstname", equalTo("Elvira"));

        System.out.println("My test case is passed");

        //verify the response headers
        response.then().assertThat().header("Content-Type", "application/json");
        System.out.println("My test case is passed");

        //in homework, create a method to get all employee status and job title
    }

        @Test
        public void cupdateEmployee() {
            RequestSpecification preparedRequest = given().
                    header("Content-Type", "application/json").
                    header("Authorization", token).body("{\n" +
                            "  \"employee_id\": \""+employee_id+"\",\n" +
                            "  \"emp_firstname\": \"Elvira\",\n" +
                            "  \"emp_lastname\": \"Bissengaliyeva\",\n" +
                            "  \"emp_middle_name\": \"EB\",\n" +
                            "  \"emp_gender\": \"F\",\n" +
                            "  \"emp_birthday\": \"1989-02-14\",\n" +
                            "  \"emp_status\": \"confirmed\",\n" +
                            "  \"emp_job_title\": \"SDET\"\n" +
                            "}");

            //hitting the endpoint
            Response response = preparedRequest.when().put("/updateEmployee.php");
            response.then().assertThat().statusCode(200);
            //for verification
            response.then().assertThat().body("Message", equalTo("Employee record Updated"));
        }

    }

