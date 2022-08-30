create table if not exists "transactions"
(
    id              UUID            DEFAULT random_uuid() PRIMARY KEY,
    amount          DECIMAL(15,4)   NOT NULL,
    reference       VARCHAR(255)    NOT NULL,
    bank_slogan     VARCHAR(255),
    receiving_user  VARCHAR(255)    NOT NULL,
    `timestamp`     TIMESTAMP       NOT NULL,
    version         INT
);