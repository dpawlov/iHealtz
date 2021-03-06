package com.example.ihealtzstore.model.binding;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationBindingModel {

    @NotNull
    @Size(min=4, max=20)
    private String username;

    @NotNull
    @Size(min=4, max=20)
    private String fullName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min=4, max=20)
    private String password;

    @NotNull
    @Size(min=4, max=20)
    private String confirmPassword;

}
