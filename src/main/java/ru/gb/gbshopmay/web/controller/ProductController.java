package ru.gb.gbshopmay.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.gb.gbapimay.product.dto.ProductDto;
import ru.gb.gbapimay.review.dto.ReviewDto;
import ru.gb.gbshopmay.service.*;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;
    private final ReviewService reviewService;

    @GetMapping("/all")
    public String getProductList(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/product-list";
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        ProductDto productDto;
        if (id != null) {
            productDto = productService.findById(id);
            ArrayList<String> images = productImageService.loadMultipleImageNames(id);
            model.addAttribute("productImages", images);
        } else {
            productDto = new ProductDto();
        }
        model.addAttribute("categoryService", categoryService);
        model.addAttribute("manufacturers", manufacturerService.findAll());

        model.addAttribute("product", productDto);

        return "product/product-form";
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('product.read')")
    public String showInfo(Model model, @PathVariable(name = "productId") Long id, ArrayList<String> errors) {
        ProductDto productDto;
        if (id != null) {
            productDto = productService.findById(id);
        } else {
            return "redirect:/product/all";
        }
        ArrayList<String> images = productImageService.loadMultipleImageNames(id);
        List<ReviewDto> reviewDtoList = reviewService.findReviewsByProductId(id);
        model.addAttribute("reviews", reviewDtoList);
        model.addAttribute("productImages", images);
        model.addAttribute("product", productDto);
        if (errors != null) {
            model.addAttribute("errors", errors);
        }
        return "product/product-info";
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('product.create', 'product.update')")
    public String saveProduct(ProductDto product, @RequestParam("file") MultipartFile file) {
        product.setManufactureDate(LocalDate.now());
        productService.save(product, file);
        return "redirect:/product/all";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('product.delete')")
    public String deleteById(@PathVariable(name = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }

    @PostMapping("/upload-multiple-files")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("id") Long id) {
        Arrays.stream(files)
                .map(file -> productService.saveImage(id, file));
        return "redirect:/product?id=" + id;
    }

    @GetMapping(value = "/images/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(productImageService.loadFileAsImage(id), "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    @GetMapping(value = "/image/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public byte[] getImageByName(@PathVariable String name) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(productImageService.loadFileAsImageByName(name), "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

}