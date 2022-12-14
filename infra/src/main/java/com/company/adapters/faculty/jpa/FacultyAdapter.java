package com.company.adapters.faculty.jpa;

import com.company.adapters.faculty.jpa.entity.FacultyEntity;
import com.company.adapters.faculty.jpa.repository.FacultyJpaRepository;
import com.company.adapters.qualification.jpa.repository.QualificationJpaRepository;
import com.company.faculty.model.Faculty;
import com.company.faculty.port.FacultyPort;
import com.company.faculty.usecase.DeleteFaculty;
import com.company.faculty.usecase.RetrieveFaculty;
import com.company.faculty.usecase.SaveFaculty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyAdapter implements FacultyPort {

    private final FacultyJpaRepository facultyJpaRepository;
    private final QualificationJpaRepository qualificationJpaRepository;

    @Override
    public Faculty save(SaveFaculty saveFaculty) {
        FacultyEntity facultyEntity = new FacultyEntity();
        facultyEntity.setId(saveFaculty.getId());
        facultyEntity.setName(saveFaculty.getName());
        facultyEntity.setQualificationEntityList(
                qualificationJpaRepository.getQualificationList(saveFaculty.getId()));
        return facultyJpaRepository.save(facultyEntity).toModel();
    }

    @Override
    public Faculty delete(DeleteFaculty deleteFaculty) throws Exception {
        Faculty faculty = facultyJpaRepository.findById(deleteFaculty.getId())
                .map(FacultyEntity::toModel)
                .orElseThrow(() -> new Exception("Faculty not found"));
        facultyJpaRepository.deleteById(deleteFaculty.getId());
        return faculty;
    }

    @Override
    public Faculty retrieve(RetrieveFaculty retrieveFaculty) throws Exception {
        return facultyJpaRepository.findById(retrieveFaculty.getId())
                .map(FacultyEntity::toModel)
                .orElseThrow(() -> new Exception("Faculty not found"));
    }
}
