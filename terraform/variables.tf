variable "franchise_domain" {
  default     = "api.mvdeccenter.tech"
  description = "Dominio personalizado para el backend"
}

variable "aws_region" {
  description = "Región donde se desplegarán los recursos de AWS"
  type        = string
  default     = "us-east-1"
}

variable "vpc_id" {
  description = "ID de la VPC donde se crean los security groups"
  type        = string
}

variable "subnet_ids" {
  description = "Lista de Subnets para RDS"
  type        = list(string)
}

variable "ecr_repo_url" {
  description = "URL del repositorio ECR con la imagen"
  type        = string
}

variable "ecs_task_execution_role_arn" {
  description = "ARN del rol de ejecución para ECS"
  type        = string
}

variable "container_port" {
  description = "Puerto expuesto por el contenedor"
  type        = number
  default     = 8080
}

variable "profile" {
  description = "Perfil en el que se ejecuta la app"
  type        = string
}

variable "db_url" {
  description = "URL de conexión R2DBC a la base de datos MySQL"
  type        = string
}

variable "db_username" {
  description = "Usuario de la base de datos"
  type        = string
}

variable "db_password" {
  description = "Contraseña de la base de datos"
  type        = string
  sensitive   = true
}