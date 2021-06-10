package ccf.project.service;

import ccf.project.domain.ProductModel;
import java.util.List;

public interface EmailService {

    /**
     * Send email notification to all Admin users for the given products
     *
     * @param productModels the products that are low in stock
     * @return the recieved product model list
     */
    List<ProductModel> sendEmailsForLowerQuantity(List<ProductModel> productModels);

    /**
     * Send notification to all Salesman users for the given product
     *
     * @param productModel the product to be included in the email
     * @return the passed product
     */
    ProductModel sendEmailsForProductUpdate(ProductModel productModel);
}
