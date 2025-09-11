module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "6.0.1"

  name = "VPC-Terraform-manuel-company"
  cidr = "10.0.0.0/16"

  azs             = ["us-east-2a", "us-east-2b"]
  private_subnets = ["10.0.1.0/24"]
  public_subnets  = ["10.0.101.0/24"]

  enable_vpn_gateway   = true
  enable_dns_hostnames = true
  enable_dns_support   = true
  enable_nat_gateway   = false # To enable/disable creation of an Elastic Ip every VPC creation. Default true

  tags = {
    Terraform   = "true"
    Environment = "dev"
  }
}

module "terraform-sg-ssh-http" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "5.3.0"

  name = "SG-Terraform_ssh-http-rules"

  description         = "Security Group create with terraform manuel, enable ssh and http"
  vpc_id              = module.vpc.vpc_id
  ingress_cidr_blocks = ["0.0.0.0/0"]
  ingress_rules       = ["https-443-tcp", "http-80-tcp"]
  egress_cidr_blocks  = ["0.0.0.0/0"]
  egress_rules        = ["https-443-tcp", "http-80-tcp"]
}

module "terraform-sg-ssh" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "5.3.0"

  name = "SG-Terraform_ssh-rules"

  description         = "Security Group create with terraform manuel, enable ssh"
  vpc_id              = module.vpc.vpc_id
  ingress_cidr_blocks = ["0.0.0.0/0"]
  ingress_rules       = ["https-443-tcp"]
  egress_cidr_blocks  = ["0.0.0.0/0"]
  egress_rules        = ["https-443-tcp"]
}
