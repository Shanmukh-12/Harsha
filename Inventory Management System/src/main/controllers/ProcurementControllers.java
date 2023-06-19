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
	HashMap<String, ModelData> modelattr = new HashMap<String, ModelData>();

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

	@RequestMapping(value = "/forgotrequest", method = RequestMethod.GET)
	public String request() {
		return "login/OtpReq";
	}

	@RequestMapping(value = "/changepass", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String changepass(password p) {
		String t = cp.changePassword(p);
		return t;

	}

	@RequestMapping(value = "/sendotp", method = RequestMethod.GET)
	public String sendmail(MailDetails md, Model m) throws MessagingException {
		i++;
		System.out.println(i);
		m.addAttribute("mail", md.getMail());
		sm.sendEmail(md);

		return "login/ChangePassword";

	}

	public ProcurementControllers() {

	}

	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String check(MailDetails md, Model m) throws MessagingException {
		System.out.println("hello");
		System.out.println("mail" + md.getMail());
		return String.valueOf(x.check(md));

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String login(credentials2 md, Model m) throws MessagingException {
		System.out.println("hello");
		System.out.println(x.getAuthent(md));
		return x.getAuthent(md);

	}

	@Scheduled(fixedDelay = 100)
	public void removeExpiredAttributes() {
		LocalDateTime currentTime = LocalDateTime.now();

		for (String attributeName : modelattr.keySet()) {
			ModelData attributeData = modelattr.get(attributeName);
			if (currentTime.isAfter(attributeData.getExpire())) {
				modelattr.remove(attributeName);
				System.out.println(attributeName + "deleted");
			}
		}
	}
}