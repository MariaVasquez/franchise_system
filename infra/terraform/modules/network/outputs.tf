output "franchise_alb_sg_id" {
  value = aws_security_group.franchise_alb_sg.id
}

output "franchise_service_sg_id" {
  value = aws_security_group.franchise_service_sg.id
}

output "franchise_db_sg_id" {
  value = aws_security_group.franchise_db_sg.id
}

output "backend_target_group_arn" {
  value = aws_lb_target_group.backend_tg.arn
}

output "alb_dns_name" {
  value = aws_lb.franchise_alb.dns_name
}