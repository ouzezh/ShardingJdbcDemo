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
  public TOrder(){
    super();
  }
  public TOrder(Long userId, Long orderId){
    super();
    this.userId = userId;
    this.orderId = orderId;
  }
}
