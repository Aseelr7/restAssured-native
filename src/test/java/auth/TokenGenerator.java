package auth;

import api.common.EndPoints;
import config.ConfigLoader;
import config.Environments;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class TokenGenerator {

    private static String generateToken(String userName , String password){
        Environments currentEnv = Environments.getCurrentEnvironment();

        RestAssured.baseURI = currentEnv.getBaseUrl();

        Response response = given()
                        .contentType("application/json")
                .body("{ \"userName\": \"" + userName + "\", \"password\": \"" + password + "\" }")
                .post(EndPoints.ACCOUNT_GENERATE_TOKEN);

                if ( response.statusCode() == 200){
                    return response.jsonPath().getString("token");
                } else {
                    throw new RuntimeException("Failed to generate the token" +  response.getStatusCode());
                }

    }

    public static String getSupervisorToken(){
      String username =  ConfigLoader.getProperty("supervisor.username");
      String password =  ConfigLoader.getProperty("supervisor.password");
      return generateToken(username,password);
    }

    public static String getGeneralUserToken(){
        String username =  ConfigLoader.getProperty("generalUser.username");
        String password =  ConfigLoader.getProperty("generalUser.password");
        return generateToken(username,password);
    }

}
