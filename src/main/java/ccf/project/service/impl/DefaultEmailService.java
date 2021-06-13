package ccf.project.service.impl;

import ccf.project.domain.ProductModel;
import ccf.project.service.EmailService;
import ccf.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultEmailService implements EmailService {

    private final JavaMailSender emailSender;

    private final UserService userService;

    public DefaultEmailService(JavaMailSender emailSender, UserService userService) {
        this.emailSender = emailSender;
        this.userService = userService;
    }

    @Override
    public List<ProductModel> sendEmailsForLowerQuantity(List<ProductModel> productModels) {
        StringBuilder message = new StringBuilder();

        for (ProductModel productModel : productModels) {
            message.append(String.format("Stock for product %s is low, currently %n left" + System.lineSeparator()
                    , productModel.getModel(), productModel.getQuantity()));
        }
        for (String adminEmail : userService.getAllAdminEmails()) {
            sendSimpleMessage(adminEmail, "Stock reducing", message.toString());
        }

        return productModels;
    }

    @Override
    public ProductModel sendEmailsForProductUpdate(ProductModel productModel) {
        String message = String.format("Update for product %s ,new price : %n" + System.lineSeparator()
                , productModel.getModel(), productModel.getQuantity());

        for (String salesmanEmail : userService.getAllSalesmanEmails()) {
            sendSimpleMessage(salesmanEmail, "Product update", message);
        }

        return productModel;
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
