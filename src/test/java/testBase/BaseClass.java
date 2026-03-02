package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public Logger logger;
	public Properties p;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver driverInstance) {
		driver.set(driverInstance);
	}

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		String filepath = "./src/test/resources/config.properties";
		FileReader file = new FileReader(filepath);
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		String executionEnv = p.getProperty("execution_env");

		if (executionEnv == null) {
			throw new RuntimeException("execution_env not defined in config.properties");
		}

		WebDriver driverInstance = null;

		if ("remote".equalsIgnoreCase(executionEnv)) {

			ChromeOptions options = new ChromeOptions();

			if ("Windows".equalsIgnoreCase(os)) {
				options.setPlatformName("Windows 11");
			} else if ("mac".equalsIgnoreCase(os)) {
				options.setPlatformName("macOS");
			} else if ("linux".equalsIgnoreCase(os)) {
				options.setPlatformName("linux");
			}

			String gridUrl = p.getProperty("grid_url");
			driverInstance = new RemoteWebDriver(URI.create(gridUrl).toURL(), options);

		} else if ("local".equalsIgnoreCase(executionEnv)) {

			ChromeOptions options = new ChromeOptions();

			switch (br.toLowerCase()) {
			case "chrome":
				driverInstance = new ChromeDriver(options);
				break;
			case "firefox":
				driverInstance = new FirefoxDriver();
				break;
			case "edge":
				driverInstance = new EdgeDriver();
				break;
			default:
				throw new RuntimeException("Invalid Browser Name");
			}
		} else {
			throw new RuntimeException("Invalid execution_env value");
		}
		setDriver(driverInstance);

		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//getDriver().manage().window().maximize();
		getDriver().get(p.getProperty("appURL"));

		String className = this.getClass().getSimpleName();
		String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
		String logFileName = className + "_" + timeStamp;

		ThreadContext.put("testName", logFileName);

	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void teardown() {
		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
		}
	}

	public String randomString() {

		RandomStringGenerator alphagenerator = new RandomStringGenerator.Builder().withinRange('a', 'z').get();
		return (alphagenerator.generate(6));
	}

	@AfterClass(alwaysRun = true)
	public void clearContext() {
		ThreadContext.clearAll();
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		// sourceFile.renameTo(targetFile);
		FileUtils.copyFile(sourceFile, targetFile);

		return targetFilePath;
	}

}
