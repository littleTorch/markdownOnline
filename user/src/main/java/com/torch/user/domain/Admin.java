package com.torch.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "admin")
public class Admin  implements Serializable {

  @MongoId
  private ObjectId id;
  private String username;
  private String password;
  private String permission;


}
