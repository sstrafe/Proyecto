package com.cibertec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.app.entity.Rol;
import com.cibertec.app.entity.Usuario;
import com.cibertec.app.service.RolService;
import com.cibertec.app.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;


	@Autowired
	private RolService rolService;

	@GetMapping("/")
    public String login(){
        return "login";
    }
	
	@GetMapping("/register")
    public String showRegistrationForm(Model model){
        Usuario userDto = new Usuario();
        model.addAttribute("usuario",userDto); 
        return "register";
    }
	

	  @PostMapping("/register/save")
	    public String registration(@ModelAttribute("usuario") Usuario usuario, BindingResult result,Model model){ 


	        Usuario existingUser = usuarioService.buscarByUsuario(usuario.getUsername()); 
             if(usuario.getNombres()==null || usuario.getNombres().isEmpty()){
	            result.rejectValue("nombres",null,"Ingresar nombres");
	        }
	        
             if(usuario.getApellidos()==null || usuario.getApellidos().isEmpty()){
 	            result.rejectValue("apellidos",null,"Ingresar apellidos");
 	        }
	        
             if(usuario.getUsername()==null || usuario.getUsername().isEmpty()){
  	            result.rejectValue("username",null,"Ingresar username");
  	        }
             
             if(usuario.getClave()==null || usuario.getClave().isEmpty()){
  	            result.rejectValue("clave",null,"Ingresar clave");
  	        }
             
             
	        if(existingUser!=null && existingUser.getUsername()!=null && !existingUser.getUsername().isEmpty()){
	            result.rejectValue("username",null,"Ya existe una cuenta con este usuario");
	        }

	        if(result.hasErrors()){
	            model.addAttribute("usuario",usuario);
	            return "/register"; 
	        }

	        
	        usuario.setRol(rolService.buscarById(2));
	        usuarioService.guardarUsuario(usuario);
	        return "redirect:/register?success"; 

	    }
	
	/*@PostMapping("/login")
	public String iniciarSesion(Model model,@ModelAttribute("usuario") Usuario usuario) {
	
		boolean band=usuarioService.login(usuario);
		System.out.println("bandd--> "+band);
		
		if(band==true) {
			   model.addAttribute("usuarios", usuarioService.listarTodosUsuario());
			   model.addAttribute("rolList", rolService.listarTodosRol());
			
			   return   "usuario/index";
			
			
		}else {
			return "error_login";
		}
		
		
	}*/
	  
	  
	  
	  @PostMapping("/login")
	  public String iniciarSesion(Model model, @ModelAttribute("usuario") Usuario usuario) {
	      Usuario entidad = usuarioService.validarCredenciales(usuario.getUsername(), usuario.getClave());
	      
	      if (entidad != null) {
	          String rolDescripcion = entidad.getRol().getDescripcion(); // debería ser "vendedor"
	          
	          if ("admin".equalsIgnoreCase(rolDescripcion)) {
	              return "redirect:administrador/usuario"; // Redirige a vista del rol vendedor
	          } else if ("vendedor".equalsIgnoreCase(rolDescripcion)) {
	              return "redirect:vendedor/cliente";
	          }else if("cliente".equalsIgnoreCase(rolDescripcion)) {
            	  return "redirect:cliente/home";
	          } else {  
	              model.addAttribute("usuarios", usuarioService.listarTodosUsuario());
	              model.addAttribute("rolList", rolService.listarTodosRol());
	              return "error_rol";
	          }
	      } else {
	          return "error_login";
	      }
	  }

	  
	  
	
	 @GetMapping("administrador/usuario/new")
	    public String createUsuarioForm(Model model){
	        
	       
	    	Usuario usuario = new Usuario();
	       
	        model.addAttribute("usuario", usuario);
	        model.addAttribute("rolList", rolService.listarTodosRol());
	       
	        return "administrador/usuario/create";
	     
	    }
	
	 
	 @PostMapping("/usuario")
	    public String saveUsuario(@ModelAttribute("usuario") Usuario usuario) {
		 usuarioService.guardarUsuario(usuario);
	        return "redirect:/usuario";
	    }
	 

	    @GetMapping("/administrador/usuario")
	    public String listUsuarios(Model model) {
	        model.addAttribute("usuarios", usuarioService.listarTodosUsuario());
	        model.addAttribute("rolList", rolService.listarTodosRol());
	       
	        
	        return "administrador/usuario/index";
	    }
	
}