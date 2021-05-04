package com.ozz.sharding.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TOrder implements BaseObject {
  private Long userId;
  private Long orderId;
}
