package com.example.springboot.service.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Person entity.
 */
@Data
@EqualsAndHashCode
public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "The name is mandatory")
    private String name;

}
