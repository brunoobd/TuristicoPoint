package br.com.sp.turisticopoint.turisticopoint.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.sp.turisticopoint.turisticopoint.annotation.Publico;

@Component
public class AppInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// variaval para obter a URI da request
		String uri = request.getRequestURI();
		// variavel para a sessão
		HttpSession session = request.getSession();
		
		
		
		// se for pagina de erro, libera
		if (uri.startsWith("/error")) {
			return true;
		}
		
		// verificar se handler é um HandlerMethod,
		// o que indica que ele está chamando algum método em algum controller
		
		if (handler instanceof HandlerMethod) {
			// castring de Object para HandlerMethod
			HandlerMethod metodo = (HandlerMethod) handler;
			
			if (uri.startsWith("/api")) {

				return true;
				}else {

					// verifica se este método é publico
					if (metodo.getMethodAnnotation(Publico.class) != null) {
						return true;
					}
					// verifica se existe um usuario logado
					if (session.getAttribute("usuarioLogado") != null) {
						return true;
					}
					// redireciona para a pagina inicial
					response.sendRedirect("/");
					return false;
				}	
		}
		
		return true;
	}
	
}
