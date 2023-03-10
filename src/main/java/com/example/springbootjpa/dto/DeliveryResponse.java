package com.example.springbootjpa.dto;

import com.example.springbootjpa.config.DeliveryStatus;
import com.example.springbootjpa.domain.Order;
import com.example.springbootjpa.domain.valuetype.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponse {

    Long deliveryId;
    Address address;
    DeliveryStatus status;

}
