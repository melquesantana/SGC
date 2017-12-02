package br.com.JPAUtil;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.Bean.AutenticarUsuarioBean;
import br.com.modelo.Usuario;

@SuppressWarnings("serial")
public class Listener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
 
		 String paginaAtual =Faces.getViewId();
		 
		 boolean ePaginaLogin = paginaAtual.contains("login.xhtml");
		 
		 if (!ePaginaLogin) {
			 AutenticarUsuarioBean autenticarUsuarioBean= Faces.getSessionAttribute("autenticarUsuarioBean");
			 
			 if (autenticarUsuarioBean == null) {
				Faces.navigate("/paginas/login.xhtml");
				return;
			}
			Usuario usuario = autenticarUsuarioBean.getUsuarioLogado();
			 if (usuario == null) {
				Faces.navigate("/paginas/login.xhtml");
				return;
			}
			 
		}
	

	}

	@Override
	public void beforePhase(PhaseEvent event) {
	//	System.out.println("antes da fase " + event.getPhaseId());
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.ANY_PHASE;
	}

}
