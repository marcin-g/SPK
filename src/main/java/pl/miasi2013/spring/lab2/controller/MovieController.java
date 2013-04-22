package pl.miasi2013.spring.lab2.controller;

// git-test

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.miasi2013.spring.lab2.model.Movie;
import pl.miasi2013.spring.lab2.service.MovieService;

@Controller
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	private MovieService movieService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllMovies(Model model) {
		model.addAttribute("movies", movieService.getAllMovies());
		return "moviesList";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String initCreationForm(Model model) {
		model.addAttribute("movie", new Movie());
		return "createOrUpdateMovieForm";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String processAddMovie(@ModelAttribute("movie") Movie movie,
			BindingResult result) {
		if (!StringUtils.hasLength(movie.getTitle())) {
			result.rejectValue("title", "required", "required");
			return "createOrUpdateMovieForm";
		}
		movieService.addMovie(movie);
		return "redirect:/movies";
	}
	
	@RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
	public String initEditMovie(@PathVariable("movieId") long movieId, Model model) {
		model.addAttribute("movie", movieService.getMovieById(movieId));		
		return "createOrUpdateMovieForm";
	}
	
	@RequestMapping(value = "/{movieId}", method = RequestMethod.PUT)
	public String updateMovie(@ModelAttribute("movie") Movie movie,BindingResult result) {
		if (!StringUtils.hasLength(movie.getTitle())) {
			result.rejectValue("title", "required", "required");
			return "createOrUpdateMovieForm";
		}
		movieService.updateMovie(movie);
		return "redirect:/movies";		
	}
	
	@RequestMapping(value = "/{movieId}", method = RequestMethod.DELETE)
	public String deleteMovie(@PathVariable("movieId") long movieId) {
		movieService.deleteMovie(movieId);
		return "redirect:/movies";		
	}
}
