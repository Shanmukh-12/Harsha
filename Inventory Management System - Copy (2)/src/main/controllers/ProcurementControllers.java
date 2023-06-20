package main.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import main.bll.login.PasswordChangeBLL;
import main.bll.login.RandomNumberBLL;
import main.bll.login.SendingEmailBLL;
import main.models.loginModel.inputModels.MailDetails;
import main.models.loginModel.inputModels.ModelData;
import main.models.loginModel.inputModels.credentials2;
import main.models.loginModel.inputModels.password;
import main.service.procurement.ProcurementService;

@Controller
public class ProcurementControllers {

	@Autowired
	ProcurementService x;
	@Autowired
	RandomNumberBLL rn;
	@Autowired
	SendingEmailBLL sm;
	@Autowired
	PasswordChangeBLL cp;
	int i = 0;

	@GetMapping("/HomeProcurement")
	public String getIndex() {
		return "procurement/HomeProcurement";
	}

	@GetMapping("/PurchasesList")
	public String getPurchasesList() {
		return "procurement/PurchasesList";
	}

	@GetMapping("/grnData")
	public String getGrn() {
		return "procurement/grnlist";
	}

	@GetMapping("/createGRN")
	public String createGRN() {
		return "procurement/create-grn";
	}

	@GetMapping("/purchaseOrderData")
	public String purchaseOrderData() {
		return "procurement/PurchasesList";
	}

	@GetMapping("/createPurchaseOrder")
	public String createPurchaseOrder() {
		return "procurement/purchase orders";
	}

	@GetMapping("/createNewPurchaseOrders")
	public String createNewPurchaseOrders() {
		return "procurement/purchase orders from scratch";
	}

	@GetMapping("/indents")
	public String indents() {
		return "procurement/indents";
	}

	@GetMapping("/prnData")
	public String prnData() {
		return "procurement/PurchaseReturnsList";
	}

	@GetMapping("/prnData2")
	public String prnData2() {
		return "procurement/PurchaseReturnsList";
	}

	@GetMapping("/createPRN")
	public String createPRN() {
		return "procurement/createprn";
	}

	@GetMapping("/addHSN")
	public String addHSN() {
		return "procurement/addHSN";
	}

	@GetMapping("/addProductCategory")
	public String addProductCategory() {
		return "procurement/addProductCategory";
	}

	@GetMapping("/addProduct")
	public String addProduct() {
		return "procurement/addProduct";
	}

	@GetMapping("/warehousestock")
	public String warehouse() {
		return "procurement/Warehouse";
	}



}