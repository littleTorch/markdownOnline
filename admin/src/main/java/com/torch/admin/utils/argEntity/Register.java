package com.torch.admin.utils.argEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Register {

    private String username;

    private String password;

    private String email;

    private String code;
}
