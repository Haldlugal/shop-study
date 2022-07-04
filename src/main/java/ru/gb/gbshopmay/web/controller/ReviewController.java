package ru.gb.gbshopmay.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbshopmay.entity.Product;
import ru.gb.gbshopmay.entity.Review;
import ru.gb.gbshopmay.entity.security.AccountUser;
import ru.gb.gbshopmay.service.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final UserService userService;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final RecaptchaService recaptchaService;
    private final ProductController productController;

    @PostMapping
    public String saveReview(
            @RequestParam(name = "comment") String comment,
            @RequestParam(name = "id") Long id,
            Principal principal,
            HttpServletRequest request,
            Model model
    ) {
        AccountUser accountUser = userService.findByUsername(principal.getName());
        Optional<Product> product = productService.findProductById(id);
        String captchaResponse = request.getParameter("g-recaptcha-response");
        String ip = request.getRemoteAddr();
        String recaptchaCheck = recaptchaService.verifyRecaptcha(ip, captchaResponse);
        ArrayList<String> errors = new ArrayList<>(recaptchaService.reviewValid(comment));
        System.out.println("check " + recaptchaCheck);
        if (!Objects.equals(recaptchaCheck, "")) {
            errors.add("Captcha not verified");
        }
        if (errors.size() == 0 && product.isPresent()) {
            Review review = Review.builder()
                    .approved(false)
                    .accountUser(accountUser)
                    .createdDate(LocalDateTime.now())
                    .text(comment)
                    .product(product.get())
                    .build();
            reviewService.save(review);
        }

        return productController.showInfo(model, id, errors);
    }
}
