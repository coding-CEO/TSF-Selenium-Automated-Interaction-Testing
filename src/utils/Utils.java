package utils;

import org.openqa.selenium.JavascriptExecutor;

public class Utils {

    public static void scroll(JavascriptExecutor javascriptExecutor, int verticalScrollPixel) {
        int delta = 10;

        while(verticalScrollPixel > 0){
            javascriptExecutor.executeScript("window.scrollBy(" + 0 + ", " + delta + ")");
            verticalScrollPixel -= delta;
        }
        while(verticalScrollPixel < 0) {
            javascriptExecutor.executeScript("window.scrollBy(" + 0 + ", " + (-1 * delta) + ")");
            verticalScrollPixel += delta;
        }
    }
}
