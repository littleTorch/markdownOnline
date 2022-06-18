package com.torch.feignclient.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo  implements Serializable {

  private String  id;
  private String ban;
  private String username;
  private String password;
  private String documentation;
  private String email;

}
