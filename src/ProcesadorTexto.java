/*
 * Procesador de textos básico
 * 
 * Contiene tres menús para el cambio de letra, tamaño y estilo del texto seleccionado.
 * 
 * Aprende Java desde cero (https://empezandojava.blogspot.com)
 * 
 * @author Amparo Izquierdo Bañez
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
		
		MarcoProcesador mimarco = new MarcoProcesador(); //Instanciamos la clase MarcoProcesador para su ejecución
		
		//Al pulsar el botón cerrar de la ventana la aplicación finaliza su ejecución
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
 class MarcoProcesador extends JFrame { //Hereda de la clase JFram
	
	public MarcoProcesador(){ //Constructor
		
		setVisible(true); //Ventana visible
		
		setBounds(550, 300, 550, 400); //Tamaño de la ventana
		
		LaminaProcesador milamina = new LaminaProcesador(); //Instanciamos la clase LaminaProcesador
		
		add(milamina); //Añadimos la lámina al marco

		
	}
}
 class LaminaProcesador extends JPanel {
	 
	 public LaminaProcesador() {
		 
		 setLayout(new BorderLayout()); //Disposición de la lámina
		 
		 JPanel laminamenu = new JPanel(); //Creamos otra lámina
		 
		 //-------------MENÚS--------------------
		 
		 JMenuBar barra = new JMenuBar();
		 
		 fuente = new JMenu("Fuente");
		 
		 estilo = new JMenu("Estilo");
		 
		 tamaño = new JMenu("Tamaño");
		 
		 //--------Tipo letra--------------------
		 
		 //El último parámetro es para añadir una ruta por si queremos inserta una imágen en el submenú
		 ConfiguraMenu("Arial", "fuente", "Arial", 9, 10, "");
		 
		 ConfiguraMenu("Courier", "fuente", "Courier", 9, 10, "");
		 
		 ConfiguraMenu("Verdana", "fuente", "Verdana", 9, 10, "");
		 
		 //--------Formato-------------------
		 
		 //Añadimos el último parámetro para añadir una imágen al submenú
		 ConfiguraMenu("Negrita", "estilo", "", Font.BOLD, 1, "bin/negrita.gif");
		 
		 ConfiguraMenu("Cursiva", "estilo", "", Font.ITALIC, 1, "bin/cursiva.gif");
		 
		 //--------Tamaño letra-----------------
		 
		 //El último parámetro es para añadir una ruta por si queremos inserta una imágen en el submenú
		 ConfiguraMenu("12", "tamaño", "", 9, 12, "");
		 
		 ConfiguraMenu("16", "tamaño", "", 9, 16, "");
		 
		 ConfiguraMenu("20", "tamaño", "", 9, 20, "");
		 
		 ConfiguraMenu("24", "tamaño", "", 9, 24, "");
		 
		 
		 barra.add(fuente); //Añadimos los menus a la barra de menus
		 
		 barra.add(estilo);
		 
		 barra.add(tamaño);
		 
		 laminamenu.add(barra); //Añadimos la barra al menú
		//Indicamos que la barra de menús va en la parte superior de la ventana
		 add(laminamenu, BorderLayout.NORTH); 
		 
		 area = new JTextPane(); //Creamos el área de texto
		 
		 add(area, BorderLayout.CENTER); //Añadimos el área de texto en la parte central
		 
		 JPopupMenu emergente = new JPopupMenu(); //Añadimos un menú emergente
		 
		 JMenuItem negritaE = new JMenuItem("Negrita"); //Añadimos ítem al menú emergente
		 
		 JMenuItem cursivaE = new JMenuItem("Cursiva");
		 
		 negritaE.addActionListener(new StyledEditorKit.BoldAction());
		 
		 cursivaE.addActionListener(new StyledEditorKit.ItalicAction());
		 
		 emergente.add(negritaE); //Añadimos el ítem al menú emergente
		 
		 emergente.add(cursivaE);
		//al hacer clic con el botón secundario del ratón sobre el área de texto aparecerá el menú emergente
		 area.setComponentPopupMenu(emergente); 

	 }
	 
	 //Método que crea los menús y gestiona los eventos
	 public void ConfiguraMenu(String rotulo, String menu, String tipoletra, int estilos, int tam, String ruta_icono) {
		 JMenuItem elementoMenu = new JMenuItem(rotulo, new ImageIcon(ruta_icono)); 
		 
		 if(menu=="fuente") { 
			 
			 fuente.add(elementoMenu);
			 
			 if(tipoletra=="Arial") { 
				//Con la clase StyledEditorKit conseguimos que se cambiar las características solo del texto seleccionado
				 //El parámetro "cambia_letra" no hace nada es un nombre identificativo de lo que hace la acción
				 elementoMenu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Arial"));
				 
			 }else if(tipoletra=="Courier") {
				 elementoMenu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Courier"));

				 
			 }else if(tipoletra=="Verdana") {
				 
				 elementoMenu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra", "Verdana"));

			 }
		 }else if(menu=="estilo") {
			 
			 estilo.add(elementoMenu);
			 
			 if(estilos==Font.BOLD) {
				 
				 elementoMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));//Combinación de teclas
				 
				 elementoMenu.addActionListener(new StyledEditorKit.BoldAction());
			 }else if(estilos==Font.ITALIC) {
				 elementoMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
				 elementoMenu.addActionListener(new StyledEditorKit.ItalicAction());
			 }
			 
			
			 }else if(menu=="tamaño") {
				 
				 tamaño.add(elementoMenu);
				 
				 //La clase StyledEditorKit nos pide dos parámetros, uno es un nombre identificatio que es lo que hace
				 //y el segundo es la variable tam, para establecer el tamaño del texto
				 elementoMenu.addActionListener(new StyledEditorKit.FontSizeAction("cambia_tamaño", tam));
			 }
		 
		 }
	 
	 JTextPane area;
	 
	 JMenu fuente, estilo, tamaño;
	
 }
