package br.com.sp.turisticopoint.turisticopoint.util;

import java.io.IOException;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class FirebaseUtil {
	// declara as variaveis que a gente usa pra se conectar com o firebase
	private Credentials credenciais;
	// variavel para acessar e manipular o storage
	private Storage storage;
	// constante para o nome do bucket
	private final String BUCKET_NAME = "turisticopoint-bruno.appspot.com";
	// constatnte para o prefixo da URL
	private final String PREFIX = "https://firebasestorage.googleapis.com/v0/b/"+BUCKET_NAME+"/o/";
	// constante para o sufixo da URL
	private final String SUFFIX = "?alt=media";
	// constante para minha URL inteira
	private final String DOWNLOAD_URL = PREFIX + "%s" + SUFFIX;
	
	public FirebaseUtil() {
		// acessar o arquivo JSON com a chave privada
		Resource resource = new ClassPathResource("chave-firebase.json");
		// gera uma credencial no firebase, atráves da chave do arquivo
		try {
			credenciais = GoogleCredentials .fromStream(resource.getInputStream());
			// cria o storage para manipular os dados no firebase
			storage = StorageOptions.newBuilder().setCredentials(credenciais).build().getService();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	// método para extrair a extensão do arquivo
	private String getExtensao(String nomeArquivo) {
		// extrai o trecho do arquivo onde está a extensão
		return nomeArquivo.substring(nomeArquivo.lastIndexOf("."));
	}
	
	// método que faz o upload
	public String upload(MultipartFile arquivo) {
		// gera um nome aleatorio para o arquivo
		String nomeArquivo = UUID.randomUUID().toString() + getExtensao(arquivo.getOriginalFilename());
		return "";
	}
}
