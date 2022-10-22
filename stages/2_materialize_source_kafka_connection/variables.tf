variable "materialize_hostname" {
  default = "localhost"
}
variable "materialize_port" {
  default = 6875
}
variable "materialize_database" {
  default = "materialize"
}
variable "materialize_username" {
  default = "materialize"
}
variable "materialize_password" {
  default = "materialize"
}

variable "kafka_connection_name" {
  default = "kafka_connection"
}
variable "kafka_connection_url" {
  default = "redpanda:9092"
}
variable "kafka_schema_registry_name" {
  default = "kafka_schema_registry"
}
variable "kafka_schema_registry_url" {
  default = "http://redpanda:8081"
}