package com.curso.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.curso.cursomc.domain.PagamentoComBoleto;
@Service
public class BoletoService {

	public static void prencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante) {
		// Adiciona data de vencimento boleto para 7 dias da data atual
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}

}
