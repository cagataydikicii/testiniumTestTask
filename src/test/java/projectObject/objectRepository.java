package projectObject;

import org.openqa.selenium.By;

public class objectRepository {
    public class mainPageObjectRepository{
        public static final By popUpMenu = By.xpath("//body/div[3]/div[5]/div[2]/div[1]/div[1]/button[1]/*[1]");
        public static final By popUpCookie = By.xpath("(//*[text()='Tüm Çerezleri Kabul Et'])[1]");
        public static final By searchBox = By.xpath("//header/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/input[1]");
        public static final By genderButton = By.xpath("//button[@id='genderManButton']");

        public static final By getSearchBox = By.cssSelector("[name='q']");

    }
    public class productPageObjectRepository{
        public static final By productList = By.xpath("//*[@class='m-productCard__desc']");

        public static final By productName = By.xpath("//body/div[3]/div[1]/div[1]/div[2]/div[2]/h1[1]/span[1]");
        public static final By productPrice = By.xpath("//ins[@id='priceNew']");
        public static final By productSelectBox = By.xpath("//body/div[@id='__next']/div[2]/div[1]/div[1]/div[2]/div[5]/div[2]/div[2]/div[1]/div[1]");
        public static final By productSize = By.xpath("(//*[@class='m-variation__item -criticalStock'])[1]");
        public static final By productSizeWomen = By.xpath("(//*[@class='m-variation__item'])[1]");
        public static final By addProduct = By.xpath("//button[@id='addBasket']");
        public static final By goToShoppingCart = By.xpath("//*[@class='o-header__userInfo--item bwi-cart-o -cart']");

    }

    public class shoppingCartObjectRepository {
        public static final By shoppingPrice = By.xpath("//body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[4]/span[2]");
        public static final By selectProductBox = By.xpath("//select[@class='a-selectControl -small']");
        public static final By productNumber = By.xpath("//body/div[@id='__next']/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/input[1]");
        public static final By productDelete = By.xpath("//body/div[@id='__next']/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/button[1]/*[1]");
        public static final By shoppingCartDelete = By.xpath("//button[@id='removeCartItemBtn0-key-0']");
        public static final By shoppingCartClean = By.xpath("//strong[contains(text(),'Sepetinizde Ürün Bulunmamaktadır')]");
    }
}
