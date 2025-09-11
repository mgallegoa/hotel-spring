variable "access_key" {
  description = "access key"
  type        = string
  sensitive   = true # Don't print the sensitive data
}
variable "secret_key" {
  description = "secret key"
  type        = string
  sensitive   = true # Don't print the sensitive data
}

variable "ec2_instances_name" {
  description = "Names for the ec2 instances"
  type        = list(string) # Set is UN ordered variable, use list to respect the order of the list
}
