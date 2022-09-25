package com.company.faculty.port;

import com.company.faculty.model.Faculty;
import com.company.faculty.usecase.DeleteFaculty;
import com.company.faculty.usecase.RetrieveFaculty;
import com.company.faculty.usecase.SaveFaculty;

public interface FacultyPort {
    Faculty saveFaculty(SaveFaculty saveFaculty);
    Faculty deleteFaculty(DeleteFaculty deleteFaculty) throws Exception;
    Faculty retrieve(RetrieveFaculty retrieveFaculty) throws Exception;
}
