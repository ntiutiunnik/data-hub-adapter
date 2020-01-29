package com.sandbox.lamadatahubadapter.controller;

import com.sandbox.lamadatahubadapter.service.DumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DumpController {

  private final DumpService dumpService;

  @Autowired
  public DumpController(DumpService dumpService) {
    this.dumpService = dumpService;
  }

  @GetMapping("dump/all")
  public @ResponseBody
  String dumpAll() {
    dumpService.dumpAll();
    return "Ok";
  }
}
