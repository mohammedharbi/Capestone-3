package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.Model.Engineer;
import com.example.wiqaya.Repository.EngineerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EngineerService {

    private final EngineerRepository engineerRepository;

    public List<Engineer> findAllEngineers() {
        return engineerRepository.findAll();
    }

    public void addEngineer(Engineer engineer) {
        engineerRepository.save(engineer);
    }

    public void updateEngineer(Integer id,Engineer engineer) {
        Engineer engineer1 = engineerRepository.findEngineerById(id);
        if (engineer1 != null) {
            engineer1.setFullName(engineer.getFullName());
            engineer1.setPhoneNumber(engineer.getPhoneNumber());
            engineer1.setEmail(engineer.getEmail());
            //engineer1.setAccreditationNumber(engineer.getAccreditationNumber()); is there any need to update AccreditationNumber ?? i don't think so.
            engineer1.setCity(engineer.getCity());
            engineerRepository.save(engineer1);
        }else throw new ApiException("Engineer Not Found");
    }

    public void deleteEngineer(Integer id) {
        Engineer engineer1 = engineerRepository.findEngineerById(id);
        if (engineer1 != null) {
            engineerRepository.delete(engineer1);
        }else throw new ApiException("Engineer Not Found");
    }

}
