package com.meli.mutant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.mutant.dto.StadisticsDto;
import com.meli.mutant.services.StadisticsService;

@RestController
@RequestMapping
public class StatsController {
	
	@Autowired
	private StadisticsService stadisticsService;
	
	@GetMapping("/stats")
	public StadisticsDto getStadistics() {
		return stadisticsService.mutantStadistics();
	}

}
