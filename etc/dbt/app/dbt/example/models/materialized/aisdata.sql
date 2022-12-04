{{ config(
    materialized = 'materializedview',
    alias = 'aisdata',
    tags = ["materialized"],
    indexes=[
      {'columns': ["'mmsi'"]},
      {'columns': ["'vesselName'"]},
      {'columns': ["'baseDateTime'"]},
      {'columns': ["'lon'"]},
      {'columns': ["'lat'"]},
      {'columns': ["'mmsi'", "'baseDateTime'", "'lon'", "'lat'"]}
    ]
) }}

SELECT * FROM {{ ref('aisvesseldata') }}