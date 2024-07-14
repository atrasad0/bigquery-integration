package br.com.bigquery.system.controller;

import br.com.bigquery.system.models.to.DataIntegrationLogTO;
import br.com.bigquery.system.service.UsaNameService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usa-names")
public class UsaNameController {
    private final UsaNameService usaNameService;

    @PostMapping("/start-integration")
    public ResponseEntity<DataIntegrationLogTO> extract() {
        val dataLog = usaNameService.processUsaNamesDataInegration();
        return ResponseEntity.ok(dataLog);
    }
}
