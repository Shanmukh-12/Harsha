package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import main.dao.indents.IndentsDao;
import main.models.indentModels.Indents;



@Controller
public class IndentsController {

	@Autowired
	public IndentsDao p;


	@GetMapping("/createIndent")
	public String showCreateForm(Model model) {
		// model.addAttribute("vendor", new Vendor());
		return "inventory/createIndent";
	}

	@GetMapping("/saveIndent")
	public String saveVendor(Indents indent, Model model) {
		p.createIndent(indent);
		return "/addIndent";
	}

	@GetMapping("/indentsButton")
	public String createVendor(Model m) {
		m.addAttribute("indents", p.getAllIndents());
		return "inventory/indents";
	}

}