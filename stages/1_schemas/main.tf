provider "schemaregistry" {
    schema_registry_url = "http://localhost:8081"
    # username            = "<confluent_schema_registry_key>"
    # password            = "<confluent_schema_registry_password>"
}

# avro/avpr/ais_vesseldata.avpr
resource "schemaregistry_schema" "ais_vesseldata" {
    for_each = fileset("${path.module}/avro/avsc/", "**/*.avsc")
    subject =substr(each.value, 0, length(each.value)-4)
    schema  = file("${path.module}/avro/avsc/${each.value}")
}
