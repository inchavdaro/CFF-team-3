package ccf.project.service.tasks;

import ccf.project.service.EmailService;
import ccf.project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationTask {

    private static final Logger logger = LoggerFactory.getLogger(NotificationTask.class);

    private static final Integer minStock = 20;

    private final ProductService productService;

    private final EmailService emailService;

    public NotificationTask(ProductService productService, EmailService emailService) {
        this.productService = productService;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 20 * * ?")
    public void stockCheckTask() {

    }


}
