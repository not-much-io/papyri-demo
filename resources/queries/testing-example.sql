-- name: create-scroll-table
-- Create scrolls table
CREATE TABLE scroll(
  id INT PRIMARY KEY NOT NULL,
  content VARCHAR NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)

-- name: insert-scroll
-- Insert new scroll to table
INSERT INTO scroll (content)
    VALUES (:content)

