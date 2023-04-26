package com.bayua.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bayua.demo.entity.M_EMPLOYEE;
import com.bayua.demo.entity.M_POSITION;
import com.bayua.demo.entity.V_EMPLOYEE;
import com.bayua.demo.service.DemoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DemoController {

	@Autowired
	private DemoService demoService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = { "/ListData" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		List<V_EMPLOYEE> list = demoService.getAllVEmp();
		model.addAttribute("list", list);
		return "listData";
	}

	@RequestMapping(value = { "/Create" }, method = { RequestMethod.GET })
	public String create(Model model) {
		List<M_POSITION> position = demoService.getListPosition();
		model.addAttribute("position", position);
		return "create";
	}

	@RequestMapping(value = { "/Create/Action" }, method = { RequestMethod.POST })
	public String createAction(Model model, HttpServletRequest request) throws Throwable {
		String name = request.getParameter("name");
		String positionId = request.getParameter("position");
		String gender = request.getParameter("gender");
		String birthDate = request.getParameter("birthDate");
		String idNumber = request.getParameter("nip");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		M_EMPLOYEE emp = new M_EMPLOYEE();
		emp.setName(name);
		emp.setGender(Integer.parseInt(gender));
		emp.setBirth_date(sdf.parse(birthDate));
		emp.setIdNumber(Integer.parseInt(idNumber));
		emp.setIsDelete(0);
		emp.setPositionId(Integer.parseInt(positionId));
		demoService.addObject(emp);

		return "redirect:/ListData";
	}

	@RequestMapping(value = { "/Delete" }, method = { RequestMethod.GET })
	public String delete(Model model, HttpServletRequest request) throws Throwable {
		String id = request.getParameter("id");
		M_EMPLOYEE emp = new M_EMPLOYEE();
		emp = demoService.getMEmpById(id);
		emp.setIsDelete(1);
		demoService.deleteEmployee(emp);
		return "redirect:/ListData";
	}

	@RequestMapping(value = { "/Edit" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(Model model, HttpServletRequest request) {
		M_EMPLOYEE obj = new M_EMPLOYEE();
		String id = request.getParameter("id");
		obj = demoService.getMEmpById(id);
		model.addAttribute("obj", obj);

		List<M_POSITION> position = new ArrayList<M_POSITION>();
		position = demoService.getListPosition();
		model.addAttribute("position", position);
		return "edit";
	}

	@RequestMapping(value = { "/Edit/Action" }, method = { RequestMethod.POST })
	public String editAction(Model model, HttpServletRequest request) throws Throwable {
		String name = request.getParameter("name");
		String positionId = request.getParameter("position");
		System.out.println(positionId);
		String gender = request.getParameter("gender");
		String birthDate = request.getParameter("birthDate");
		String idNumber = request.getParameter("nip");
		System.out.println(birthDate);
		String id = request.getParameter("id");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		M_EMPLOYEE emp = new M_EMPLOYEE();
		emp.setId(Long.parseLong(id));
		emp.setName(name);
		emp.setGender(Integer.parseInt(gender));
		emp.setBirth_date(sdf.parse(birthDate));
		emp.setIdNumber(Integer.parseInt(idNumber));
		emp.setIsDelete(0);
		emp.setPositionId(Integer.parseInt(positionId));
		demoService.saveUpdateObject(emp);

		return "redirect:/ListData";
	}

}
