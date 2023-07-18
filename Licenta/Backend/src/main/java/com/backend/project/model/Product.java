package com.backend.project.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Document(collection = "products")
public class Product {

    @Transient
    public static final String sequenceName = "product_sequence";

    @Id
    int id;
    int productId;
    String productName;
    String productDetails;
    String category;
    String availability;
    String latitude;
    String longitude;
    String location;
    int quantity;
}
