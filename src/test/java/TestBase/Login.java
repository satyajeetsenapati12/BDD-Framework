/*
 * package TestBase;
 * 
 * 
 * 
 * 
 * import java.io.FileReader; import java.io.IOException; import
 * java.time.Duration; import java.util.Properties; import
 * java.util.concurrent.TimeUnit;
 * 
 * import org.openqa.selenium.By; import
 * org.openqa.selenium.support.ui.ExpectedConditions; import
 * org.openqa.selenium.support.ui.WebDriverWait; import org.testng.Assert;
 * 
 * import com.constant.Constants; import com.constant.CoreConstants; import
 * com.constant.CoreConstants.screenShotType; import
 * com.testBase.MainExecutionClass; import
 * com.warrenstrange.googleauth.GoogleAuthenticator;
 * 
 * import action.core.ActionPerformer; import page.Loginpage; import page.Order;
 * 
 * public class Login extends MainExecutionClass {
 * 
 * Loginpage login = new Loginpage();
 * 
 * ActionPerformer actions = new ActionPerformer(CoreConstants.driver);
 * 
 * Order order=new Order(); //ActionPerformer action = new
 * ActionPerformer(CoreConstants.driver); // String LoginExcel =
 * "src/main/resources/Excel/Login.xlsx"; static Properties props = new
 * Properties();
 * 
 * public void loginRecVue(String username, String password) throws Exception {
 * 
 * FileReader reader = new
 * FileReader("src/main/resources/configuration/Credentials.properties");
 * props.load(reader); ActionPerformer actions = new
 * ActionPerformer(CoreConstants.driver);
 * 
 * CoreConstants.driver.get(Constants.ApplicationURL);
 * CoreConstants.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10
 * ));
 * //CoreConstants.driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS
 * );
 * 
 * actions.enterText(login.username, username);
 * 
 * CoreConstants.report.logs("Enter username", "PASS");
 * actions.enterText(login.password, password);
 * 
 * CoreConstants.report.logs("Enter password", "PASS");
 * 
 * try { CoreConstants.driver.findElement(login.signIn).click();
 * timer.pause(10); } catch (Exception e) { timer.pageLoad(); timer.pause(5);
 * CoreConstants.driver.navigate().refresh(); timer.pause(10);
 * CoreConstants.driver.findElement(login.signIn).click(); timer.pause(10); }
 * 
 * String otp = getOTP(); verifyGoogleAuthentication(otp);
 * 
 * } private static String getOTP() throws IOException { FileReader reader1 =
 * new FileReader("src/main/resources/configuration/Credentials.properties");
 * props.load(reader1); GoogleAuthenticator gAuth = new GoogleAuthenticator();
 * 
 * // Generate the current OTP int otp =
 * gAuth.getTotpPassword(props.getProperty("secretkey")); String otpnew =
 * String.format("%06d", otp); System.out.println("otp is"+otpnew); return
 * String.valueOf(otpnew);
 * 
 * 
 * } public void verifyGoogleAuthentication(String otp) throws Exception {
 * 
 * actions.javascriptClick(login.arrowbuttonforauthenticatoion);
 * actions.javascriptClick(login.selectgoogleauthentication);
 * 
 * Thread.sleep(5000); actions.javascriptClick(login.inputbox);
 * //inputbox.click(); actions.enterText(login.inputbox, otp);
 * //inputbox.sendKeys(otp); actions.javascriptClick(login.Click_verify);
 * 
 * 
 * }
 * 
 * 
 * public void logoutRecVue() throws Exception {
 * 
 * actions.javascriptClick(order.clickusericon);
 * actions.javascriptClick(order.clicklogout);
 * System.out.print("logout from system"); }
 * 
 * 
 * 
 * }
 */