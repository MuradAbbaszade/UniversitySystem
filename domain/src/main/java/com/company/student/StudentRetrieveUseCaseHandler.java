package com.company.student;

import com.company.common.usecase.UseCaseHandler;
import com.company.student.model.Student;
import com.company.student.port.StudentPort;
import com.company.student.usecase.RetrieveStudent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("byIdStudent")
@RequiredArgsConstructor
public class StudentRetrieveUseCaseHandler implements UseCaseHandler<Student, RetrieveStudent> {

    private final StudentPort studentPort;

    @Override
    public Student handle(RetrieveStudent useCase) throws Exception {
        return studentPort.retrieve(useCase);
    }
}
