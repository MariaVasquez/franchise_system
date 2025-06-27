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
}

module "ecr" {
  source = "./modules/ecr"
}

module "rds" {
  source             = "./modules/rds"
  subnet_ids         = var.subnet_ids
  franchise_db_sg_id = module.network.franchise_db_sg_id
}