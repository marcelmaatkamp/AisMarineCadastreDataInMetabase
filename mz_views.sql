CREATE SOURCE test
FROM KAFKA BROKER 'redpanda:9092' TOPIC 'test'
FORMAT TEXT
ENVELOPE NONE;

CREATE MATERIALIZED VIEW test_mv AS
SELECT * FROM test;
