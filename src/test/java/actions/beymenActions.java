package actions;

import org.openqa.selenium.By;
import utiliy.log;
import base.abtractPage;
import projectObject.objectRepository.mainPageObjectRepository;
import projectObject.objectRepository.productPageObjectRepository;
import projectObject.objectRepository.shoppingCartObjectRepository;

public class beymenActions extends  abtractPage{
    log log = new log();
    public beymenActions openUrl(String url){
        try{
            openBrowser();
            naviGateTo(url);
            log.info(url+" openUrl passed");
        }catch (Exception e){
            log.error("Hata!!"+url+" acılırken hata meydana geldi!!");
        }
        return this;
    }

    public beymenActions popUpControl() throws Exception {
        try {
            if (isElementExist(mainPageObjectRepository.popUpCookie)) {
                    click(mainPageObjectRepository.popUpCookie);
                    log.info("Goruntulenen  popUpa tiklandi");
            }
          /*  if (isElementExist(mainPageObjectRepository.popUpMenu)) {
                    clickJS(mainPageObjectRepository.popUpMenu);
                    log.info("Goruntulenen  popUpa tiklandi");

            }*/  if(isElementExist(mainPageObjectRepository.genderButton)){
                    wait(2);
                    click(mainPageObjectRepository.genderButton);
            }


        }catch (Exception e){
            throw new Exception("popUp tıklanırken Hata alındı");
        }
        return this;
    }

    public beymenActions searchAndClean( String text) throws InterruptedException {
        click(mainPageObjectRepository.searchBox);
         sendKeys(mainPageObjectRepository.searchBox,text,false);
         //şort yazdırıldığının görünürlüğü için sleep atıldı
         wait(3);
          log.info(text+" yazdirildi");
          cleanText(mainPageObjectRepository.searchBox);

        return this;
    }

    public beymenActions searchProdoct( String text) {
        sendKeys(mainPageObjectRepository.searchBox,text,true);
        log.info(text+" yazdirilip, aratildi");

        return this;
    }

    public beymenActions selectRandomProduct( ) throws InterruptedException {
        selectRandomProductList();
       return this;
    }

    public beymenActions productWriteText(By productName, By productPrice) {
        productNameAndPrice(productName,productPrice);
        return this;
    }

    public String addToShoppingCart() throws InterruptedException {
        String price = null;
        try {
            price = returnText(productPageObjectRepository.productPrice);
            if(isElementExist(productPageObjectRepository.productSize))
            click(productPageObjectRepository.productSize);
            if(isElementExist(productPageObjectRepository.productSizeWomen))
                click(productPageObjectRepository.productSizeWomen);

            log.info("Ürün bedeni secildi ");
            click(productPageObjectRepository.addProduct);
        }catch (Exception e){
            log.info("HATA!! "+e);
        }
        return price;
    }

    public beymenActions addProductAndCleanShoppingCart(String price) throws InterruptedException {

        try {

            click(productPageObjectRepository.goToShoppingCart);
            Thread.sleep(5000);
            wait(3);
            String shoppingPrice = returnText(shoppingCartObjectRepository.shoppingPrice);
            if (price == shoppingPrice) {
                log.info("Ürün fiyatı : " + price + " Sepetteki ürün fiyatı : " + shoppingPrice);
            } else log.error("Ürün fiyatı : " + price + " Sepetteki ürün fiyatı : " + shoppingPrice);

            selectProductBox(shoppingCartObjectRepository.selectProductBox);
            log.info("Ürün güncelleme adimi gecildi");
            wait(2);

            click(shoppingCartObjectRepository.shoppingCartDelete);
            log.info("Sepetteki ürün silindi ");
            if (isElementExist(shoppingCartObjectRepository.shoppingCartClean)) {
                log.info(" Sepetin bos oldugu teyit edildi ");
            }
        }catch (Exception e){
            log.info("Sepet temizlenirken hata alindi " + e);
        }

        return this;
    }
    public void closeBrowsers(){
        closeBrowser();
    }
}
