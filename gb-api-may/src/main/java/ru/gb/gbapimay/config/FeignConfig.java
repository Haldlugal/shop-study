package ru.gb.gbapimay.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.gb.gbapimay.category.api.CategoryGateway;
import ru.gb.gbapimay.manufacturer.api.ManufacturerGateway;
import ru.gb.gbapimay.product.api.ProductGateway;
import ru.gb.gbapimay.security.UserGateway;

@Configuration
@EnableConfigurationProperties(GbApiProperties.class)
@RequiredArgsConstructor
@Import(value = {FeignClientFactory.class})
public class FeignConfig {

    private final GbApiProperties gbApiProperties;
    private final FeignClientFactory feignClientFactory;

    @Bean
    public ManufacturerGateway manufacturerGateway() {
        return feignClientFactory.newFeignGateway(ManufacturerGateway.class, gbApiProperties.getEndpoint().getManufacturerUrl());
    }

    @Bean
    public CategoryGateway categoryGateway() {
        return feignClientFactory.newFeignGateway(CategoryGateway.class, gbApiProperties.getEndpoint().getCategoryUrl());
    }

    @Bean
    public CategoryGateway categoryFrontGateway() {
        return feignClientFactory.newFeignGateway(CategoryGateway.class, gbApiProperties.getEndpoint().getCategoryUrl());
    }

    @Bean
    public ProductGateway productGateway() {
        return feignClientFactory.newFeignGateway(ProductGateway.class, gbApiProperties.getEndpoint().getProductUrl());
    }

    @Bean
    public UserGateway userGateway() {
        return feignClientFactory.newFeignGateway(UserGateway.class, gbApiProperties.getEndpoint().getUserUrl());
    }
}
