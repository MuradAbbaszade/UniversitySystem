package com.company.adapters.qualification.jpa.repository;

import com.company.adapters.qualification.jpa.entity.QualificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QualificationJpaRepository extends JpaRepository<QualificationEntity,Long> {

    @Query(value = "select * from qualification where faculty_id=:id" , nativeQuery = true)
    public List<QualificationEntity> getQualificationList(@Param("id") Long id);

    @Query(value = "select * from qualification where name=:name" , nativeQuery = true)
    public Optional<QualificationEntity> findByName(@Param("name") String name);
}
