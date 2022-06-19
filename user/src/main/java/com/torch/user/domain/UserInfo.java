package com.torch.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_info")
public class UserInfo  implements Serializable {

  @MongoId
  private String  id;
  private String ban;
  private String username;
  private String password;
  private String documentation;
  private String email;

}
