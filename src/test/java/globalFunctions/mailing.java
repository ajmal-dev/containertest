package test.java.globalFunctions;

import org.apache.commons.mail.*;

import java.io.IOException;

public class mailing

{
    propertiesReader propfile=new propertiesReader();

    public void sendMail() throws EmailException, IOException {
        MultiPartEmail email = new MultiPartEmail();
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("reports/sampleReport.html");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Sample test automation reports");
        attachment.setName("AutomataionReport.html");
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("ajmalbabu77@gmail.com", "Ajshanej@2020"));
        email.setSSLOnConnect(true);
        email.setFrom("ajmalbabu77@gmail.com");
        email.setSubject("Test Automation report");
        email.setMsg("I have attached the Automation mail");
        email.addTo(propfile.propertiesRead("ReportsRecipeints"));
        email.attach(attachment);
        email.send();
    }
}
