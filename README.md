![Wiqaya drawio](https://github.com/user-attachments/assets/650ccbb3-f4f8-4dc8-8098-e4f1e8e5f234)

---Mohammed---

---Models---

//Mohammed
public class Engineer

//Mohammed
public class RequestInspection

---Endpoints---

// Endpoint No.29
    //mohammed
    //get avg Condition by city (city)
    public Double getAllHouseAverageConditionPercentageByCity(Integer adminId,Integer conditionPercentage,String city)

    // Endpoint No.5
    //Mohammed
    public List<House> findHouseByConditionPercentageLessThan(Integer adminId,Integer conditionPercentage)

    // Endpoint No.25
    //mohammed
    // get cheapest offer by the offers received by id
    public Offer getCheapestOffer(Integer userId, Integer reportId)

    // Endpoint No.11
    //Mohammed
    public void publishReport(Integer userId, Integer reportId)

    // Endpoint No.21
    //mohammed
    public void createRequestInspection(Integer user_id, Integer house_id, RequestInspectionDTOIN requestInspectionDTOIN)

    // Endpoint No.7
    //mohammed
    // admin can check the availability on specifies day (Integer day) not working
        public List<Engineer> getAvailableEngineersForDate(LocalDate date)

    // Endpoint No.8
    //Mohammed
    public void assignEng(Integer adminId, Integer engId, Integer requestInspectionId)

    // Endpoint No.19
    //mohammed
    public String checkMyStatusServiceProvider(Integer id)

    // Endpoint No.20
    //mohammed
    public List<ServiceProvider> getServiceProvidersAboveOrders(Integer userId, Integer doneOrdersNum)

    // Endpoint No.4
    //Mohammed
    public List<Engineer> getAllEngUnderReview(Integer adminId)

    ---Presentation---
    https://www.canva.com/design/DAGZsKUl--4/zBQHQxbyuD6CUgzxp38PmQ/edit?utm_content=DAGZsKUl--4&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
