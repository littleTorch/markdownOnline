package com.torch.feignclient.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanInfo  implements Serializable {

  private String id;
  private String reportContent;
  private String whistleblower;
  private String whistleblowered;

}
