package com.curso.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.curso.cursomc.domain.Cliente;
import com.curso.cursomc.domain.enums.TipoCliente;
import com.curso.cursomc.dto.ClienteNewDto;
import com.curso.cursomc.repositories.ClienteRepository;
import com.curso.cursomc.resources.exception.FieldMessage;
import com.curso.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
	
	@Autowired
	private ClienteRepository repo;
	
	public void initialize(ClienteInsert ann) {
		
	}

	@Override
	public boolean isValid(ClienteNewDto objDto, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCpf(objDto.getCpfouCnpj()))  {
			list.add(new FieldMessage("CpfouCnpj","CPF inválido"));
		}
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCnpj(objDto.getCpfouCnpj()))  {
			list.add(new FieldMessage("CpfouCnpj","CNPJ inválido"));
		}
		
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email","email já existente!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
