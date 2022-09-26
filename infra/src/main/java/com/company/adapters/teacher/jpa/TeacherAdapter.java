package com.company.adapters.teacher.jpa;

import com.company.adapters.teacher.jpa.entity.TeacherEntity;
import com.company.adapters.teacher.jpa.repository.TeacherJpaRepository;
import com.company.teacher.model.Teacher;
import com.company.teacher.port.TeacherPort;
import com.company.teacher.usecase.DeleteTeacher;
import com.company.teacher.usecase.RetrieveTeacher;
import com.company.teacher.usecase.SaveTeacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherAdapter implements TeacherPort {

    private final TeacherJpaRepository teacherJpaRepository;
    @Override
    public Teacher retrieve(RetrieveTeacher retrieveTeacher) throws Exception {
        return teacherJpaRepository.findById(retrieveTeacher.getId())
                .map(TeacherEntity::toModel)
                .orElseThrow(()->new Exception("Teacher not found"));
    }

    @Override
    public Teacher delete(DeleteTeacher deleteTeacher) throws Exception {
        Teacher teacher = teacherJpaRepository.findById(deleteTeacher.getId())
                .map(TeacherEntity::toModel)
                .orElseThrow(()->new Exception("Teacher not found"));
        teacherJpaRepository.deleteById(deleteTeacher.getId());
        return teacher;
    }

    @Override
    public Teacher save(SaveTeacher saveTeacher) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(saveTeacher.getId());
        teacherEntity.setName(saveTeacher.getName());
        teacherEntity.setSurname(saveTeacher.getSurname());
        return teacherJpaRepository.save(teacherEntity).toModel();
    }
}