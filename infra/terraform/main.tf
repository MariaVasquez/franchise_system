terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }

  required_version = ">= 1.3.0"
}

provider "aws" {
  region = var.aws_region
}

module "network" {
  source = "./modules/network"
  vpc_id = var.vpc_id
  subnet_ids = var.subnet_ids
  acm_certificate_arn = var.acm_certificate_arn
}

module "ecr" {
  source = "./modules/ecr"
}

module "rds" {
  source             = "./modules/rds"
  subnet_ids         = var.subnet_ids
  franchise_db_sg_id = module.network.franchise_db_sg_id
}

module "ecs" {
  source                      = "./modules/ecs"
  ecr_repo_url                = module.ecr.ecr_repo_url
  ecs_task_execution_role_arn = var.ecs_task_execution_role_arn
  subnet_ids                  = var.subnet_ids
  target_group_arn            = module.network.backend_target_group_arn
  service_security_group_id = module.network.franchise_service_sg_id
  ecs_cluster_id              = module.ecs.ecs_cluster_id
  container_port              = var.container_port

  db_url        = var.db_url
  db_username   = var.db_username
  db_password   = var.db_password
  port          = var.container_port
}