locals {
  materialize = {
    host     = var.materialize_hostname
    port     = var.materialize_port
    database = var.materialize_database
    username = var.materialize_username
    password = var.materialize_password
  }
  kafka = {
    connection = {
      name = var.kafka_connection_name
      url = var.kafka_connection_url
    }
    schema-registry = {
      name = var.kafka_schema_registry_name
      url = var.kafka_schema_registry_url
    }
  }
}
provider "sql" {
  alias = "postgres"
  url   = "postgres://${local.materialize.username}:${local.materialize.password}@${local.materialize.host}:${local.materialize.port}/${local.materialize.database}?sslmode=disable"
}

resource "sql_migrate" "kafka_connection" {
  provider = sql.postgres
  migration {
    id   = local.kafka.connection.name
    up   = "CREATE CONNECTION ${local.kafka.connection.name} TO KAFKA (BROKER '${local.kafka.connection.url}');"
    down = "DROP CONNECTION ${local.kafka.connection.url};"
  }
}

resource "sql_migrate" "kafka_connection_schema_registry" {
  provider = sql.postgres
  migration {
    id   = local.kafka.schema-registry.name
    up   = "CREATE CONNECTION ${local.kafka.schema-registry.name} TO CONFLUENT SCHEMA REGISTRY (URL '${local.kafka.schema-registry.url}');"
    down = "DROP CONNECTION ${local.kafka.schema-registry.name};"
  }
}
