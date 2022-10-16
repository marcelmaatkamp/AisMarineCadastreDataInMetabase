{{ config(
    materialized = 'materializedview',
    alias = 'aisdata',
    tags = ["materialized"]
) }}

SELECT * FROM {{ ref('aisvesseldata') }}