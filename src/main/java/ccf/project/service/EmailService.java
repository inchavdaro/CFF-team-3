package ccf.project.service;

import ccf.project.domain.ProductModel;

import java.util.List;

public interface EmailService {

    void sendEmailsForLowerQuantity(List<ProductModel> productModels);

    void sendEmailsForProductUpdate(ProductModel productModel);
}
