package com.company.adapters.group.jpa;

import com.company.adapters.group.jpa.entity.GroupEntity;
import com.company.adapters.group.jpa.repository.GroupJpaRepository;
import com.company.adapters.qualification.jpa.entity.QualificationEntity;
import com.company.adapters.qualification.jpa.repository.QualificationJpaRepository;
import com.company.group.model.Group;
import com.company.group.port.GroupPort;
import com.company.group.usecase.DeleteGroup;
import com.company.group.usecase.RetrieveGroup;
import com.company.group.usecase.SaveGroup;
import com.company.qualification.model.Qualification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupAdapter implements GroupPort {

    private final GroupJpaRepository groupJpaRepository;
    private final QualificationJpaRepository qualificationJpaRepository;

    @Override
    public Group retrieve(RetrieveGroup retrieveGroup) throws Exception {
        return groupJpaRepository.findById(retrieveGroup.getId())
                .map(GroupEntity::toModel)
                .orElseThrow(() -> new Exception("Group not found"));
    }

    @Override
    public Group delete(DeleteGroup deleteGroup) throws Exception {
        Group group = groupJpaRepository.findById(deleteGroup.getId())
                .map(GroupEntity::toModel)
                .orElseThrow(() -> new Exception("Group not found"));
        groupJpaRepository.deleteById(deleteGroup.getId());
        return group;
    }

    @Override
    public Group save(SaveGroup saveGroup) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId(saveGroup.getId());
        groupEntity.setName(saveGroup.getName());
        groupEntity.setYear(saveGroup.getYear());
        Qualification qualification = saveGroup.getQualification();
        QualificationEntity qualificationEntity = qualificationJpaRepository.findById(qualification.getId()).get();
        groupEntity.setQualificationEntity(qualificationEntity);
        return groupJpaRepository.save(groupEntity).toModel();
    }
}
