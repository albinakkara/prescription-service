
package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PrescriptionRequest {

    private List<PrescriptionItemRequest> items;

}
