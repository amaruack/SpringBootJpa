package com.example.springbootjpa.domain;

import com.example.springbootjpa.config.OrderStatus;

public interface NestedProjectionOrderStateOnly {

    OrderStatus getOrderStatus();
    MemberInfo getTeam();

    interface MemberInfo {
        String getName();
    }

}
