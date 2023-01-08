/*
 * Procesador de textos b�sico
 * 
 * Contiene tres men�s para el cambio de letra, tama�o y estilo del texto seleccionado.
 * 
 * Aprende Java desde cero (https://empezandojava.blogspot.com)
 * 
 * @author Amparo Izquierdo Ba�ez
 * 
 * @mail amizba@gmail.com
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;

public class ProcesadorTexto {

	public static void main(String[] args) {
		
		MarcoProcesador mimarco = new MarcoProcesador(); //Instanciamos la clase MarcoProcesador para su ejecuci�n
		
		//Al pulsar el bot�n cerrar de la ventana la aplicaci�n finaliza su ejecuci�n
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
 class MarcoProcesador extends JFrame { //Hereda de la clase JFram
	
	public MarcoProcesador(){ //Constructor
		
		setVisible(true); //Ventana visible
		
		setBounds(550, 300, 550, 400); //Tama�o de la ventana
		
		LaminaProcesador milamina = new LaminaProcesador(); //Instanciamos la clase LaminaProcesador
		
		add(milamina); //A�adimos la l�mina al marco

		
	}
}
 class LaminaProcesador extends JPanel {
	 
	 public LaminaProcesador() {
		 
		 setLayout(new BorderLayout()); //Disposici�n de la l�mina
		 
		 JPanel laminamenu = new JPanel(); //Creamos otra l�mina
		 
		 //-------------MEN�S--------------------
		 
		 JMenuBar barra = new JMenuBar();
		 
		 fuente = new JMenu("Fuente");
		 
		 estilo = new JMenu("Estilo");
		 
		 tama�o = new JMenu("Tama�o");
		 
		 //--------Tipo letra--------------------
		 
		 //El �ltimo par�metro es para a�adir una ruta por si queremos inserta una im�gen en el submen�
		 ConfiguraMenu("Arial", "fuente", "Arial", 9, 10, "");
		 
		 ConfiguraMenu("Courier", "fuente", "Courier", 9, 10, "");
		 
		 ConfiguraMenu("Verdana", "fuente", "Verdana", 9, 10, "");
		 
		 //--------Formato-------------------
		 
		 //A�adimos el �ltimo par�metro para a�adir una im�gen al submen�
		 ConfiguraMenu("Negrita", "estilo", "", Font.BOLD, 1, "bin/negrita.gif");
		 
		 ConfiguraMenu("Cursiva", "estilo", "", Font.ITALIC, 1, "bin/cursiva.gif");
		 
		 //--------Tama�o letra-----------------
		 
		 //El �ltimo par�metro es para a�adir una ruta por si queremos inserta una im�gen en el submen�
		 ConfiguraMenu("12", "tama�o", "", 9, 12, "");
		 
		 ConfiguraMenu("16", "tama�o", "", 9, 16, "");
		 
		 ConfiguraMenu("20", "tama�o", "", 9, 20, "");
		 
		 ConfiguraMenu("24", "tama�o", "", 9, 24, "");
		 
		 
		 barra.add(fuente); //A�adimos los menus a la barra de menus
		 
		 barra.add(estilo);
		 
		 barra.add(tama�o);
		 
		 laminamenu.add(barra); //A�adimos la barra al men�
		//Indicamos que la barra de men�s va en la parte superior de la ventana
		 add(laminamenu, BorderLayout.NORTH); 
		 
		 area = new JTextPane(); //Creamos el �rea de texto
		 
		 add(area, BorderLayout.CENTER); //A�adimos el �rea de texto en la parte central
		 
		 JPopupMenu emergente = new JPopupMenu(); //A�adimos un men� emergente
		 
		 JMenuItem negritaE = new JMenuItem("Negrita"); //A�adimos �tem al men� emergente
		 
		 JMenuItem cursivaE = new JMenuItem("Cursiva");
		 
		 negritaE.addActionListener(new StyledEditorKit.BoldAction());
		 
		 cursivaE.addActionListener(new StyledEditorKit.ItalicAction());
		 
		 emergente.add(negritaE); //A�adimos el �tem al men� emergente
		 
		 emergente.add(cursivaE);
		//al hacer clic con el bot�n secundario del rat�n sobre el �rea de texto aparecer� el men� emergente
		 area.setComponentPopupMenu(emergente); 

	 }
	 
	 //M�todo que crea los men�s y gestiona los eventos
	 public void ConfiguraMenu(String rotulo, String menu, String tipoletra, int estilos, int tam, String ruta_icono) {
		 JMenuItem elementoMenu = new JMenuItem(rotulo, new ImageIcon(ruta_icono)); 
		 
		 if(menu=="fuente") { 
			 
			 fuente.add(elementoMenu);
			 
			 if(tipoletra=="Arial") { 
				//Con la clase StyledEditorKit conseguimos que se cambiar las caracter�sticas solo del texto seleccionado
				 //El par�metro "cambia_letra" no hace nada es un nombre identificativo de lo que hace la acci�n
				 elementoMenu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Arial"));
				 
			 }else if(tipoletra=="Courier") {
				 elementoMenu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Courier"));

				 
			 }else if(tipoletra=="Verdana") {
				 
				 elementoMenu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Verdana"));

			 }
		 }else if(menu=="estilo") {
			 
			 estilo.add(elementoMenu);
			 
			 if(estilos==Font.BOLD) {
				 
				 elementoMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));//Combinaci�n de teclas
				 
				 elementoMenu.addActionListener(new StyledEditorKit.BoldAction());
			 }else if(estilos==Font.ITALIC) {
				 elementoMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
				 elementoMenu.addActionListener(new StyledEditorKit.ItalicAction());
			 }
			 
			
			 }else if(menu=="tama�o") {
				 
				 tama�o.add(elementoMenu);
				 
				 //La clase StyledEditorKit nos pide dos par�metros, uno es un nombre identificatio que es lo que hace
				 //y el segundo es la variable tam, para establecer el tama�o del texto
				 elementoMenu.addActionListener(new StyledEditorKit.FontSizeAction("cambia_tama�o", tam));
			 }
		 
		 }
	 
	 JTextPane area;
	 
	 JMenu fuente, estilo, tama�o;
	
 }
