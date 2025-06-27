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