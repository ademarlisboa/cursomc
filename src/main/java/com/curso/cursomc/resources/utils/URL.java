package com.curso.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
			}
	}
	
	public static List<Integer> decodeIntlist(String S){
		String [] vet  = S.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length ;i++) {
			Integer.parseInt(vet[i]);
		}
		return list;
	}

}
