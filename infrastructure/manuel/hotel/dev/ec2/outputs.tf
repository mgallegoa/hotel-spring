output "instance_ip_public-subnet" {
  value = aws_instance.hotel-instances-public-subnet.private_ip
  # value = { for service, i in aws_instance.hotel-instances : service => i.private_ip }
}
output "instance_ip_private-subnet" {
  value = aws_instance.hotel-instances-private-subnet.private_ip
  # value = { for service, i in aws_instance.hotel-instances : service => i.private_ip }
}

output "hotel-instances-public-subnet_public_ip" {
  description = "Public IP of the EC2 instance"
  value       = aws_instance.hotel-instances-public-subnet.public_ip
}
