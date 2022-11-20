# materialize-dbt-example
Ingests AIS data in Materialized and displays contents via Metabase.

# schematics
```mermaid
flowchart 
 
subgraph import["import"]
  marinecadastre.gov(marinecadastre.gov)-- raw data --> AisProducer
  AisProducer-- AVRO --> Redpanda{{Redpanda}}
  Redpanda-- source --> Materialized[(Materialized)]
end

subgraph models["models"]
    DBT--> Materialized
end

subgraph display["views"]
    Materialized--> Metabase
    Materialized--> Superset
    Materialized--> Redash
    Materialized--> ...
end
```

# AIS
https://marinecadastre.gov/ais/
![](documentation/images/AIS/AIS_1.png)

## Raw Data Metadata
https://coast.noaa.gov/data/marinecadastre/ais/data-dictionary.pdf
![](documentation/images/AIS/AIS_2.png)

## Raw data
![](documentation/images/AIS/AIS_3.png)

# start applications
```bash
$ docker-compose up -d 
```
# initialize applications 

## upload avro ais schema
stages/1_schemas/avro/avdl/ais_vesseldata.avdl
```bash
$ cd stages/1_schemas &&\
  terraform init &&\
  terraform plan &&\
  terraform apply
```
# verify schema in redpanda
http://localhost:8084/schema-registry/AisVesselData-value
![](documentation/images/redpanda/redpanda_schema.png)

## start dbt connection
```bash
$ cd tages/2_materialize_source_kafka_connection &&
  terraform init &&\
  terraform plan &&\
  terraform apply
```
## verify dbt connection
http://localhost:8085/#!/model/model.example.aisvesseldata#details
![](documentation/images/dbt/dbt_ais_vesseldata.png)

## start ingest
```
$ docker-compose -f docker-compose.yml -f docker-compose-ingest.yml run ingest
```

# verify ingest in redpanda
http://localhost:8084/topics/AisVesselData?o=-1&p=-1&q&s=50#messages
![](documentation/images/redpanda/redpanda_data.png)

# verify bt
http://localhost:8083
![](documentation/images/dbt/dbt_1.png)

# verify materialized objects
```bash
$ docker-compose exec materialized psql -h localhost -p 6875 -c 'show objects;'
         name          |       type        
-----------------------+-------------------
 aisdata               | materialized-view
 aisvesseldata         | source
 kafka_connection      | connection
 kafka_schema_registry | connection
```
# verify materialized aisdata

```bash
 $ docker-compose exec materialized psql -h localhost -p 6875 -c 'select count(*) from aisdata;'
 count 
-------
    51
(1 row)
```

# metabase
http://localhost:3000

| username | password   |
| -------- |------------|
| user01@metabase.com | password1! | 
 | user02@metabase.com | password2! | 

![](documentation/images/metabase/metabase_1.png)
![](documentation/images/metabase/metabase_2.png)
![](documentation/images/metabase/metabase_3.png)
![](documentation/images/metabase/metabase_4.png)

