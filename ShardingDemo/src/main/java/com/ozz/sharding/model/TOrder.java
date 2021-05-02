package com.ozz.sharding.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TOrder implements BaseObject {
  private Integer userId;
  private Integer orderId;
}
