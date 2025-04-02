package com.example.mvc.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private String firstName;

	@NotNull(message = "Last name is required")
	@Size(min = 2, message = "Last name length>=2 required ")
	private String lastName;

	@Min(value = 0, message = "Must be greater than or equal to zero")
	@Max(value = 10, message = "Must be less than or equal to 10")
	private int freePasses;

	@Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "Only 5 character/digits allowed")
	private String postalCode;

}
