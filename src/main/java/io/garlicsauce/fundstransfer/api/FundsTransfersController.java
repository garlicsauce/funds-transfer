package io.garlicsauce.fundstransfer.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/fundsTransfers")
public class FundsTransfersController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void transferFunds(@RequestBody @Valid FundsTransferRequest request) {
        log.info("Received funds transfer request {}", request);
    }
}
