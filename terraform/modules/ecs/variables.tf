variable "ecr_repo_url" {}
variable "ecs_task_execution_role_arn" {}
variable "ecs_cluster_id" {}
variable "subnet_ids" {
  type = list(string)
}
variable "service_security_group_id" {}
variable "target_group_arn" {}
variable "container_port" {
  default = 8080
}

variable "db_url" {}
variable "db_username" {}
variable "db_password" {}
variable "port" {}
variable "profile" {}