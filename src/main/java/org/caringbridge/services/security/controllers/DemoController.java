package org.caringbridge.services.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling the request by mapping url.
 * 
 * @author Simi George
 *
 */
@RestController
public class DemoController {

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(path = "/hello", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResponseEntity<String> hello(
	    @RequestParam(name = "name", required = false) String name) {

	return ResponseEntity.ok("Hello " + name);
    }

}
