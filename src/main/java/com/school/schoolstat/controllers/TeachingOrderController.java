package com.school.schoolstat.controllers;

import com.school.schoolstat.models.dto.responses.TeachingOrderResponseDto;
import com.school.schoolstat.models.enums.TeachingOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("teaching-order")
public class TeachingOrderController {

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        List<TeachingOrderResponseDto> teachingOrders = new ArrayList<>();
        teachingOrders.add(new TeachingOrderResponseDto(TeachingOrder.PUBLIC.getValue(), TeachingOrder.PUBLIC));
        teachingOrders.add(new TeachingOrderResponseDto(TeachingOrder.PRIVE_LAIC.getValue(), TeachingOrder.PRIVE_LAIC));
        teachingOrders.add(new TeachingOrderResponseDto(TeachingOrder.PRIVE_CATHOLIQUE.getValue(), TeachingOrder.PRIVE_CATHOLIQUE));
        teachingOrders.add(new TeachingOrderResponseDto(TeachingOrder.PRIVE_FRANCO_ARABE.getValue(), TeachingOrder.PRIVE_FRANCO_ARABE));
        teachingOrders.add(new TeachingOrderResponseDto(TeachingOrder.PRIVE_PROTESTANT.getValue(), TeachingOrder.PRIVE_PROTESTANT));

        return ResponseEntity.ok(teachingOrders);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count(){

        int total = TeachingOrder.LENGTH.getValue();
        return ResponseEntity.ok(total);
    }
}
