import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EJ3{
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.automationexercise.com/products");

            WebElement campoBusqueda = wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")));
            campoBusqueda.sendKeys("jeans");

            WebElement botonBuscar = driver.findElement(By.id("submit_search"));
            botonBuscar.click();

            wt.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".title.text-center"), "SEARCHED PRODUCTS"));

            List<WebElement> productos = driver.findElements(By.cssSelector(".productinfo p"));
            int cantidad = productos.size();
            System.out.println("Cantidad de productos encontrados: " + cantidad);

            boolean coinciden = true;

            for (WebElement prod : productos) {
                String nombre = prod.getText();
                System.out.println("Producto: " + nombre);
                if (!nombre.toLowerCase().contains("jean")) {
                    coinciden = false;
                }
            }

            if (cantidad > 0 && coinciden) {
                System.out.println("Paso la prueba");
            } else {
                System.out.println("La prueba fallo");
            }

        } finally {
            driver.quit();
        }
    }

}