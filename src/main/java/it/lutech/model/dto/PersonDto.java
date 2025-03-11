package it.lutech.model.dto;

import java.time.LocalDate;
import java.util.Date;

import io.smallrye.common.constraint.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    @Nullable
    private Long id;
    @NotBlank
    private String name;
    @PastOrPresent
    private Date birthday;
    @NotNull
    private Boolean alive;
}
