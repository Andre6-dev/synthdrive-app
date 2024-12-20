-- liquibase formatted sql
-- changeset devandre:add_users_data
INSERT INTO users (public_id, first_name, last_name, email, password_hash, role_id, address, avatar_url, is_enabled)
VALUES
    (gen_random_uuid(), 'John', 'Doe', 'john.doe@example.com', 'hashed_password1', 1, '123 Main St, City', 'https://example.com/avatar1.png', TRUE),
    (gen_random_uuid(), 'Jane', 'Smith', 'jane.smith@example.com', 'hashed_password2', 2, '456 Elm St, City', 'https://example.com/avatar2.png', TRUE),
    (gen_random_uuid(), 'Alice', 'Johnson', 'alice.johnson@example.com', 'hashed_password3', 3, NULL, 'https://example.com/avatar3.png', TRUE),
    (gen_random_uuid(), 'Bob', 'Brown', 'bob.brown@example.com', 'hashed_password4', 2, '789 Pine St, City', NULL, FALSE),
    (gen_random_uuid(), 'Eve', 'White', 'eve.white@example.com', 'hashed_password5', 1, '101 Maple St, City', NULL, TRUE);


-- liquibase formatted sql
-- changeset devandre:add_auth_providers_data
INSERT INTO auth_providers (user_id, type)
VALUES (1, 'CREDENTIALS'),
       (2, 'GOOGLE')

-- liquibase formatted sql
-- changeset devandre:add_admins_data
INSERT INTO admins (display_name, user_id)
VALUES ('Admin John', 1),
       ('Admin Sophia', 5);

-- liquibase formatted sql
-- changeset devandre:add_customers_data
INSERT INTO customers (display_name, user_id)
VALUES ('Customer Jane', 2),
       ('Customer Bob', 4),
       ('Customer Alice', 3);

-- liquibase formatted sql
-- changeset devandre:add_companies_data
INSERT INTO companies (display_name, address, description)
VALUES ('ABC Parking Ltd.', '123 Parking St, City', 'Leading parking service provider.'),
       ('XYZ Garages Co.', '456 Garage Lane, City', 'Premium garage management.'),
       ('PQR Valet Services', '789 Valet Blvd, City', 'Trusted valet service provider.');

-- liquibase formatted sql
-- changeset devandre:add_managers_data
INSERT INTO managers (display_name, user_id, company_id)
VALUES ('Manager John', 1, 1),
       ('Manager Eve', 5, 2);

-- liquibase formatted sql
-- changeset devandre:add_valets_data
INSERT INTO valets (user_id, display_name, license_id, company_id)
VALUES (3, 'Valet Alice', 'LIC12345', 1),
       (4, 'Valet Bob', 'LIC67890', 2);

-- liquibase formatted sql
-- changeset devandre:add_parking_data
INSERT INTO garages (display_name, description, image, company_id)
VALUES ('Garage A1', 'Underground parking', 'image1.jpg', 1),
       ('Garage B2', 'Open space parking', 'image3.jpg', 2);

-- liquibase formatted sql
-- changeset devandre:add_address_data
INSERT INTO address (address, latitude, longitude, garage_id)
VALUES ('123 Parking St, City', 37.774929, -122.419416, 1),
       ('456 Garage Lane, City', 40.712776, -74.005974, 2);

-- liquibase formatted sql
-- changeset devandre:add_slots_data
INSERT INTO slots (display_name, price_per_hour, length, width, height, type, garage_id)
VALUES ('Slot A1', 10.00, 500, 200, 150, 'CAR', 1),
       ('Slot A2', 12.00, 550, 250, 160, 'CAR', 1),
       ('Slot B1', 8.00, 450, 180, 140, 'BIKE', 2);

-- liquibase formatted sql
INSERT INTO bookings (price_per_hour, total_price, start_time, end_time, vehicle_number, phone_number, passcode, status,
                      slot_id, customer_id)
VALUES (10.00, 20.00, '2024-06-17 08:00:00', '2024-06-17 10:00:00', 'XYZ123', '1234567890', 'PASS123', 'BOOKED', 1, 1),
       (12.00, 36.00, '2024-06-17 09:00:00', '2024-06-17 12:00:00', 'ABC456', '9876543210', 'PASS456', 'BOOKED', 2, 2);
