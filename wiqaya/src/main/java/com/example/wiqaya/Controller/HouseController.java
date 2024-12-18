package com.example.wiqaya.Controller;

import com.example.wiqaya.ApiResponse.ApiResponse;
import com.example.wiqaya.DTO.IN.HouseDTOIN;
import com.example.wiqaya.Model.House;
import com.example.wiqaya.Service.HouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wiqaya/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllHouses(){
        return ResponseEntity.status(200).body(houseService.getAllHouses());
    }

    @PostMapping("/add/user-id/{user_id}")
    public ResponseEntity<?> addHouse(@PathVariable Integer user_id,@RequestBody @Valid HouseDTOIN house){
        houseService.addHouse(user_id,house);
        return ResponseEntity.status(200).body(new ApiResponse("House added"));
    }

    @PutMapping("/update/house-id/{id}")
    public ResponseEntity<?> updateHouse(@PathVariable Integer id,@RequestBody @Valid House house){
        houseService.updateHouse(id,house);
        return ResponseEntity.status(200).body(new ApiResponse("House updated"));
    }

    @DeleteMapping("/delete/user-id/{userId}/house-id/{houseId}")
    public ResponseEntity<?> deleteHouse(@PathVariable Integer userId,@PathVariable Integer houseId){
        houseService.deleteHouse(userId,houseId);
        return ResponseEntity.status(200).body(new ApiResponse("House deleted"));
    }

    @GetMapping("/find-house-by-condition-percentage-less-than/adminId/{admin_id}/conditionPercentage/{conditionPercentage}")
    public ResponseEntity findHouseByConditionPercentage(@PathVariable Integer admin_id,@PathVariable Integer conditionPercentage){
        return ResponseEntity.status(200).body(houseService.findHouseByConditionPercentageLessThan(admin_id,conditionPercentage));
    }

}
