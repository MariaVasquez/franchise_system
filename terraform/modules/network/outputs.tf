output "franchise_alb_sg_id" {
  value = aws_security_group.franchise_alb_sg.id
}

output "franchise_service_sg_id" {
  value = aws_security_group.franchise_service_sg.id
}

output "franchise_db_sg_id" {
  value = aws_security_group.franchise_db_sg.id
}