{{ config(materialized='source') }}

CREATE SOURCE IF NOT EXISTS {{ this }}
  FROM KAFKA CONNECTION kafka_connection (TOPIC 'topic_a')
  FORMAT TEXT