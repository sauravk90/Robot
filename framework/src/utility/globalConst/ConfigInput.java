package utility.globalConst;

import utility.propertiesManager.AutProperties;

import java.io.FileNotFoundException;

public class ConfigInput {

    public static String browser, language, country, url;
    public static int explicitWait;

    /**
     * C O N F I G U R A T I O N   I N P U T
     */
    public static void init(){

        // explicit wait
        explicitWait = Integer.parseInt(AutProperties.getInstance().getProperty("wait.explicit"));

        // browser name
        //browser = AutProperties.getInstance().getProperty("browser.name");

        // application url
        url = AutProperties.getInstance().getProperty("app.url");

        // Localization code
        //language = AutProperties.getInstance().getProperty("locale.language");
        //country = AutProperties.getInstance().getProperty("locale.country");
    }
}
