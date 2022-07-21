import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Page {

    protected WebDriver webDriver;
    private String appXpath = "/html/body/todo-app";
    private String listXpath = "//section/section/ul";
    private String listElementXpath = "//li";
    private String listElementEditXpath = "//li[@class='editing']/input";
    private String listElementTextXpath = "/label";
    private String inputBoxXpath = "./html/body/todo-app/section/header/input";
    private String listElementWithTextXpath = "//label[text()='%s']";
    private String listElementMarkAsCompleteCheckboxXpath = "//label[text()='%s']/../input";
    private String listElementRemoveItemButtonXpath = "//label[text()='%s']/../button";
    private String elementCountXpath = "//footer/span/strong";
    private String clearCompletedButtonXpath = "//footer/button[text() = 'Clear completed']";


    public Page(WebDriver webDriver) {this.webDriver = webDriver;}

    public WebElement appElement(){
        String xpath = String.format(appXpath);
        return webDriver.findElement(By.xpath(xpath));
    }
    public WebElement todosList(){
        String xpath = String.format(listXpath);
        return appElement().findElement(By.xpath(xpath));
    }

    public WebElement listElement(){
        String xpath = String.format(listElementXpath);
        return todosList().findElement(By.xpath(xpath));
    }

    public WebElement inputBoxElement(){
        String xpath = String.format(inputBoxXpath);
        return webDriver.findElement(By.xpath(xpath));
    }

    public void addItemToList(String itemText)
    {
        inputBoxElement().click();
        inputBoxElement().sendKeys(itemText);
        inputBoxElement().sendKeys(Keys.ENTER);
    }

    public WebElement removeItemButton(String elementText){
        String xpath = String.format(listElementRemoveItemButtonXpath, elementText);
        return listElement().findElement(By.xpath(xpath));
    }

    public WebElement findElementWithText(String elementText){
        String xpath = String.format(listElementWithTextXpath, elementText);
        return listElement().findElement(By.xpath(xpath));
    }

    public Integer elementCount(){
        String xpath = String.format(elementCountXpath);
        return Integer.parseInt(webDriver.findElement(By.xpath(xpath)).getText());
    }

    public WebElement listElementEdit(){
        String xpath = String.format(listElementEditXpath);
        return todosList().findElement(By.xpath(xpath));
    }

    public WebElement listItemCompletedCheckBoxForItemWithText(String elementText){
        String xpath = String.format(listElementMarkAsCompleteCheckboxXpath, elementText);
        return listElement().findElement(By.xpath(xpath));
    }

    public WebElement clearCompletedButton(){
        String xpath = String.format(clearCompletedButtonXpath);
        return webDriver.findElement(By.xpath(xpath));
    }
}
