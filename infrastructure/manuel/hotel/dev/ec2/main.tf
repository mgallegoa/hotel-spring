# Other way to define variables. To define variables in local file.
locals {
  instance-public-tag                      = "EC2 intance to develop"
  instance-private-tag                     = "EC2 intance to data base"
  instance-user-data-install-docker_amazon = <<-EOF
                                      #!/bin/bash
                                      yum update -y
                                      yum install -y docker
                                      systemctl enable docker
                                      systemctl start docker
                                      usermod -aG docker ec2-user
                                      EOF
  instance-user-data-install-docker_ubuntu = <<-EOF
                                      #!/bin/bash
                                      set -e
                                      apt-get update -y
                                      apt-get install -y ca-certificates curl
                                      # Oficial GPG key
                                      install -m 0755 -d /etc/apt/keyrings
                                      curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
                                      chmod a+r /etc/apt/keyrings/docker.asc
                                    echo "Installed the docker repository - Manuel - Mariana"

                                      echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
                                      $(lsb_release -cs) stable" | \
                                      sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

                                      apt-get update -y
                                      apt-get install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

                                      usermod -aG docker ubuntu
                                      systemctl enable docker
                                      systemctl start docker

                            EOF

  instance-user-data-install-docker_debian = <<-EOF
                                    #!/bin/bash
                                    set -e

                                    # Update and install prerequisites
                                    apt-get update -y
                                    apt-get install -y ca-certificates curl gnupg lsb-release

                                    # Add Docker’s official GPG key
                                    install -m 0755 -d /etc/apt/keyrings
                                    curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /etc/apt/keyrings/docker.gpg
                                    chmod a+r /etc/apt/keyrings/docker.gpg

                                    # Add Docker repository for Debian
                                    echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian \
                                      $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null

                                    # Install Docker Engine
                                    apt-get update -y
                                    apt-get install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

                                    # Allow the default user to run docker without sudo
                                    # (Debian images often have user 'admin' or no default; keep 'root' if unsure)
                                    if id -u admin >/dev/null 2>&1; then
                                        usermod -aG docker admin
                                    elif id -u debian >/dev/null 2>&1; then
                                        usermod -aG docker debian
                                    fi

                                    # Enable and start Docker
                                    systemctl enable docker
                                    systemctl start docker
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
  # ami                         = "ami-0634f3c109dcdc659" # Amazon Linux 2023 in the us-east-2
  # ami                         = "ami-0cfde0ea8edd312d4" # Ubuntu Server 24.04 LTS in the us-east-2
  ami = "ami-0bb7d855677353076" # Debian 13 in the us-east-2
  # user_data                   = local.instance-user-data-install-docker_amazon
  # user_data                   = local.instance-user-data-install-docker_ubuntu
  user_data = local.instance-user-data-install-docker_debian

  instance_type               = "t3.small" # 2xCPU, 2 GB Ram
  subnet_id                   = data.terraform_remote_state.network.outputs.public_subnets[0]
  vpc_security_group_ids      = [data.terraform_remote_state.network.outputs.sg_web_id]
  associate_public_ip_address = true
  key_name                    = "ssh-key-AWS-Instance-us-east-2-user-manuelarias-2025-09-11"
  tags = {
    ExtraTag = local.instance-public-tag
    Name     = var.ec2_instances_name[0] # When use set tolist(var.ec2_instances_name)[0]
    Owner    = "https://www.linkedin.com/in/manuel-fernando-gallego-arias/"
    Team     = "Development"
    Project  = "Hotel"
  }
}

resource "aws_instance" "hotel-instances-private-subnet" {
  ami                         = "ami-0634f3c109dcdc659" # Amazon Linux 2023 in the us-east-2
  user_data                   = local.instance-user-data-install-docker_amazon
  instance_type               = "t3.micro" # 2xCPU, 1 GB Ram
  subnet_id                   = data.terraform_remote_state.network.outputs.private_subnets[0]
  vpc_security_group_ids      = [data.terraform_remote_state.network.outputs.sg_db_id]
  associate_public_ip_address = false
  key_name                    = "ssh-key-AWS-Instance-us-east-2-user-manuelarias-2025-09-11"

  tags = {
    ExtraTag = local.instance-private-tag
    Name     = element(var.ec2_instances_name, 1) # to work with list
    Owner    = "https://www.linkedin.com/in/manuel-fernando-gallego-arias/"
    Team     = "Development"
    Project  = "Hotel"
  }
}
