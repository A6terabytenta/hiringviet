package vn.com.hiringviet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.com.hiringviet.dto.ResumeDTO;
import vn.com.hiringviet.model.EducationHistory;
import vn.com.hiringviet.model.EmploymentHistory;
import vn.com.hiringviet.model.Resume;

@Service("resumeService")
public interface ResumeService {

	public List<Integer> getListSkillByMemberId(Integer memberId);

	public boolean deleteSkillOfProfile(ResumeDTO resumeDTO);

	public boolean saveEducation(Resume resume, EducationHistory educationHistory);

	public boolean saveEmployee(Resume resume, EmploymentHistory employmentHistory, Integer positionId);

	public boolean update(Resume currentResume, Resume newResume);

	public boolean updatePhoneNumber(Integer resumeId, String phoneNumber);
}
