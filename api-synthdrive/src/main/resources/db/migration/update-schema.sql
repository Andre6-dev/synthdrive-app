CREATE SEQUENCE IF NOT EXISTS address_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS admin_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS booking_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS booking_timeline_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS company_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS customer_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS garage_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS manager_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS review_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS role_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS slot_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS user_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS valet_sequence START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS verification_sequence START WITH 5 INCREMENT BY 1;

CREATE TABLE address
(
    id         BIGINT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    address    VARCHAR(255),
    latitude   DECIMAL,
    longitude  DECIMAL,
    garage_id  BIGINT,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE admins
(
    id           BIGINT NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    display_name VARCHAR(255),
    user_id      BIGINT,
    CONSTRAINT pk_admins PRIMARY KEY (id)
);

CREATE TABLE auth_providers
(
    id      BIGINT NOT NULL,
    type    VARCHAR(255),
    user_id BIGINT,
    CONSTRAINT pk_auth_providers PRIMARY KEY (id)
);

CREATE TABLE booking_timelines
(
    id         BIGINT NOT NULL,
    timestamp  TIMESTAMP WITHOUT TIME ZONE,
    status     VARCHAR(255),
    booking_id BIGINT NOT NULL,
    valet_id   BIGINT,
    manager_id BIGINT,
    CONSTRAINT pk_booking_timelines PRIMARY KEY (id)
);

CREATE TABLE bookings
(
    id             BIGINT NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    price_per_hour DECIMAL,
    total_price    DECIMAL,
    start_time     TIMESTAMP WITHOUT TIME ZONE,
    end_time       TIMESTAMP WITHOUT TIME ZONE,
    vehicle_number VARCHAR(255),
    phone_number   VARCHAR(255),
    passcode       VARCHAR(255),
    status         VARCHAR(255),
    slot_id        BIGINT,
    customer_id    BIGINT,
    CONSTRAINT pk_bookings PRIMARY KEY (id)
);

CREATE TABLE companies
(
    id           BIGINT NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    display_name VARCHAR(255),
    address      VARCHAR(255),
    description  VARCHAR(255),
    CONSTRAINT pk_companies PRIMARY KEY (id)
);

CREATE TABLE customers
(
    id           BIGINT NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    display_name VARCHAR(255),
    user_id      BIGINT,
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

CREATE TABLE garages
(
    id           BIGINT NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    display_name VARCHAR(255),
    description  VARCHAR(255),
    image        VARCHAR(255),
    company_id   BIGINT,
    CONSTRAINT pk_garages PRIMARY KEY (id)
);

CREATE TABLE managers
(
    id           BIGINT NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    display_name VARCHAR(255),
    user_id      BIGINT,
    company_id   BIGINT,
    CONSTRAINT pk_managers PRIMARY KEY (id)
);

CREATE TABLE reviews
(
    id          BIGINT NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    rating      INTEGER,
    comment     VARCHAR(255),
    customer_id BIGINT NOT NULL,
    garage_id   BIGINT NOT NULL,
    CONSTRAINT pk_reviews PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id         BIGINT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE slots
(
    id             BIGINT NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    display_name   VARCHAR(255),
    price_per_hour DECIMAL,
    length         INTEGER,
    width          INTEGER,
    type           VARCHAR(255),
    garage_id      BIGINT,
    CONSTRAINT pk_slots PRIMARY KEY (id)
);

CREATE TABLE users
(
    id            BIGINT NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    public_id     UUID,
    first_name    VARCHAR(255),
    last_name     VARCHAR(255),
    email         VARCHAR(255),
    password_hash VARCHAR(255),
    address       VARCHAR(255),
    avatar_url    VARCHAR(255),
    is_enabled    BOOLEAN,
    role_id       BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE valets
(
    id           BIGINT NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    display_name VARCHAR(255),
    license_id   VARCHAR(255),
    company_id   BIGINT,
    user_id      BIGINT,
    CONSTRAINT pk_valets PRIMARY KEY (id)
);

CREATE TABLE valets_assignments
(
    booking_id       BIGINT NOT NULL,
    created_at       TIMESTAMP WITHOUT TIME ZONE,
    updated_at       TIMESTAMP WITHOUT TIME ZONE,
    pickup_latitude  DECIMAL(10, 8),
    pickup_longitude DECIMAL(11, 8),
    return_latitude  DECIMAL(10, 8),
    return_longitude DECIMAL(11, 8),
    pickup_valet_id  BIGINT,
    return_valet_id  BIGINT,
    CONSTRAINT pk_valets_assignments PRIMARY KEY (booking_id)
);

CREATE TABLE verifications
(
    id         BIGINT  NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    verified   BOOLEAN NOT NULL,
    admin_id   BIGINT  NOT NULL,
    garage_id  BIGINT  NOT NULL,
    CONSTRAINT pk_verifications PRIMARY KEY (id)
);

ALTER TABLE address
    ADD CONSTRAINT uc_address_garage UNIQUE (garage_id);

ALTER TABLE admins
    ADD CONSTRAINT uc_admins_user UNIQUE (user_id);

ALTER TABLE auth_providers
    ADD CONSTRAINT uc_auth_providers_user UNIQUE (user_id);

ALTER TABLE customers
    ADD CONSTRAINT uc_customers_user UNIQUE (user_id);

ALTER TABLE managers
    ADD CONSTRAINT uc_managers_user UNIQUE (user_id);

ALTER TABLE valets
    ADD CONSTRAINT uc_valets_user UNIQUE (user_id);

ALTER TABLE verifications
    ADD CONSTRAINT uc_verifications_garage UNIQUE (garage_id);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_GARAGE FOREIGN KEY (garage_id) REFERENCES garages (id);

ALTER TABLE admins
    ADD CONSTRAINT FK_ADMINS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE auth_providers
    ADD CONSTRAINT FK_AUTH_PROVIDERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE bookings
    ADD CONSTRAINT FK_BOOKINGS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE bookings
    ADD CONSTRAINT FK_BOOKINGS_ON_SLOT FOREIGN KEY (slot_id) REFERENCES slots (id);

ALTER TABLE booking_timelines
    ADD CONSTRAINT FK_BOOKING_TIMELINES_ON_BOOKING FOREIGN KEY (booking_id) REFERENCES bookings (id);

ALTER TABLE booking_timelines
    ADD CONSTRAINT FK_BOOKING_TIMELINES_ON_MANAGER FOREIGN KEY (manager_id) REFERENCES managers (id);

ALTER TABLE booking_timelines
    ADD CONSTRAINT FK_BOOKING_TIMELINES_ON_VALET FOREIGN KEY (valet_id) REFERENCES valets (id);

ALTER TABLE customers
    ADD CONSTRAINT FK_CUSTOMERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE garages
    ADD CONSTRAINT FK_GARAGES_ON_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id);

ALTER TABLE managers
    ADD CONSTRAINT FK_MANAGERS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id);

ALTER TABLE managers
    ADD CONSTRAINT FK_MANAGERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_GARAGE FOREIGN KEY (garage_id) REFERENCES garages (id);

ALTER TABLE slots
    ADD CONSTRAINT FK_SLOTS_ON_GARAGE FOREIGN KEY (garage_id) REFERENCES garages (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE valets_assignments
    ADD CONSTRAINT FK_VALETS_ASSIGNMENTS_ON_BOOKING FOREIGN KEY (booking_id) REFERENCES bookings (id);

ALTER TABLE valets_assignments
    ADD CONSTRAINT FK_VALETS_ASSIGNMENTS_ON_PICKUP_VALET FOREIGN KEY (pickup_valet_id) REFERENCES valets (id);

ALTER TABLE valets_assignments
    ADD CONSTRAINT FK_VALETS_ASSIGNMENTS_ON_RETURN_VALET FOREIGN KEY (return_valet_id) REFERENCES valets (id);

ALTER TABLE valets
    ADD CONSTRAINT FK_VALETS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES companies (id);

ALTER TABLE valets
    ADD CONSTRAINT FK_VALETS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE verifications
    ADD CONSTRAINT FK_VERIFICATIONS_ON_ADMIN FOREIGN KEY (admin_id) REFERENCES admins (id);

ALTER TABLE verifications
    ADD CONSTRAINT FK_VERIFICATIONS_ON_GARAGE FOREIGN KEY (garage_id) REFERENCES garages (id);