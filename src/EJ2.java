import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EJ2 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("https://bstackdemo.com/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shelf-item")));

            List<WebElement> productosIniciales = driver.findElements(By.cssSelector(".shelf-item__title"));
            int cantidadInicial = productosIniciales.size();
            System.out.println("Cantidad inicial de productos: " + cantidadInicial);

            WebElement opcionSamsung = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Samsung']"))
            );

            WebElement checkboxSamsung = driver.findElement(By.cssSelector("input[value='Samsung']"));
            if (!checkboxSamsung.isSelected()) {
                opcionSamsung.click();
            }


            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".shelf-item__title"), cantidadInicial));

            List<WebElement> productosFiltrados = driver.findElements(By.cssSelector(".shelf-item__title"));
            int cantidadFinal = productosFiltrados.size();
            System.out.println("Cantidad de productos después del filtro: " + cantidadFinal);

            System.out.println("Nombres de los productos filtrados:");
            for (WebElement producto : productosFiltrados) {
                System.out.println("- " + producto.getText());
            }

            if (cantidadFinal < cantidadInicial) {
                System.out.println("resultado: Prueba exitosa. La cantidad final es menor a la inicial.");
            } else {
                System.out.println("resultado: Prueba fallida. El filtro no redujo los productos.");
            }

        } catch (Exception e) {
            System.out.println("resultado: Prueba fallida por excepción" + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}