{{ config(materialized='source') }}

CREATE SOURCE {{ this }}
  FROM KAFKA CONNECTION kafka_connection (TOPIC 'AisVesselData')
  FORMAT AVRO USING CONFLUENT SCHEMA REGISTRY CONNECTION kafka_schema_registry
  INCLUDE HEADERS, TIMESTAMP as event_timestamp
  WITH (SIZE = '2');
