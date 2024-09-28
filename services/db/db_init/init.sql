
-- -- can split it to separete table for users_auth, which would just hold passwords with user email, and corresponding user_id, with PK=email
-- CREATE TABLE users (
--     user_id SERIAL PRIMARY KEY,
--     full_name VARCHAR(255) NOT NULL,
--     email VARCHAR(255) UNIQUE NOT NULL,
--     password_hash VARCHAR(255) NOT NULL,
--     user_type VARCHAR(10) CHECK (user_type IN ('Employee', 'Recruiter')),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- CREATE TABLE jobs (
--     job_id SERIAL PRIMARY KEY,
--     user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
--     title VARCHAR(255) NOT NULL,
--     description TEXT NOT NULL,
--     location VARCHAR(255),
--     employment_type VARCHAR(50) CHECK (employment_type IN ('Full-time', 'Part-time', 'Contract', 'Temporary', 'Internship')),
--     salary_range VARCHAR(50),
--     closing_date DATE,
--     application_url VARCHAR(255),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- -- it's one table for any kind of job-user relations, alternatively we can split it to specific tables (~might be better performance)
-- CREATE TABLE users_jobs (
--     job_id INT ON DELETE CASCADE,
--     user_id INT ON DELETE CASCADE,
--     relationship VARCHAR(1) CHECK (relationship in ('E', 'R'))
--     -- E is job an employee has applied for
--     -- R is a job that recruiter has posted
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     PRIMARY KEY (order_id, product_id),
-- );



-- CREATE TABLE chat_messages (
--     message_id SERIAL PRIMARY KEY,
--     sender_id INT REFERENCES users(user_id) ON DELETE CASCADE,
--     receiver_id INT REFERENCES users(user_id) ON DELETE CASCADE,
--     content TEXT NOT NULL,
--     is_read BOOLEAN DEFAULT FALSE,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
-- );


-- -- big question if we want to store them or just keep pdf files (for keeping files we would need some good security checks for malicious files)
-- CREATE TABLE skills (
--     -- how do we want to generate them ?, allow option of adding new ones for recruiters
--     skill_id SERIAL PRIMARY KEY,
--     skill_name VARCHAR(50) NOT NULL UNIQUE,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
-- );

-- CREATE TABLE users_skills (
--     skill_id SERIAL PRIMARY KEY,
--     user_id INT REFERENCES users(worker_id) ON DELETE CASCADE,
--     skill_id INT REFERENCES skills(skill_id) ON DELETE CASCADE,
--     proficiency_level INT CHECK (proficiency_level >= 1 AND proficiency_level <= 10),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
-- );

