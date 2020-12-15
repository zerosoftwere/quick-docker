package net.xerosoft.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.smallrye.common.constraint.NotNull;
import lombok.Data;

@Data
public class ContactRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String phone;
    
    @Email
    private String email;
}
