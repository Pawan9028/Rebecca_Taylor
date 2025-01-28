package com.rt.testUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

public class TestUtils {
	
	static ExtentReports extent;

	// Extend report code to generate good test report
	public static ExtentReports getReporterObject() {
		String path = System.getProperty("user.dir") + "/src/test/resources/Reports";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Pawan Rewatkar");
		reporter.config().setDocumentTitle("Automation Test Result");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Arista", "Rebecca Taylor");
		// extent.setSystemInfo("Arista", "Shopify");
		return extent;

	}

	// Screenshot code for fail test cases
	public String getScreenShotPath(String testCaseName, ChromeDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationField = System.getProperty("user.dir") + "/src/test/resources/Screenshot" + testCaseName
				+ ".png";
		FileUtils.copyFile(source, new File(destinationField));
		return destinationField;
	}

	// Send an Email code by using sendGrid
	public void sendReportToEmail() throws IOException {
		String sendGridApiKey = "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"; // Replace with your SendGrid API Key
		String fromEmail = "pavan@aristasystems.in";
		String toEmail = "pawanrewatkar28@gmail.com"; //send Email to one Mail ID
		List<String> recipients = List.of(
	            "pawanrewatkar28@gmail.com",
	            "shrikant@aristasystems.in",
	              "parmanand@aristasystems.in"
	           
	        );
		
		String subject = "Automation Test Report";
		String contentText = "Please find the attached Automation test report for Rebecca Taylor.";
		String attachmentPath = System.getProperty("user.dir") + "/src/test/resources/Reports/index.html";

		Email from = new Email(fromEmail);
		//Email to = new Email(toEmail);//send Email to one Mail ID
		Content content = new Content("text/plain", contentText);
		Mail mail = new Mail();
		mail.setFrom(from);
        mail.setSubject(subject);
        mail.addContent(content);

		 // Add multiple recipients
        Personalization personalization = new Personalization();
        for (String emailAddress : recipients) {
            Email to = new Email(emailAddress);
            personalization.addTo(to);
        }
        mail.addPersonalization(personalization);
		
		//Add attachment
		Attachments attachments = new Attachments();
		byte[] fileContent;
		fileContent = Files.readAllBytes(Paths.get(attachmentPath));
		String encodedContent = Base64.getEncoder().encodeToString(fileContent);
		attachments.setContent(encodedContent);
		attachments.setType("text/html");
		attachments.setFilename("ExtentReport.html");
		attachments.setDisposition("attachment");
		mail.addAttachments(attachments);

		SendGrid sg = new SendGrid(sendGridApiKey);
		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}
}

//	//Send report to email code
//	public void sendReportToEmail() {
//        try {
//            // Create the attachment
//            EmailAttachment attachment = new EmailAttachment();
//            attachment.setPath(System.getProperty("user.dir") + "/src/test/resources/Reports/index.html");
//            attachment.setDisposition(EmailAttachment.ATTACHMENT);
//            attachment.setDescription("Test Report");
//            attachment.setName("ExtentReport.html");
//
//            // Create the email message
//            MultiPartEmail email = new MultiPartEmail();
//            email.setHostName("smtp.gmail.com");
//            email.setSmtpPort(587);//port
//            email.setAuthenticator(new DefaultAuthenticator("pavan@aristasystems.in", "Pawan@161094"));
//            email.setStartTLSEnabled(true);
//            email.setFrom("pavan@aristasystems.in", "Pawan Rewatkar");
//            email.addTo("pawanrewatkar28@gmail.com");
//            email.setSubject("Automation Test Report");
//            email.setMsg("Please find the attached Automation test report for Rebecca Taylor.");
//
//            // Attach the report
//            email.attach(attachment);
//            
//            // Send the email
//            email.send();
//            System.out.println("Email sent successfully!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
