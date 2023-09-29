package net.java.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequest {

@NotNull(message = "Username Should not be null")
private String name;
@Email(message = " Invalid Email address")
private String email;
@Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number entered")
private String mobile;
private String gender;
@Min(value = 18,message = "minimum required age 18")
@Max(value = 60,message = "maximun up to 60")
private int age;
@NotBlank(message = "nationality should not be Blank")
private String nationality;
}
