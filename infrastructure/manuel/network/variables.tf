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
