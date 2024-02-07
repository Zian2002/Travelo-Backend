package com.zian.travelo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "tour_info")
public class TourInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 100)
    private String description;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "itinerary", joinColumns = @JoinColumn(name = "tour_info_id"))
    @MapKeyColumn(name = "schedule")
    @Column(name = "detail",nullable = false)
    private Map<String, String> itinerary;
    private LocalDate createdAt;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;

}
