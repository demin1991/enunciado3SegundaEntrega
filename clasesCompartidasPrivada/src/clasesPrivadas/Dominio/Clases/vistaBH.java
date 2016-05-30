package clasesPrivadas.Dominio.Clases;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Set;

public class vistaBH {
	private Panel panelBH = new Panel();
	private Frame frameVistaBH = new Frame("Camino Predeterminado");	
	private CtrlPresentacion iCtrlPresentacion;
	private Label headerLabel;
	private Label statusLabel;
	private Button OKButton = new Button("Escoge");
    private Choice camino = new Choice();

	
	 void asignar_listenersComponentes2() {
		 OKButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	            String data = "Camino Seleccionado: " 
	            + camino.getItem(camino.getSelectedIndex());
	            statusLabel.setText(data);
	        	 iCtrlPresentacion.sincronizacionBH_a_Principal1(camino.getItem(camino.getSelectedIndex()));
	         }
	      }); 
	 }

	public void activar(HashMap<String, Set<Integer>> a) {
		for (String key : a.keySet()) {
			camino.add(key);
		}
		frameVistaBH.setEnabled(true);
	    hacerVisible();
	  }

	  public void desactivar() {
	    frameVistaBH.setEnabled(false);
	    hacerInvisible();
	  }
		  public void hacerVisible() {
		    frameVistaBH.pack();
		    frameVistaBH.setVisible(true);
		  }

		  public void hacerInvisible() {
		    frameVistaBH.setVisible(false);
		  }
		  
	
	public vistaBH(CtrlPresentacion pCtrlPresentacion) {
	    iCtrlPresentacion = pCtrlPresentacion;
	    inicializarComponentes();
	}
	
	void inicializarComponentes() {
		frameVistaBH.setSize(new Dimension(400,400));
		frameVistaBH.setMinimumSize(new Dimension(700,200));
		frameVistaBH.setResizable(true);
	    frameVistaBH.setLayout(new GridLayout(3, 1));
	    panelBH.setLayout(new FlowLayout());
	    frameVistaBH.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 iCtrlPresentacion.sincronizacionBH_a_Principal();
	         }        
	      });    
	      headerLabel = new Label();
	      headerLabel.setAlignment(Label.CENTER);
	      statusLabel = new Label();        
	      statusLabel.setAlignment(Label.CENTER);
	      statusLabel.setSize(350,100);
	      
	      frameVistaBH.add(headerLabel);
	      frameVistaBH.add(panelBH);
	      frameVistaBH.add(statusLabel);
	      frameVistaBH.setVisible(true);

	      headerLabel.setText("Control in action: Choice"); 

		camino.add("haha");

	      panelBH.add(camino);
	      panelBH.add(OKButton);


	    asignar_listenersComponentes2();
	}


}
