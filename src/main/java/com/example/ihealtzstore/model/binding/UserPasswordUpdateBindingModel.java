package com.example.ihealtzstore.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserPasswordUpdateBindingModel {
    private Long id;

    @NotNull
    @Size(min=4, max=20)
    private String oldPassword;

    @NotNull
    @Size(min=4, max=20)
    private String newPassword;

    @NotNull
    @Size(min=4, max=20)
    private String confirmNewPassword;

}
