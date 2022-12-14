package com.company.adapters.qualification.jpa;

import com.company.adapters.faculty.jpa.entity.FacultyEntity;
import com.company.adapters.faculty.jpa.repository.FacultyJpaRepository;
import com.company.adapters.qualification.jpa.entity.QualificationEntity;
import com.company.adapters.qualification.jpa.repository.QualificationJpaRepository;
import com.company.faculty.model.Faculty;
import com.company.qualification.model.Qualification;
import com.company.qualification.port.QualificationPort;
import com.company.qualification.usecase.DeleteQualification;
import com.company.qualification.usecase.RetrieveQualification;
import com.company.qualification.usecase.SaveQualification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationAdapter implements QualificationPort {

    private final QualificationJpaRepository qualificationJpaRepository;
    private final FacultyJpaRepository facultyJpaRepository;

    @Override
    public Qualification save(SaveQualification saveQualification) {
        QualificationEntity qualificationEntity = new QualificationEntity();
        qualificationEntity.setId(saveQualification.getId());
        qualificationEntity.setName(saveQualification.getName());
        Faculty faculty = saveQualification.getFaculty();
        FacultyEntity facultyEntity = facultyJpaRepository.findById(faculty.getId()).get();
        qualificationEntity.setFacultyEntity(facultyEntity);
        return qualificationJpaRepository.save(qualificationEntity).toModel();
    }

    @Override
    public Qualification retrieve(RetrieveQualification retrieveQualification) throws Exception {
        return qualificationJpaRepository.findById(retrieveQualification.getId())
                .map(QualificationEntity::toModel)
                .orElseThrow(() -> new Exception("Qualification not found"));
    }

    @Override
    public Qualification delete(DeleteQualification deleteQualification) throws Exception {
        Qualification qualification = qualificationJpaRepository.findById(deleteQualification.getId())
                .map(QualificationEntity::toModel)
                .orElseThrow(() -> new Exception("Qualification not found"));
        qualificationJpaRepository.deleteById(deleteQualification.getId());
        return qualification;
    }

}
