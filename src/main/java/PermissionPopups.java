//package br.com.vivo.Utils;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import java.util.HashMap;
//
//public class PermissionPopups {
//
//    //Browser Based PopUp, not HTML
//
//    public static void main(String[] args) throws Exception {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anicolle\\Downloads\\chromedriver_win32\\chromedriver.exe");
//        HashMap<String, Integer> conentSettings = new HashMap<String, Integer>();
//        HashMap<String, Object> profile = new HashMap<String, Object>();
//        HashMap<String, Object> prefs = new HashMap<String, Object>();
//
//        /*
//         *
//         * Managed default content settings
//         * notifications OR geolocation OR media_stream
//         * 0 - Ask(Default)
//         * 1 - Allow
//         * 2 - Block
//         * */
//        conentSettings.put("notifications", 0);
//        conentSettings.put("geolocation", 0);
//        conentSettings.put("media_stream", 0);
//        profile.put("managed_default_content_settings", conentSettings);
//        prefs.put("profile", profile);
//
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", prefs);
//
////            To disable the notifications based permission popup
////            		options.addArguments("disable-notifications");
//
////            To disable the location based permission popup
////            		options.addArguments("disable-geolocation");
//
////            To disable the microphone or camera based permission popup
////            		options.addArguments("disable-media-stream");
//
////            WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//
//        //To verify notification based permission popup
//        driver.get("https://123milhas.com/");
//
//        //To verify location based permission popup
////            driver.get("https://whatmylocation.com/");
//
//        //To verify microphone based permission popup
////            driver.get("https://mictests.com/");
////            Thread.sleep(6000);
////            driver.findElement(By.id("mic-launcher")).click();
////
////            //To verify camera based permission popup
////            driver.get("https://webcamtests.com/");
////            Thread.sleep(6000);
////            driver.findElement(By.id("webcam-launcher")).click();
//    }
//}
