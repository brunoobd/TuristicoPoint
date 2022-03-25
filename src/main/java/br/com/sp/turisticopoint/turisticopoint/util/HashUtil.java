package br.com.sp.turisticopoint.turisticopoint.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class HashUtil {
	public static String hash(String palavra) {
		// "tempero" do hash, palavra que vamos colocar junto com a senha para depois aplicar o hash
		String salt = "s@mb@l3l3";
		// adicionar o "tempero" a palavra
		palavra = salt + palavra;
		// gera o hash
		String hash = Hashing.sha512().hashString(palavra, StandardCharsets.UTF_8).toString();
		// retorna o hash
		return hash;
	}
}
