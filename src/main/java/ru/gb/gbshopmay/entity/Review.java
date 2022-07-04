package ru.gb.gbshopmay.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import ru.gb.gbshopmay.entity.common.BaseEntity;
import ru.gb.gbshopmay.entity.security.AccountUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review")
public class Review extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountUser accountUser;

    @Column(name = "text")
    private String text;

    @Column(name="approved")
    private boolean approved;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
