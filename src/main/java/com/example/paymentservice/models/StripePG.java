package com.example.paymentservice.models;

import com.stripe.Stripe;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.PriceCreateParams.ProductData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePG implements IPaymentGateway{
    @Value("${stripe.api.key}")
    String apiKey;

    @Override
    public String getPaymentLink(Long orderId){

        Stripe.apiKey= apiKey;

        ProductData productData =
                ProductData.builder()
                        .setName("testproduct")
                        .build();

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(1000L)
                        .setProductData(productData)
                        .build();

        try{
            Price price = Price.create(priceCreateParams);

            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .setAfterCompletion(
                                    PaymentLinkCreateParams.AfterCompletion.builder()
                                            .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                            .setRedirect(
                                                    PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                            .setUrl("https://google.com")
                                                            .build()
                                            )
                                            .build()
                            )
                            .build();

            PaymentLink paymentLink = PaymentLink.create(params);
            return String.valueOf(paymentLink.getUrl());
        }
        catch(Exception e){
            System.out.println(e);
        }


        return null;
    }
}
