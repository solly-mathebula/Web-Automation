package utilities;

import com.microsoft.playwright.Page;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Paths;
import java.util.Base64;

public class CommonFunctions implements ConcurrentEventListener {
    public static String captureScreenshotBase64() {
       try{

               String timestamp = LocalDateTime.now()
                       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

               String filename = timestamp + ".png";
               Path path = Paths.get("screenshots", filename);

               Files.createDirectories(path.getParent());


               Base.getPage().screenshot(new Page.ScreenshotOptions()
                       .setPath(path)
                       .setFullPage(true));

               byte[] imageBytes = Files.readAllBytes(path);
               return Base64.getEncoder().encodeToString(imageBytes);

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        try{
            publisher.registerHandlerFor(TestStepFinished.class, this::onStepFinished);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void onStepFinished(TestStepFinished event) {
        try{
            if (event.getTestStep() instanceof PickleStepTestStep) {
                PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
                String keyword = step.getStep().getKeyword();
                String text = step.getStep().getText();
                Result result = event.getResult();

                if (result.getStatus() == Status.PASSED) {
                    ExtentTestManager.getTest().pass(keyword + text);
                } else if (result.getStatus() == Status.FAILED) {
                    ExtentTestManager.getTest().fail(keyword + text);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String captureScreenshotToFile(Page page, String testName) {
        String path = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        return path;
    }
}
