package com.mariavasquez.franchis.system.shared.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    DATABASE_ERROR("TRAPP_ERR_1",500,"Database error"),
    FRANCHISE_NOT_FOUND("TRAPP_ERR_2",404,"Franchise not found"),
    FRANCHISE_EXIST("TRAPP_ERR_3",409,"Franchise '{0}' already exist"),
    BRANCH_EXIST("TRAPP_ERR_3",409,"BRANCH '{0}' already exist"),
    PRODUCT_NOT_FOUND("TRAPP_ERR_4",404,"Product not found"),
    BRANCH_NOT_FOUND("TRAPP_ERR_5",404,"Branch not found"),
    TRANSACTION_SUCCESS("TRAPP_SUCC_1",200, "Ok"),
    TRANSACTION_CREATED("TRAPP_SUCC_1",201, "Created"),
    UNEXPECTED_ERROR("TRAPP_ERR_6",500,"Unexpected error");

    private final String code;
    private final int httpStatus;
    private final String message;

    public String formatMessage(Object... args){return String.format(this.message, args);}

}
