{{ config(
    materialized = 'source',
    tags = ["source","kafka"]
) }}

CREATE CONNECTION redpanda_broker FOR KAFKA BROKER {{ "'" ~ var('kafka_broker') ~ "'" }};
CREATE CONNECTION redpanda_registry FOR CONFLUENT SCHEMA REGISTRY URL {{ "'" ~ var('kafka_schema_registry') ~ "'" }};

CREATE SOURCE current_predictions
  FROM KAFKA CONNECTION redpanda_broker (TOPIC 'events')
  FORMAT AVRO
  USING CONFLUENT SCHEMA REGISTRY CONNECTION redpanda_registry
  INCLUDE TIMESTAMP as event_timestamp
  ENVELOPE UPSERT;