package com.Cat.sCorner.Cat.sCorner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseDTO {
     private Long id;
     @NotEmpty
     @NotBlank
    private String name;
    private String description;
    private String schedule;
}
