package com.torch.feignclient.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin  implements Serializable {

  private String  id;
  private String username;
  private String password;
  private String permission;


}
