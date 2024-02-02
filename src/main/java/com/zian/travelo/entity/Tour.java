package com.zian.travelo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createdAt;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private Integer stock;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "tour_info_id")
    private TourInfo tourInfo;

    public void increaseStock(int quantity) {
        stock += quantity;
    }

    public void decreaseStock(int quantity) {
        stock -= quantity;
    }





}
