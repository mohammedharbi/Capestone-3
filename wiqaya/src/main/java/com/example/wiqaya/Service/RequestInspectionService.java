package com.example.wiqaya.Service;

import com.example.wiqaya.ApiResponse.ApiException;
import com.example.wiqaya.Model.RequestInspection;
import com.example.wiqaya.Repository.RequestInspectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestInspectionService {

    private final RequestInspectionRepository requestInspectionRepository;

    public List<RequestInspection> getRequestInspection() {
        return requestInspectionRepository.findAll();
    }

    public void addRequestInspection(RequestInspection requestInspection) {
        requestInspectionRepository.save(requestInspection);
    }

    // is there any need to update a request ??
//    public void updateRequestInspection(Integer id, RequestInspection requestInspection) {
//        RequestInspection requestInspection1 = requestInspectionRepository.findRequestInspectionById(id);
//
//        if (requestInspection1 != null) {
//            requestInspection.setStatus(requestInspection.getStatus());
//            requestInspectionRepository.save(requestInspection);
//        }else throw new ApiException("requestInspection not found");
//    }

    public void deleteRequestInspection(Integer id) {
        RequestInspection requestInspection = requestInspectionRepository.findRequestInspectionById(id);
        if (requestInspection != null) {
            requestInspectionRepository.delete(requestInspection);
        }else throw new ApiException("requestInspection not found");
    }
}
