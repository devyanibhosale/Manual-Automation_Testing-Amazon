package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.Base;

public class SearchPage extends Base {

    public void searchForProduct(String productName) throws InterruptedException {
        if (loc.getProperty("search_bar") != null && loc.getProperty("search_btn") != null) {
            driver.findElement(By.id(loc.getProperty("search_bar"))).sendKeys(productName);
            Thread.sleep(3000);
            driver.findElement(By.xpath(loc.getProperty("search_btn"))).click();
        } else {
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
            searchBox.sendKeys(productName);
            searchBox.submit();
        }
    }

    public boolean areSearchResultsDisplayed() {
        if (loc.getProperty("search_results") != null) {
            // Check if search results are displayed on the page
            return !driver.findElements(By.xpath(loc.getProperty("search_results"))).isEmpty();
        } else {
            System.out.println("Search results element not found in properties file.");
            return false;
        }
    }

    public boolean areSearchResultsFilteredByBrand(String brand) {
        if (loc.getProperty("brand_checkbox") != null) {
            // Check if search results are filtered by the specified brand
            return !driver.findElements(By.xpath(loc.getProperty("brand_checkbox").replace("$brand$", brand))).isEmpty();
        } else {
            System.out.println("Brand filter element not found in properties file.");
            return false;
        }
    }

    public void applyPriceFilter(String minPrice, String maxPrice) {
        if (loc.getProperty("low-price") != null && loc.getProperty("high-price") != null && loc.getProperty("go_btn") != null) {
            WebElement minPriceInput = driver.findElement(By.xpath(loc.getProperty("low-price")));
            WebElement maxPriceInput = driver.findElement(By.xpath(loc.getProperty("high-price")));
            minPriceInput.sendKeys(minPrice);
            maxPriceInput.sendKeys(maxPrice);
            driver.findElement(By.xpath(loc.getProperty("go_btn"))).click();
        } else {
            System.out.println("Price filter elements not found in properties file.");
        }
    }

    public void applyBrandFilter(String brand) {
        if (loc.getProperty("brand_checkbox") != null) {
            WebElement brandCheckbox = driver.findElement(By.xpath(loc.getProperty("brand_checkbox").replace("$brand$", brand)));
            brandCheckbox.click();
        } else {
            System.out.println("Brand filter element not found in properties file.");
        }
    }

    public void clickGoButton() {
        if (loc.getProperty("go_btn") != null) {
            driver.findElement(By.xpath(loc.getProperty("go_btn"))).click();
        } else {
            System.out.println("Go button element not found in properties file.");
        }
    }

    public void goToNextPage() {
        if (loc.getProperty("next_button") != null) {
            driver.findElement(By.xpath(loc.getProperty("next_button"))).click();
        } else {
            System.out.println("Next button element not found in properties file.");
        }
    }

    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    public int getNumberOfSearchResults() {
        if (loc.getProperty("search_results") != null) {
            // Count the number of search results displayed
            return driver.findElements(By.xpath(loc.getProperty("search_results"))).size();
        } else {
            System.out.println("Search results element not found in properties file.");
            return -1;
        }
    }

    public void resetSearchAndFilters() {
        if (loc.getProperty("reset_button") != null) {
            WebElement resetButton = driver.findElement(By.xpath(loc.getProperty("reset_button")));
            resetButton.click();
        } else {
            System.out.println("Reset button element not found in properties file.");
        }
    }

    public boolean isNoResultsMessageDisplayed() {
        if (loc.getProperty("no_results_message") != null) {
            WebElement noResultsMessage = driver.findElement(By.xpath(loc.getProperty("no_results_message")));
            return noResultsMessage.isDisplayed();
        } else {
            System.out.println("No results message element not found in properties file.");
            return false;
        }
    }
}
