package com.curso.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.dto.ClienteDto;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator  implements ConstraintValidator<ClienteUpdate, ClienteDto> {
	@Autowired
	private ClienteRepository repo;
	@Autowired 
	private HttpServletRequest request; 
	
	public void initialize(ClienteUpdate ann) {
		
	}

	@Override
	public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		List<FieldMessage> list = new ArrayList<>();
		
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		
		Integer idURI = Integer.parseInt(map.get("id"));
		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if (aux != null) {
			if(!aux.getId().equals(idURI) ) {
				list.add(new FieldMessage("email","email já existente!"));
			}
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}
