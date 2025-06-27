variable "vpc_id" {
  description = "ID de la VPC donde se crean los security groups"
  type        = string
}
variable "subnet_ids" {
  description = "IDs de las subnets públicas para el ALB"
  type        = list(string)
}