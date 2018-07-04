package br.edu.granbery.trabalhomodular.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.granbery.trabalhomodular.swing.screen.About;
import br.edu.granbery.trabalhomodular.swing.screen.Homepage;
import br.edu.granbery.trabalhomodular.swing.screen.Indicators;
import br.edu.granbery.trabalhomodular.swing.screen.Registration;

public class MenuAction {
	
	public ActionListener addNotaFiscal() {
		ActionListener newNotaFiscal = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration registrationPage = new Registration();
				registrationPage.render();
			}
		};
		return newNotaFiscal;
	}
	
	public ActionListener toHomepage() {
		ActionListener homepage = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Homepage homepage = new Homepage();
				homepage.render();
			}
			
		};
		return homepage;
	}
	
	public ActionListener toAboutPage() {
		ActionListener about = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About aboutPage = new About();
				aboutPage.render();
			}
		};
		return about;
	}
	
	public ActionListener toIndicators() {
		ActionListener indicators = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Indicators indicators = new Indicators();
				indicators.render();
			}
		};
		return indicators;
	}

}
