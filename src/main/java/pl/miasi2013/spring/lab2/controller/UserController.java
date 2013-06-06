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

import pl.miasi2013.spring.lab2.model.Book;
import pl.miasi2013.spring.lab2.model.User;
import pl.miasi2013.spring.lab2.service.BookService;
import pl.miasi2013.spring.lab2.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String initEditProfile(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName(); 
		try{
			User user = userService.getUserByUsername(name);
			//user.setName(name);
			model.addAttribute("user", user);
			return "profile";
		}
		catch(UsernameNotFoundException e){
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "users/profile", method = RequestMethod.POST)
	public String updateQueue(@ModelAttribute("user") User user,
			BindingResult result) {
		/*
		 * if (!UserService.isQueueValid(queue,result)) { return "profile"; }
		 */
		// userService.updateQueue(queue);
		return "redirect:/";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getAllUsers(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "usersList";
	}
	
	@RequestMapping(value = "/users/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("user", new User());
		return "createOrUpdateUserForm";
	}

	@RequestMapping(value = "/users/new", method = RequestMethod.POST)
	public String processAddBook(@ModelAttribute("user") User user,
			BindingResult result) {
		if (!UserService.isUserValid(user,result)) {
			return "createOrUpdateUserForm";
		}
		userService.insertUser(user);
		return "redirect:/users";
	}
	@RequestMapping(value = "/users/edit/{userId}", method = RequestMethod.GET)
	public String initEditBook(@PathVariable("userId") long userId, Model model) {
		model.addAttribute("user", userService.getUserById(userId));		
		return "createOrUpdateUserForm";
	}
	
	@RequestMapping(value = "/users/edit/{userId}", method = RequestMethod.PUT)
	public String updateBook(@ModelAttribute("user") User user,BindingResult result) {
		if (!UserService.isUserValid(user,result)) {
			return "createOrUpdateUserForm";
		}
		userService.updateUser(user);
		return "redirect:/users";		
	}
}
