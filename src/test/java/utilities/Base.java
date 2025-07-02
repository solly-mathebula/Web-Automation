package utilities;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;

public class Base {

    protected Playwright playwright = null;
    protected Browser browser = null;
    protected BrowserContext context = null;
    protected static ThreadLocal<Page> page = new ThreadLocal<>();


    public void initializeBrowser() {
        try {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)
                    .setSlowMo(50) // slow down for visibility, remove if unnecessary
            );
            context = browser.newContext();
            page.set(context.newPage());
        } catch (Exception e) {
            System.err.println("Failed to initialize browser: " + e.getMessage());
            Assert.fail("Browser initialization failed", e);
        }
    }


    public static Page getPage() {
        Page p = page.get();
        if (p == null) {
            throw new IllegalStateException("Page has not been initialized. Call initializeBrowser() first.");
        }
        return p;
    }


    public void navigateToPage(String url) {
        try {
            getPage().navigate(url);
        } catch (Exception e) {
            System.err.println("Failed to navigate to page: " + url);
            Assert.fail("Navigation failed to: " + url, e);
        }
    }


    public void clickByText(String text) {
        try {
            Locator locator = getPage().getByText(text).first();
            waitUntilVisibleAndEnabled(locator);
            locator.click(new Locator.ClickOptions().setForce(true));
        } catch (Exception e) {
            System.err.println("Failed to click element by text '" + text + "': " + e.getMessage());
            Assert.fail("Failed to click by text: " + text, e);
        }
    }


    public void clickBySelector(String selector) {
        try {
            Locator locator = getPage().locator(selector).first();
            waitUntilVisibleAndEnabled(locator);
            locator.click(new Locator.ClickOptions().setForce(true));
        } catch (Exception e) {
            System.err.println("Failed to click element by selector '" + selector + "': " + e.getMessage());
            Assert.fail("Failed to click by selector: " + selector, e);
        }
    }


    public void inputTextBySelector(String selector, String text) {
        try {
            Locator locator = getPage().locator(selector).first();
            waitUntilVisibleAndEnabled(locator);
            locator.fill(text);
        } catch (Exception e) {
            System.err.println("Failed to input text by selector '" + selector + "': " + e.getMessage());
            Assert.fail("Failed to input text by selector: " + selector, e);
        }
    }


    private void waitUntilVisibleAndEnabled(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(30000)
            );
        } catch (Exception e) {
            throw new RuntimeException("Element not visible after waiting: " + locator.toString(), e);
        }
    }


    public void closeBrowser() {
        try {
            if (page.get() != null) {
                page.get().close();
                page.remove();
            }
            if (context != null) {
                context.close();
            }
            if (browser != null) {
                browser.close();
            }
            if (playwright != null) {
                playwright.close();
            }
        } catch (Exception e) {
            System.err.println("Error closing browser resources: " + e.getMessage());
        }
    }
}
