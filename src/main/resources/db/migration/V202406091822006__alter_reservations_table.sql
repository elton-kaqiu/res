-- Step 1: Drop the foreign key constraint
ALTER TABLE reservations DROP CONSTRAINT fk_reservations_users_user_id;

-- Step 2: Drop the user_id column
ALTER TABLE reservations DROP COLUMN user_id;

-- Step 3: Add new columns
ALTER TABLE reservations
    ADD COLUMN first_name VARCHAR(50),
    ADD COLUMN last_name VARCHAR(50),
    ADD COLUMN email VARCHAR(255),
    ADD COLUMN phone_number VARCHAR(30);
