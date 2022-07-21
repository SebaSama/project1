import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Tests extends TestBase {

    @Test
    public void checkElementIsAddedToTheListAndCounterUpdated(){
        Integer elementCount=0;
        WebDriver driver = getWebDriver();
        addElements();
        elementCount = homePage.elementCount();

        homePage.addItemToList("test item");

        Assert.assertTrue(homePage.findElementWithText("test item").isDisplayed());
        Assert.assertTrue(homePage.elementCount()==elementCount+1);
    }

    @Test
    public void checkElementIsRemovedByButtonAndCounterUpdated(){
        Integer elementCount=0;
        WebDriver driver = getWebDriver();
        addElements();
        elementCount = homePage.elementCount();

        Actions action = new Actions(driver);
        WebElement listEntry = homePage.findElementWithText("Old Item");
        action.moveToElement(listEntry).perform();

        WebElement removeItemButton = homePage.removeItemButton("Old Item");
        removeItemButton.click();

        Assert.assertTrue(homePage.elementCount()==elementCount-1);
        Assert.assertFalse(checkListElementExists("Old Item"));
    }

    @Test
    public void checkElementCanBeEdited(){
        WebDriver driver = getWebDriver();
        addElements();

        Actions action = new Actions(driver);
        WebElement listEntry = homePage.findElementWithText("Old Item");
        action.doubleClick(listEntry).perform();

        WebElement editListItem = homePage.listElementEdit();

        action.click(editListItem).perform();

        editListItem.click();
        editListItem.sendKeys(" edited");
        editListItem.sendKeys(Keys.ENTER);

        Assert.assertTrue(homePage.findElementWithText("Old Item edited").isDisplayed());
        Assert.assertFalse(checkListElementExists("Old Item"));

    }

    @Test
    public void checkCompleteItemsAreDeletedByClearButton(){
        WebDriver driver = getWebDriver();
        addElements();

        markItemCompleted("Some Item");
        markItemCompleted("New Item");

        WebElement clearCompletedButton = homePage.clearCompletedButton();
        clearCompletedButton.click();

        Assert.assertFalse(checkListElementExists("Some Item"));
        Assert.assertFalse(checkListElementExists("New Item"));

    }

    @Test
    public void checkRemoveItemButtonCannotBeAccessedIfNoEdit(){
        WebDriver driver = getWebDriver();
        addElements();

        WebElement testItem = homePage.findElementWithText("New Item");
        WebElement removeItemButton = homePage.removeItemButton("New Item");

        Assert.assertFalse(elementIsClicked(removeItemButton));


    }

    private void addElements(){
        homePage.addItemToList("New Item");
        homePage.addItemToList("Old Item");
        homePage.addItemToList("Some Item");
        homePage.addItemToList("Other Item");
        homePage.addItemToList("Whatever Item");
        
    }

    private boolean checkListElementExists(String elementText){
        try {
            homePage.findElementWithText(elementText);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private boolean elementIsClicked(WebElement element){
        try {
            element.click();
            return true;
        } catch (Exception ignored){
            return false;
        }
    }

    private void markItemCompleted(String elementText){
        homePage.listItemCompletedCheckBoxForItemWithText(elementText).click();
    }
}