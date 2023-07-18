package com.backend.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {

    String productName;
    String productDetails;
    String category;
    String availability;
    String latitude;
    String longitude;
    String location;
    int quantity;


}
