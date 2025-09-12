# Other way to define variables. To define variables in local file.
locals {
  instanse-public-tag  = "EC2 intance to develop"
  instanse-private-tag = "EC2 intance to data base"
  instanse-user-data-install-docker = <<-EOF
                                      #!/bin/bash
                                      yum update -y
                                      yum install -y docker
                                      systemctl enable docker
                                      systemctl start docker
                                      usermod -aG docker ec2-user
                                      EOF
}

data "terraform_remote_state" "network" {
  backend = "s3"
  config = {
    bucket = "terraform.state.manuel.fernando.arias.marin"
    key    = "manuel/network/terraform.tfstate"
    region = "us-east-1"
  }
}

resource "aws_instance" "hotel-instances-public-subnet" {
  ami                         = "ami-0634f3c109dcdc659" # Amazon Linux 2023 in the us-east-2
  instance_type               = "t3.small"              # 2xCPU, 2 GB Ram
  subnet_id                   = data.terraform_remote_state.network.outputs.public_subnets[0]
  vpc_security_group_ids      = [data.terraform_remote_state.network.outputs.sg_web_id]
  associate_public_ip_address = true
  key_name                    = "ssh-key-AWS-Instance-us-east-2-user-manuelarias-2025-09-11"
  user_data                   = local.instanse-user-data-install-docker

  tags = {
    ExtraTag = local.instanse-public-tag
    Name     = var.ec2_instances_name[0] # When use set tolist(var.ec2_instances_name)[0]
    Owner    = "https://www.linkedin.com/in/manuel-fernando-gallego-arias/"
    Team     = "Development"
    Project  = "Hotel"
  }
}

resource "aws_instance" "hotel-instances-private-subnet" {
  ami                         = "ami-0634f3c109dcdc659" # Amazon Linux 2023 in the us-east-2
  instance_type               = "t3.micro"              # 2xCPU, 1 GB Ram
  subnet_id                   = data.terraform_remote_state.network.outputs.private_subnets[0]
  vpc_security_group_ids      = [data.terraform_remote_state.network.outputs.sg_db_id]
  associate_public_ip_address = false
  key_name                    = "ssh-key-AWS-Instance-us-east-2-user-manuelarias-2025-09-11"
  user_data                   = local.instanse-user-data-install-docker

  tags = {
    ExtraTag = local.instanse-private-tag
    Name     = element(var.ec2_instances_name, 1) # to work with list
    Owner    = "https://www.linkedin.com/in/manuel-fernando-gallego-arias/"
    Team     = "Development"
    Project  = "Hotel"
  }
}
