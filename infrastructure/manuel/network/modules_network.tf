module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "6.0.1"

  name = "VPC-Terraform-manuel-company"
  cidr = "10.0.0.0/16"

  azs             = ["us-east-2a", "us-east-2b"]
  private_subnets = ["10.0.1.0/24"]
  public_subnets  = ["10.0.101.0/24"]

  enable_vpn_gateway = true
  # To enable/disable creation of NAT Gateway to communicate private nets with internet. Default true
  enable_nat_gateway   = true
  single_nat_gateway   = true # Single NAT gateway shared across AZs
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Terraform   = "true"
    Environment = "dev"
  }
}

module "terraform-sg-ssh-http" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "5.3.0"

  name = "SG-Terraform_ssh-http-rules"

  description = "Security Group create with terraform manuel, enable ssh and http"
  vpc_id      = module.vpc.vpc_id
  # HTTP/HTTPS from everywhere
  ingress_with_cidr_blocks = [
    {
      rule        = "http-80-tcp"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      rule        = "https-443-tcp"
      cidr_blocks = "0.0.0.0/0"
    },
    {
      rule        = "ssh-tcp"
      cidr_blocks = "0.0.0.0/0" # 203.0.113.25/32 in case to use only the IP for SSH
    }
  ]
  egress_cidr_blocks = ["0.0.0.0/0"]
  egress_rules       = ["ssh-tcp", "https-443-tcp", "http-80-tcp"]
}

module "terraform-sg-ssh" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "5.3.0"

  name = "SG-Terraform_ssh-rules"

  description         = "Security Group create with terraform manuel, enable ssh"
  vpc_id              = module.vpc.vpc_id
  ingress_cidr_blocks = ["0.0.0.0/0"]
  ingress_rules       = ["ssh-tcp"]
  
  # outbound everything
  egress_cidr_blocks  = ["0.0.0.0/0"]
  egress_rules        = ["all-all"]
}
