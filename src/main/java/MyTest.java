import com.qa.utils.GlobalParams;

public class MyTest {
    public static void main(String[] args) {
        GlobalParams params = new GlobalParams();
        params.initializeGlobalParams();

        // Access platform-specific parameters
        System.out.println("Platform: " + params.getPlatformName());
        System.out.println("UDID: " + params.getUDID());
        System.out.println("Device: " + params.getDeviceName());
        System.out.println("System Port: " + params.getSystemPort());
        System.out.println("ChromeDriver Port: " + params.getChromeDriverPort());
    }
}

