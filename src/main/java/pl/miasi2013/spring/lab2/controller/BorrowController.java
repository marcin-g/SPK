package pl.miasi2013.spring.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.miasi2013.spring.lab2.model.User;
import pl.miasi2013.spring.lab2.model.relations.Borrow;
import pl.miasi2013.spring.lab2.service.BorrowService;
import pl.miasi2013.spring.lab2.service.UserService;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllUserBorrows(Model model) {
		try {
			User user = userService.getLoggedUser();
			model.addAttribute("borrows",
					borrowService.getUserBorrowsWithBooks(user));
			return "borrowsList";
		} catch (UsernameNotFoundException e) {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("borrow", new Borrow());
		return "createOrUpdateBorrowForm";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processAddBorrow(@ModelAttribute("borrow") Borrow borrow,
			BindingResult result) {
		if (!BorrowService.isBorrowValid(borrow, result)) {
			return "createOrUpdateBorrowForm";
		}
		borrowService.insertBorrow(borrow);
		return "redirect:/borrows";
	}

	@RequestMapping(value = "/{borrowId}", method = RequestMethod.GET)
	public String initEditBorrow(@PathVariable("borrowId") long borrowId,
			Model model) {
		model.addAttribute("borrow", borrowService.getBorrowById(borrowId));
		return "createOrUpdateBorrowForm";
	}

	@RequestMapping(value = "/{borrowId}", method = RequestMethod.PUT)
	public String updateBorrow(@ModelAttribute("borrow") Borrow borrow,
			BindingResult result) {
		if (!BorrowService.isBorrowValid(borrow, result)) {
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

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public String getUserHistory(@PathVariable("userId") long userId,
			Model model) {
		model.addAttribute("borrows", borrowService
				.getBorrowsWithBooks(borrowService.getUserBorrows(userId)));
		return "borrowsList";
	}

	@RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
	public String getBookHistory(@PathVariable("bookId") long bookId,
			Model model) {
		model.addAttribute("borrows", borrowService
				.getBorrowsWithBooks(borrowService.getBorrowsByBookId(bookId)));
		return "borrowsList";
	}

}
