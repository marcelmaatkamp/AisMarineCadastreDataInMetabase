locals {
  metabase = {
    url = "http://localhost:3000"
    credentials = {
      username = "admin"
      password = "admin"
    }
    database = {
      hostname = "materialized"
      port = 6875
      database = "materialize"
      username = "materialize"
      password = "materialize"
    }
  }
}

provider "metabase" {
  host = "http://localhost:3000"
  username = "admin"
  password = "admin"
}
