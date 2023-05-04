package testiniumTest;
import org.testng.annotations.Test;
import data.excelOperations;
import data.getDataTestinium;
import actions.beymenActions;
import java.util.ArrayList;
import java.util.List;
import utiliy.log;
import projectObject.objectRepository.productPageObjectRepository;

public class testiniumTest {

    excelOperations excelOperations;
    beymenActions beymenActions;
    log log = new log();

    public List<String[]> testList = new ArrayList<String[]>();


    @Test()
    public  void  boynerTest() throws Exception {
        excelOperations = new excelOperations();

        log.info("********Start Testinium_Beyman_Test Case********");

        if(excelOperations.controlFile(getDataTestinium.filePath,getDataTestinium.fileName))
            throw new Exception("Hata..."+getDataTestinium.filePath+getDataTestinium.fileName+"dosyası bulunamamıştır");

        testList= excelOperations.excelReadtoList(getDataTestinium.filePath,getDataTestinium.fileName);
        log.info(testList+" içerigi dolduruldu..");

        String sort = testList.get(0)[0];
        String gomlek = testList.get(0)[1];

        beymenActions = new beymenActions();

        try {
            beymenActions.openUrl(getDataTestinium.boynerUrl);
            beymenActions.popUpControl();
            beymenActions.searchAndClean(sort);
            beymenActions.searchProdoct(gomlek);
            beymenActions.selectRandomProduct();
            beymenActions.productWriteText(productPageObjectRepository.productName, productPageObjectRepository.productPrice);
            String price = beymenActions.addToShoppingCart();
            beymenActions.addProductAndCleanShoppingCart(price);
            beymenActions.closeBrowser();
            log.info("Sürec basarili bir sekilde sonlandirildi. browser kapatildi...");

        }catch (Exception e){
            beymenActions.closeBrowser();
            log.info("Sürec esnasında hata alındıgı icin browser kapatildi....");
        }


    }


}
