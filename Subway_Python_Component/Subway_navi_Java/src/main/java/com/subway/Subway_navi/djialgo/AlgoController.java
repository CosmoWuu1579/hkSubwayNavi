package com.subway.Subway_navi.djialgo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "http://localhost:5000")
public class AlgoController {
    private final AlgoRespository algoRespository;

    public AlgoController(AlgoRespository algoRespository){
        this.algoRespository = algoRespository;
    }
    @GetMapping("")
    Object home(
            @RequestParam String param1,
            @RequestParam String param2
    ){
        return algoRespository.findDirection(param1,param2);
    }

}