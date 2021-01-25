package ObjectRepository;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
	
    WebDriver driver;
   
    
    
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Capturing xpaths
	@FindBy(xpath = "//span[contains(text(),'sign in')]")
	private WebElement SignInButton;
	
	@FindBy(xpath = "//div//following-sibling::input[@name='login']")
	private WebElement UserName;
	
	@FindBy(xpath = "//div//following-sibling::input[@placeholder='Password']")
	private WebElement Password;
	
	@FindBy(xpath = "//div//following-sibling::input[@value='Sign In']")
	private WebElement LoginButton;
	
	@FindBy(xpath = "//div[@id='toast-close-button']")
	private WebElement toastCloseButton;
	
	@FindBy(xpath = "//header/nav[1]/section[1]/ul[1]/li[3]/a[1]")
	private WebElement libraryButton;
	
	@FindBy(xpath = "//a[@id='dropdown-create']")
	private WebElement CreateNewButton;
	
	@FindBy(xpath = "//ul[@class='f-dropdown content-type-dropdown open']/li[4]/a[@title='New Quiz']")
	private WebElement QuizButton;
	
	
	@FindBy(xpath = "//input[@id='testTitle']")
	private WebElement QuizNewTitle;
	
	@FindBy(xpath = "//textarea[@id='testDescription']")
	private WebElement QuizDescription;
	
	@FindBy(xpath = "//form[1]/li[1]/div[2]/div[1]/a[1]")
	private WebElement QuizNumberofAttempts;
	
	@FindBy(xpath = "//form[1]/li[2]/div[2]/div[1]/div[2]/div[1]/a[1]")
	private WebElement QuizTimeLimit;
	
	@FindBy(xpath = "//input[@id='languageSelector']")
	private WebElement QuizLanguage;
	
	@FindBy(xpath = "//body/section[@id='testContent']/div[2]/div[1]/div[2]/ul[1]/li[1]/div[2]/div[1]/label[1]/span[1]")
	private WebElement QuizShowSolution;

	@FindBy(xpath = "//iframe[@id='assessmentFrame']")
	private WebElement getiframe;
	
	@FindBy(xpath = "//input[@id='timeLimit']")
	private WebElement gettimeLimit;
	
	
	//Creating methods
	
	String SelectQuizLanguage = "//body/ul[@id='ui-id-1']/li";
	public void getQuizLanguage() {
		try {
			checkPageIsReady();
			click(QuizLanguage);
			checkPageIsReady();
			getSelectValue(SelectQuizLanguage,"Portuguese-pt");
			checkPageIsReady();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	
	
	
	
	String SelectQuizTimeLimit = "//body/section[@id='testContent']/div[2]/div[1]/div[2]/ul[1]/form[1]/li[2]/div[2]/div/div[2]/div/ul/li";
	public void getQuizTimeLimit() {
		try {
			checkPageIsReady();
			click(QuizTimeLimit);
			checkPageIsReady();
			getSelectValue(SelectQuizTimeLimit,"min");
			checkPageIsReady();
			gettimeLimit.sendKeys("10");
			checkPageIsReady();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	
	public void getQuizNewTitle(String TextBoxData) {
		try {
			QuizNewTitle.sendKeys(TextBoxData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	
	public void getQuizDescription(String TextBoxData) {
		try {
			QuizDescription.sendKeys(TextBoxData);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	
	
	public void getNumberofAttempts() {
		try {
			checkPageIsReady();
			click(QuizNumberofAttempts);
			checkPageIsReady();
			getSelectValue(SelectNumberofAttempts,"4");
			checkPageIsReady();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	
	String SelectNumberofAttempts = "//body/section[@id='testContent']/div[2]/div[1]/div[2]/ul[1]/form[1]/li[1]/div[2]/div[1]/ul/li";
	public void getSelectValue(String locator, String value) {
		List<WebElement> SelectValueList = driver.findElements(By.xpath(locator));
		int countValueList = SelectValueList.size();
		System.out.println("count of ValueList : " + countValueList);
		for(int i=0;i<countValueList;i++)	
		{ 
			System.out.println(SelectValueList.get(i).getText());
		if(SelectValueList.get(i).getText().equals(value))
		{
			SelectValueList.get(i).click();
			break;
		}	
			}	
		}
		
	
	
	public void getSwitchIframe() {
		try {
			driver.switchTo().frame(getiframe);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	
	public void getOutOfSwitchIframe() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	
	
	
	public void getlibraryButton() {
		try {
			click(libraryButton);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	
	@FindBy(xpath = "//input[@id='testTitle']")
	List<WebElement> QuizNewTitleTextBox;
	public List<WebElement> SearchResults() {
	    return QuizNewTitleTextBox;
	}
	
	
	public void getiFramePage() {
		List<WebElement> iFrameList = driver.findElements(By.tagName("iframe"));
		int countIframe = iFrameList.size();
		System.out.println("count of Iframe : " + countIframe);
		for(int i=0;i<countIframe;i++)
		{
			driver.switchTo().frame(i);
			QuizNewTitle.sendKeys("test2");
			QuizDescription.sendKeys("text3");
			driver.switchTo().defaultContent();
			break;
		}
		}	
	
	
	public void setQuizNewTitleTextBox(String strQuizNewTitleTextBox) {
		try {
			QuizNewTitle.sendKeys(strQuizNewTitleTextBox);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AssertionError("QuizNewTitle not entered", e);
		}
	}
	
	public void checkPageIsReady() throws InterruptedException {
		  
		  JavascriptExecutor js = (JavascriptExecutor)driver;
		  if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		   System.out.println("Page Is loaded Successfully");
		  } 
		  Thread.sleep(2000);
		  //This loop will rotate for 25 times to check If page Is ready after every 1 second.
		  //You can replace your value with 25 If you wants to Increase or decrease wait time.
		  for (int i=0; i<60; i++){ 
		   try {
		    Thread.sleep(1000);
		    }catch (InterruptedException e) {} 
		   //To check page ready state.
		   if (js.executeScript("return document.readyState").toString().equals("complete")){ 
		    break; 
		   }   
		  }
		  }
		
	
	By addItem = By.xpath("//header/nav[1]/section[1]/ul[1]/li[3]/a[1]");
	public void LibraryTab(){
		WebDriverWait wait = new WebDriverWait(driver, 2000);
		
		// get the "Add Item" element
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(addItem));

		// wait the element "Add Item" to become stale
		wait.until(ExpectedConditions.stalenessOf(element));

		// click on "Add Item" once the page is reloaded
		wait.until(ExpectedConditions.presenceOfElementLocated(addItem)).click();
	}
	
	
	public void getCreateNewButton() {
		try {
			click(CreateNewButton);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	
	public void getQuizButton() {
		try {
			click(QuizButton);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	public void gettoastCloseButton() {
		if(toastCloseButton.isDisplayed())
		{
			WebDriverWait wait=new WebDriverWait(driver, 60);
			toastCloseButton= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-close-button']")));
			toastCloseButton.click();
		}else
			{
				System.out.println("No toastCloseButton is present");
			}
		}
	
	public void getSignInButton() {
		try {
			click(SignInButton);
			pause(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}	
	
	public void click(WebElement elementToBeClicked){
	    WebDriverWait wait = new WebDriverWait(driver, 3000);
	    wait.until(ExpectedConditions.visibilityOf(elementToBeClicked));
	    wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked)); 
	    wait.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class); elementToBeClicked.click(); 
	 }
		
	public void setUserName(String strUserName) {
		try {
			UserName.sendKeys(strUserName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AssertionError("UserName not entered", e);
		}
	}

	public void setPassword(String strPassword) {
		try {
			Password.sendKeys(strPassword);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AssertionError("Password not entered", e);
		}	
	}

	public void getLoginButton() {
		try {
			LoginButton.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public void setLoginCredentials(String strUserName , String strPassword) {
		try {
			UserName.sendKeys(strUserName);
			Password.sendKeys(strPassword);
			LoginButton.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AssertionError("Password not entered", e);
		}	
	}
	
	public void implicitlyWait() {
	try {
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	
	public void pause(Integer milliseconds){
	    try {
	        TimeUnit.MILLISECONDS.sleep(milliseconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public void safeJavaScriptClick(WebElement element) throws Exception {
		try {
		if (element.isEnabled() && element.isDisplayed()) {
		System.out.println("Clicking on element with using java script");

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		} else {
		System.out.println("Unable to click on element");
		}
		} catch (StaleElementReferenceException e) {
		System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
		System.out.println("Element was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
		System.out.println("Unable to click on element " + e.getStackTrace());
		}
		}
	
}
