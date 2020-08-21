package com.intellisoft.excelreader.web.controller;

import com.intellisoft.excelreader.domain.entity.AirLine;
import com.intellisoft.excelreader.domain.entity.AirPort;
import com.intellisoft.excelreader.service.pnrAndPnlService.AirLineService;
import com.intellisoft.excelreader.service.pnrAndPnlService.AirPortService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PnlController {
    private AirLineService airLineService;
    private AirPortService airPortService;

    public PnlController(AirLineService airLineService, AirPortService airPortService) {
        this.airLineService = airLineService;
        this.airPortService = airPortService;
    }

    @GetMapping("/upload-pnl")
    public String pnlForm(Model model){
        List<AirLine> airlineList = airLineService.findAllAirline();
        model.addAttribute("airlines",airlineList);
        model.addAttribute("view","pnl-pnr/upload-pnl");

        return "home";
    }

    @PostMapping("/upload-pnl")
    public String getFlightDate(Model model){



        //iskam da e drug template
        return "home";
    }
    @GetMapping("/airlines-list")
    @ResponseBody
    public List<AirLine> airlineList(HttpSession session){

        List<AirLine> airlines = airLineService.findAllAirline()
                .stream()
                .sorted(Comparator.comparing(AirLine::getName))
                .collect(Collectors.toList());

        return airlines;
    }
    @GetMapping("/airports-list")
    @ResponseBody
    public List<AirPort> airportList(Model model){
       airPortService.deleteDuplicateItems();
        List<AirPort> airports = airPortService.findAllAirports()
                                                .stream()
                                                .sorted(Comparator.comparing(AirPort::getIataApCode))
                                                .collect(Collectors.toList());

        return airports;
    }


}
