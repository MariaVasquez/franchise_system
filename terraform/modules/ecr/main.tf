resource "aws_ecr_repository" "franchise_repo" {
  name                 = "franchise-app"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }

  tags = {
    Environment = "dev"
  }
}