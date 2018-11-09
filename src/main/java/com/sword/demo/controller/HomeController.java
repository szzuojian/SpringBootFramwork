package com.sword.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sword.demo.entity.Msg;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "123123123123");
		model.addAttribute("msg", msg);
		return "index";
	}

	@RequestMapping(value = "/admin/test1")
	@ResponseBody
	public String adminTest1() {
		return "adminTest1";
	}

	@RequestMapping("/admin/test2")
	@ResponseBody
	public String adminTest2() {
		return "adminTest2";
	}
}
