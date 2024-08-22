CREATE DATABASE IF NOT EXISTS `wallet-sandbox`;
USE `wallet-sandbox`;

create table if not exists `account`
(
    end_date         date           null,
    end_date_balance decimal(38, 2) null,
    id               bigint         not null
        primary key,
    email            varchar(255)   null,
    full_name        varchar(255)   null,
    mobile           varchar(255)   null,
    constraint UK_hb8ofqht4k0kn4rnr29ayem5w
        unique (mobile),
    constraint UK_q0uja26qgu1atulenwup9rxyr
        unique (email)
);

create table if not exists `balance_change`
(
    end_date              date           null,
    end_date_balance      decimal(38, 2) null,
    transaction_in_total  decimal(38, 2) null,
    transaction_out_total decimal(38, 2) null,
    account_id            bigint         null,
    id                    bigint auto_increment
        primary key,
    transaction_in_count  bigint         null,
    transaction_out_count bigint         null
);

create table if not exists `cashin`
(
    create_time              date           null,
    provider_callback_time   date           null,
    settle_amount            decimal(38, 2) null,
    cashin_code              bigint         null,
    id                       bigint auto_increment
        primary key,
    order_id                 bigint         null,
    provider_id              bigint         null,
    provider_receipt         bigint         null,
    provider_callback_amount varchar(255)   null,
    provider_request_time    varchar(255)   null,
    provider_service_id      varchar(255)   null,
    settle_time              varchar(255)   null,
    transaction_id           varchar(255)   null
);

create table if not exists `cashout`
(
    amount                      decimal(38, 2) null,
    performed_time              date           null,
    ver02cashout_performed_time date           null,
    bank_id                     bigint         null,
    id                          bigint auto_increment
        primary key,
    receiver                    bigint         null,
    sender                      bigint         null,
    type                        bigint         null,
    ver02cashout_id             bigint         null,
    bank_account_name           varchar(255)   null,
    bank_code                   varchar(255)   null,
    bank_number                 varchar(255)   null
);

create table if not exists `fee_config`
(
    receiver_fee_fix_amount decimal(38, 2) null,
    receiver_fee_percentage decimal(38, 2) null,
    sender_fee_fix_amount   decimal(38, 2) null,
    sender_fee_percentage   decimal(38, 2) null,
    account_id              bigint         null,
    id                      bigint auto_increment
        primary key,
    method_id               bigint         null,
    transaction_type        bigint         null
);

create table if not exists `payment_method`
(
    id                          bigint auto_increment
        primary key,
    bank_code                   varchar(255) null,
    merchant_id                 varchar(255) null,
    method_branch               varchar(255) null,
    method_type                 varchar(255) null,
    payment_method_bank_code    varchar(255) null,
    payment_method_code         varchar(255) null,
    provider_fee_fix            varchar(255) null,
    provider_max_amount         varchar(255) null,
    provider_min_amount         varchar(255) null,
    provider_service_code       varchar(255) null,
    provider_service_id         varchar(255) null,
    provider_service_percentage varchar(255) null,
    user_id                     varchar(255) null
);

create table if not exists `provider_request`
(
    id                   bigint auto_increment
        primary key,
    bank_response_amount varchar(255) null,
    cashin_id            varchar(255) null,
    provider_id          varchar(255) null,
    provider_service_id  varchar(255) null,
    request_context      varchar(255) null,
    request_time         varchar(255) null,
    request_type         varchar(255) null,
    response_content     varchar(255) null,
    response_time        varchar(255) null,
    settle_amount        varchar(255) null,
    status               varchar(255) null
);

create table if not exists `transaction`
(
    amount                   decimal(38, 2) null,
    create_time              date           null,
    settle_time              date           null,
    cashin_id                bigint         null,
    config_merchant_fee_id   bigint         null,
    id                       bigint auto_increment
        primary key,
    order_id                 bigint         null,
    receiver                 bigint         null,
    sender                   bigint         null,
    type                     bigint         null,
    paymemt_method_code      varchar(255)   null,
    payment_method_bank_code varchar(255)   null,
    status                   varchar(255)   null
);

