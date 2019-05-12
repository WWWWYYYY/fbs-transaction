package com.enjoy.controller;

import com.enjoy.service.GHService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/order")
public class GHController {

    private static final Logger logger = LoggerFactory.getLogger(GHController.class);

	@Autowired
	private GHService ghService;

    @ApiOperation(value="国航下单")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String doOrder(@ApiParam(value = "身份证号", required = true) @RequestParam("idcard") String idcard){
        try {
            String busId = UUID.randomUUID().toString();//流水号
            ghService.doOrder(busId,idcard);
            return "doOrder successfully";
        } catch (Exception e) {
            logger.error("下单失败",e);
            return e.getMessage();
        }
    }


}