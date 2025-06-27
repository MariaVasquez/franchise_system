variable "db_username" {
  type        = string
  default     = "adminfranchise"
}

variable "db_password" {
  type        = string
  sensitive   = true
  default     = "admin_franchise"
}

variable "db_name" {
  type        = string
  default     = "franchise_system"
}

variable "franchise_db_sg_id" {
  description = "ID del Security Group para RDS"
  type        = string
}

variable "subnet_ids" {
  description = "Lista de Subnets para RDS"
  type        = list(string)
}