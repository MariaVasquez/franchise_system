resource "aws_db_subnet_group" "franchise_db_subnet_group" {
  name       = "franchise_db-subnet-group"
  subnet_ids = var.subnet_ids

  tags = {
    Name = "franchise_db-subnet-group"
  }
}

resource "aws_db_instance" "franchise_db" {
  identifier              = "franchise-db"
  allocated_storage       = 20
  engine                  = "mysql"
  engine_version          = "8.0.36"
  instance_class          = "db.t3.micro"
  db_name                 = var.db_name
  username                = var.db_username
  password                = var.db_password
  skip_final_snapshot     = true
  publicly_accessible     = true
  db_subnet_group_name    = aws_db_subnet_group.franchise_db_subnet_group.name
  vpc_security_group_ids = [var.franchise_db_sg_id]
  multi_az                = false
}