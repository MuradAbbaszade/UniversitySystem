package com.company.teacher.usecase;

import com.company.common.model.UseCase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveTeacher implements UseCase {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Long roleId;
}
