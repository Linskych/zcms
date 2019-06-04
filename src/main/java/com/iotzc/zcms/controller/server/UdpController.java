package com.iotzc.zcms.controller.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RequestMapping("/server/udp")
@RestController
@Slf4j(topic="server")
public class UdpController {

    @RequestMapping("/push_charge_end")
    public String pushChargeEnd () {
        log.info("push_charge_end....");
        return "";
    }
}
