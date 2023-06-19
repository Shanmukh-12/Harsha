package main.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import main.dao.adjustments.AdjustmentsDAO;
import main.models.adjustmentModels.Adjustments; 


@Controller
public class AdjustmentsController {
	
	@Autowired
	public AdjustmentsDAO a;
	
	 @GetMapping("/adjustments")
	    public String showDataPage(Model model) {
	       
	        Adjustments adjustments = a.getAdjustments();
	        model.addAttribute("adjustments", adjustments);
	        return "inventory/adjustments"; 
	    }

	    @PostMapping("/adjustments")
	    public String updateData(Adjustments adjustments) {
	        
	        a.updateAdjustments(adjustments);
	        return "inventory/adjustments"; 
	}

}
