output "vpc_id" {
  value = module.vpc.vpc_id
}

output "public_subnets" {
  value = module.vpc.public_subnets
}

output "private_subnets" {
  value = module.vpc.private_subnets
}

output "nat_ips" {
  value = module.vpc.nat_public_ips
}

output "sg_web_id" {
  value = module.terraform-sg-ssh-http.security_group_id
}

output "sg_db_id" {
  value = module.terraform-sg-ssh.security_group_id
}

