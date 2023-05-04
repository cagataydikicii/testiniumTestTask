import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utiliy.log;

import static io.restassured.RestAssured.given;

public class trelloTest {
    protected static RequestSpecification urL;
    log log = new log();
    @BeforeAll
    public static void startUrl(){

        urL = new RequestSpecBuilder().
                setBaseUri("https://api.trello.com").
                build();
    }



    @Test
    public void trelloAPItest (){
        // Trello üzerinde bir board oluşturunuz.
        createBoardMethod();
        //
        createCardMethedo();
        createListMethod();

    }
    public void createBoardMethod (){
        // Trello üzerinde bir board oluşturunuz.
        urL.pathParams("pp1",1,"pp2","boards");
        log.info("Create Board testi basladi");
        urL.queryParam("name","cagatayBoard");
        urL.queryParam("key","6de01b37f9c18b21524961ab1702a24c");
        urL.queryParam("token","ATTA6f6faf0d467eb7e53d5b1bc6447a51202b1a905f5ac772e29a4afa900522b532D25301F4");

        Response response=    given().spec(urL)
                .contentType(ContentType.JSON).when().post("/{pp1}/{pp2}");

        response.prettyPrint();
        log.info("Create Board testi basarili");


    }
    public void createCardMethedo(){
        urL.pathParams("pp1",1,"pp2","cards");
        urL.queryParam("name","cagatayCards");
        urL.queryParam("key","6de01b37f9c18b21524961ab1702a24c");
        urL.queryParam("idList","63da7792c59ce5d882b0a9fd");
        urL.queryParam("token","ATTA6f6faf0d467eb7e53d5b1bc6447a51202b1a905f5ac772e29a4afa900522b532D25301F4");

        Response response=    given().spec(urL)
                .contentType(ContentType.JSON).when().post("/{pp1}/{pp2}");

        response.prettyPrint();
    }
    public void createListMethod (){
        log.info("Create List testi basladi");
        urL.pathParams("pp1",1,"pp2","lists");
        urL.queryParam("name","cagatayliste");
        urL.queryParam("key","6de01b37f9c18b21524961ab1702a24c");
        urL.queryParam("idBoard", "63da759455d4fc60d79e548f");
        urL.queryParam("token","ATTA6f6faf0d467eb7e53d5b1bc6447a51202b1a905f5ac772e29a4afa900522b532D25301F4");

        Response response=    given().spec(urL)
                .contentType(ContentType.JSON).when().post("/{pp1}/{pp2}");

        response.prettyPrint();
        log.info("Create List testi basarili");

    }
}
