import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EJ1 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("https://www.selenium.dev/selenium/web/web-form.html");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement campoTexto = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.name("my-text"))
            );

            campoTexto.sendKeys("Maycol y Kevin");

            WebElement campoPassword = driver.findElement(By.name("my-password"));
            campoPassword.sendKeys("ClaveSegura123");

            WebElement areaTexto = driver.findElement(By.name("my-textarea"));
            areaTexto.sendKeys("por favor no apoyar los pies en la pared");

            WebElement elementoSelect = driver.findElement(By.name("my-select"));
            Select dropdown = new Select(elementoSelect);
            dropdown.selectByValue("2");

            WebElement checkbox = driver.findElement(By.id("my-check-2"));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }

            WebElement botonSubmit = driver.findElement(By.cssSelector("button[type='submit']"));
            botonSubmit.click();

            WebElement mensajeRespuesta = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("message"))
            );

            String textoMensaje = mensajeRespuesta.getText();

            if ("Received!".equalsIgnoreCase(textoMensaje)) {
                System.out.println("RESULTADO: Prueba EXITOSA. Mensaje recibido: " + textoMensaje);
            } else {
                System.out.println("RESULTADO: Prueba FALLIDA. Mensaje obtenido: " + textoMensaje);
            }

        } catch (Exception e) {
            System.out.println("RESULTADO: Prueba FALLIDA por excepción  " + e.getMessage());
        } finally {

        }
    }
}