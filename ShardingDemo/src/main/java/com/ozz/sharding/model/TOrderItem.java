package com.ozz.sharding.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TOrderItem extends TOrder {
  private Long orderItemId;
  public TOrderItem(){
    super();
  }
  public TOrderItem(Long userId, Long orderId, Long orderItemId){
    super(userId, orderId);
    this.orderItemId = orderItemId;
  }
}
