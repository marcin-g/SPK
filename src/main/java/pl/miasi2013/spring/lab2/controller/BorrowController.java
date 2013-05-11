package pl.miasi2013.spring.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.miasi2013.spring.lab2.model.relations.Borrow;
import pl.miasi2013.spring.lab2.service.BorrowService;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
	@Autowired
	private BorrowService borrowService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getAllBorrows(Model model) {
		model.addAttribute("borrows", borrowService.getAllBorrows());
		return "borrowsList";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("borrow", new Borrow());
		return "createOrUpdateBorrowForm";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processAddBorrow(@ModelAttribute("borrow") Borrow borrow,
			BindingResult result) {
		if (!BorrowService.isBorrowValid(borrow,result)) {
			return "createOrUpdateBorrowForm";
		}
		borrowService.insertBorrow(borrow);
		return "redirect:/borrows";
	}
	
	@RequestMapping(value = "/{borrowId}", method = RequestMethod.GET)
	public String initEditBorrow(@PathVariable("borrowId") long borrowId, Model model) {
		model.addAttribute("borrow", borrowService.getBorrowById(borrowId));		
		return "createOrUpdateBorrowForm";
	}
	
	@RequestMapping(value = "/{borrowId}", method = RequestMethod.PUT)
	public String updateBorrow(@ModelAttribute("borrow") Borrow borrow,BindingResult result) {
		if (!BorrowService.isBorrowValid(borrow,result)) {
			return "createOrUpdateBorrowForm";
		}
		borrowService.updateBorrow(borrow);
		return "redirect:/borrows";		
	}
	
	@RequestMapping(value = "/{borrowId}", method = RequestMethod.DELETE)
	public String deleteBorrow(@PathVariable("borrowId") long borrowId) {
		borrowService.deleteBorrowById(borrowId);
		return "redirect:/borrows";		
	}

	
}
