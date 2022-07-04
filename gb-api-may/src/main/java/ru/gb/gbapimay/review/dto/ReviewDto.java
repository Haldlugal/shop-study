package ru.gb.gbapimay.review.dto;

import lombok.*;
import ru.gb.gbapimay.product.dto.ProductDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private String text;
    private String userName;
    private ProductDto product;
}
