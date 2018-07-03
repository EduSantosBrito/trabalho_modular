package br.edu.granbery.trabalhomodular.swing.component;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.edu.granbery.trabalhomodular.swing.action.MenuAction;

public class Menubar {
	public JMenuBar build() {
		MenuAction menuAction = new MenuAction();
		JMenuItem exitItem = buildExitItem();
		JMenuItem homepageItem = buildHomepageItem();
		JMenuItem indicatorsItem = buildIndicatorsItem();
		JMenuItem addNotaFiscalItem = buildAddNotaFiscalItem();
		JMenuItem aboutItem = buildAboutItem();
		
		JMenu fileMenu = buildFileMenu(exitItem, homepageItem, indicatorsItem);
		JMenu newMenu = buildNewMenu(addNotaFiscalItem);
		JMenu aboutMenu = buildAboutMenu(aboutItem);
		
		addActionListener(menuAction, homepageItem, addNotaFiscalItem, aboutItem);
		
		return buildMenubar(fileMenu, newMenu, aboutMenu);
	}

	private void addActionListener(MenuAction menuAction, JMenuItem homepageItem, JMenuItem addNotaFiscalItem,
			JMenuItem aboutItem) {
		addNotaFiscalItem.addActionListener(menuAction.addNotaFiscal());
		homepageItem.addActionListener(menuAction.toHomepage());
		aboutItem.addActionListener(menuAction.toAboutPage());
	}

	private JMenuBar buildMenubar(JMenu fileMenu, JMenu newMenu, JMenu aboutMenu) {
		JMenuBar menubar = new JMenuBar();
		menubar.add(fileMenu);
		menubar.add(newMenu);
		menubar.add(aboutMenu);
		return menubar;
	}

	private JMenu buildAboutMenu(JMenuItem aboutItem) {
		JMenu aboutMenu = new JMenu("Sobre");
		aboutMenu.setMnemonic(KeyEvent.VK_S);
		aboutMenu.add(aboutItem);
		return aboutMenu;
	}

	private JMenu buildNewMenu(JMenuItem addNotaFiscal) {
		JMenu newMenu = new JMenu("Cadastro");
		newMenu.setMnemonic(KeyEvent.VK_I);
		newMenu.add(addNotaFiscal);
		return newMenu;
	}

	private JMenu buildFileMenu(JMenuItem exit, JMenuItem homepage, JMenuItem indicators) {
		JMenu fileMenu = new JMenu("Arquivo");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.add(homepage);
		fileMenu.add(indicators);
		fileMenu.add(exit);
		return fileMenu;
	}

	private JMenuItem buildAboutItem() {
		JMenuItem about = new JMenuItem("Sobre");
		about.setToolTipText("Navegar para Tela Sobre");
		return about;
	}

	private JMenuItem buildAddNotaFiscalItem() {
		JMenuItem addNotaFiscal = new JMenuItem("Incluir Nota Fiscal");
		addNotaFiscal.setMnemonic(KeyEvent.VK_N);
		addNotaFiscal.setToolTipText("Incluir nova Nota Fiscal");
		return addNotaFiscal;
	}

	private JMenuItem buildIndicatorsItem() {
		JMenuItem indicators = new JMenuItem("Tela Indicadores");
		indicators.setMnemonic(KeyEvent.VK_I);
		indicators.setToolTipText("Navegar para Tela de Indicadores");
		return indicators;
	}

	private JMenuItem buildHomepageItem() {
		JMenuItem homepage = new JMenuItem("Tela Principal");
		homepage.setMnemonic(KeyEvent.VK_T);
		homepage.setToolTipText("Navegar para Tela Principal");
		return homepage;
	}

	private JMenuItem buildExitItem() {
		JMenuItem exit = new JMenuItem("Sair");
		exit.setMnemonic(KeyEvent.VK_S);
		exit.setToolTipText("Sair da Aplicação");
		exit.addActionListener((ActionEvent event) -> {
		    System.exit(0);
		});
		return exit;
	}
}
