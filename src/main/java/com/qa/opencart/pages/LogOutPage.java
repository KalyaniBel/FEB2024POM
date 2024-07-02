import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;
public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
private By logoutBtn = By.xpath("//input[@value='Logout']");
  public void doLogout() {

    System.Out.println("Logging Out");
		eleUtil.doClick(loginBtn);
		
  }

	}
