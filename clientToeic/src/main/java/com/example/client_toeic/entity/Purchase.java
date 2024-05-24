package com.example.client_toeic.entity;

import com.example.client_toeic.utils.EntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "purchase")
public class Purchase extends EntityBase {

    private LocalDateTime time;
    private Integer status;
    private String paymentMethod;
    private Integer userId;
    private Integer examId;
}
