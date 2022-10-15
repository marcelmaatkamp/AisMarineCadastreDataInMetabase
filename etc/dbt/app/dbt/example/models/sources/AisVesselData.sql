{{ config(materialized='source') }}

CREATE SOURCE {{ this }}
FROM KAFKA BROKER {{ "'" ~ var('kafka_broker') ~ "'" }}
TOPIC 'AisVesselData'
FORMAT AVRO USING CONFLUENT SCHEMA REGISTRY {{ "'" ~ var('kafka_schema_registry') ~ "'" }}
INCLUDE TIMESTAMP as event_timestamp;
