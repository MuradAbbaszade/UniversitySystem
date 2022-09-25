package com.company.faculty.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Faculty {
    private Long id;
    private String name;
}
