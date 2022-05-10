resource "aws_instance" "astro_backend" {
    ami = "ami-05ba3a39a75be1ec4"
    instance_type = "t2.micro"
    tags = {
            Name = var.name
    }
    key_name         = "my-ec2-ssh"
    vpc_security_group_ids = [aws_security_group.main.id]
    connection {
      type        = "ssh"
      host        = self.public_ip
      user        = "ubuntu"
      private_key = file("my-ec2-ssh")
      timeout     = "4m"
   }
}

resource "aws_security_group" "main" {
  egress = [
    {
      cidr_blocks      = [ "0.0.0.0/0", ]
      description      = ""
      from_port        = 0
      ipv6_cidr_blocks = []
      prefix_list_ids  = []
      protocol         = "-1"
      security_groups  = []
      self             = false
      to_port          = 0
    }
  ]
 ingress                = [
   {
     cidr_blocks      = [ "0.0.0.0/0", ]
     description      = ""
     from_port        = 22
     ipv6_cidr_blocks = []
     prefix_list_ids  = []
     protocol         = "tcp"
     security_groups  = []
     self             = false
     to_port          = 22
  }
  ]
}

resource "aws_key_pair" "devloper_key" {
  key_name   = "my-ec2-ssh"
  public_key = file("my-ec2-ssh.pub")
}

output "target" {
    value = aws_instance.astro_backend.public_ip
}