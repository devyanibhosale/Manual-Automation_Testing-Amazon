package testcase;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.Base;
import pages.SearchPage;

public class MyFirstTestFW extends Base {

    @Test(priority = 1)
    public void testSearchForLaptop() throws InterruptedException {
        // Instantiate the SearchPage object
        SearchPage searchPage = new SearchPage();

        // Search for a laptop
        searchPage.searchForProduct("laptop");
        clearSearchBox(); // Clear search box after searching for a laptop
    }

    @Test(priority = 2)
    public void testSearchForBluetoothSpeaker() throws InterruptedException {
        // Instantiate the SearchPage object
        SearchPage searchPage = new SearchPage();

        // Search for a bluetooth speaker
        searchPage.searchForProduct("bluetooth speaker");
        clearSearchBox(); // Clear search box after searching for a bluetooth speaker
    }

    @Test(priority = 3)
    public void testSearchWithoutProduct() throws InterruptedException {
        // Instantiate the SearchPage object
        SearchPage searchPage = new SearchPage();

        // Search without entering any product
        searchPage.searchForProduct(" ");
        clearSearchBox(); // Clear search box after performing empty search
    }

    @Test(priority = 4)
    public void applyMultipleFiltersTest() throws InterruptedException {
        // Open Amazon
        driver.get("https://www.amazon.in/");

        SearchPage searchPage = new SearchPage();

        // Search for a product
        searchPage.searchForProduct("laptop");

        // Apply price filter within the specified range
        searchPage.applyPriceFilter("500", "1000");

        // Apply brand filter for Dell
        searchPage.applyBrandFilter("Dell");

        // Click the go button to apply filters
        searchPage.clickGoButton();
    }

    @Test(priority = 5)
    public void applyPriceFilterTest() throws InterruptedException {
        // Open Amazon
        driver.get("https://www.amazon.in/");

        // Search for a product
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("laptop");
        searchBox.submit();

        // Apply price filter within the specified range
        WebElement minPriceInput = driver.findElement(By.xpath(loc.getProperty("low-price")));
        WebElement maxPriceInput = driver.findElement(By.xpath(loc.getProperty("high-price")));
        minPriceInput.sendKeys("500");
        maxPriceInput.sendKeys("1000");
        driver.findElement(By.xpath(loc.getProperty("go_btn"))).click();
    }

    @Test(priority = 6)
    public void dynamicSearchResultsUpdateTest() throws InterruptedException {
        // Open Amazon
        driver.get("https://www.amazon.in/");

        // Create an instance of SearchPage
        SearchPage searchPage = new SearchPage();

        // Search for a product
        searchPage.searchForProduct("laptop");

        // Apply brand filter for Samsung
        searchPage.applyBrandFilter("Samsung");

        // Add assertions or further verifications if needed
        // For example:
        // Assert.assertTrue(...);
    }

    @Test(priority = 7)
    public void verifyNoMatchingResultsMessage() throws InterruptedException {
        // Open Amazon
        driver.get(prop.getProperty("testurl"));

        // Instantiate the SearchPage object
        SearchPage searchPage = new SearchPage();

        // Enter search query
        searchPage.searchForProduct("asdfgh");

        // Check if the message indicating no matching results is displayed
        boolean isNoResultsMessageDisplayed = searchPage.isNoResultsMessageDisplayed();
        Assert.assertTrue(isNoResultsMessageDisplayed, "Message indicating no matching results should be displayed.");
    }

    @Test(priority = 8)
    public void refineSearchWithFiltersTest() throws InterruptedException {
        // Open Amazon
        driver.get("https://www.amazon.in/");

        // Instantiate SearchPage object
        SearchPage searchPage = new SearchPage();

        // Perform a search
        searchPage.searchForProduct("laptop");

        // Apply initial filters
        searchPage.applyPriceFilter("500", "1000");

        // Wait for search results to update
        Thread.sleep(5000);

        // Apply additional filters
        searchPage.applyBrandFilter("Brand");

        // Click the go button to apply the additional filters
        searchPage.clickGoButton();

        // Wait for search results to update
        Thread.sleep(5000);

        // Verify if the search results are updated to reflect the refined search criteria
        // Add your verification logic here
    }

    @Test(priority = 9)
    public void resetSearchAndFiltersTest() throws InterruptedException {
        // Instantiate the SearchPage object
        SearchPage searchPage = new SearchPage();

        // Open Amazon
        driver.get("https://www.amazon.in/");

        // Perform a search
        searchPage.searchForProduct("laptop");

        // Apply filters like price range and brand
        searchPage.applyPriceFilter("500", "1000");
        searchPage.applyBrandFilter("Samsung");

        // Reset search and filters
        searchPage.resetSearchAndFilters();

        // Wait for the search results to reset
        Thread.sleep(5000); // Adjust this wait time as needed
    }

    @Test(priority = 10)
    public void seamlessIntegrationTest() throws InterruptedException {
        // Open Amazon
        driver.get(prop.getProperty("testurl"));

        // Instantiate the SearchPage object
        SearchPage searchPage = new SearchPage();

        // Search for a product
        searchPage.searchForProduct("laptop");

        // Apply price filter within the specified range
        searchPage.applyPriceFilter("500", "1000");

        // Apply brand filter for a specific brand
        searchPage.applyBrandFilter("Brand");

        // Click the go button to apply filters
        searchPage.clickGoButton();

        // Wait for search results to update
        Thread.sleep(5000); // Adjust this wait time as needed

        // Verify if the search results are dynamically updated based on the applied filters
        // For example:
        // Assert.assertTrue(searchPage.verifySearchResultsUpdatedProperly(), "Search results are not updated properly");
    }

    @Test(priority = 11)
    public void verifyNumberOfResultsPerPage() throws InterruptedException {
        // Access Amazon
        driver.get(prop.getProperty("testurl"));
        
        // Instantiate the SearchPage object
        SearchPage searchPage = new SearchPage();
        
        // Perform search for any product
        searchPage.searchForProduct("laptop");
        
        // Wait for search results to load
        Thread.sleep(5000);
        
        // Get the number of search results displayed
        int numberOfResults = searchPage.getNumberOfSearchResults();
        
        // Verify if the number of results per page is as expected
        int expectedNumberOfResultsPerPage = 20; // Assuming the expected number of results per page is 20
        Assert.assertEquals(numberOfResults, expectedNumberOfResultsPerPage, 
                            "Number of search results per page is not as expected");
    }

    @Test(priority = 12)
    public void verifyNavigationToNextPage() throws InterruptedException {
        // Access Amazon
        driver.get(prop.getProperty("testurl"));
        
        // Instantiate the SearchPage object
        SearchPage searchPage = new SearchPage();
        
        // Perform a search
        searchPage.searchForProduct("laptop");
        
        // Wait for search results to load
        Thread.sleep(5000);
        
        // Navigate to the next page
        searchPage.goToNextPage();
        
        // Verify navigation to the next page of search results
        String currentPageUrl = searchPage.getCurrentPageUrl();
        Assert.assertTrue(currentPageUrl.contains("/page=2"), "Navigation to the next page failed");
    }
    @Test(priority = 13)
    public void verifyEmptySearchError() throws InterruptedException {
        // Open Amazon
        driver.get("https://www.amazon.in/");

        // Instantiate the SearchPage object
        SearchPage searchPage = new SearchPage();

        // Perform a search with empty query
        searchPage.searchForProduct("");

        // Check if the error message is displayed
        boolean isErrorDisplayed = searchPage.isNoResultsMessageDisplayed();
        Assert.assertTrue(isErrorDisplayed, "Error message for empty search query is not displayed.");
    }

}
