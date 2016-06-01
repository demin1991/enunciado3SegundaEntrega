package clasesPrivadas.Dominio.Clases;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * @author Daniel Pulido
 *
 */
public class vistaCP1 {
	private JPanel panelCCN = new JPanel();
	private JPanel panelCCN1 = new JPanel();
	private JPanel panelCCN2 = new JPanel();
	private JPanel panelCCN3 = new JPanel();
	private JPanel panelCCN4 = new JPanel();
	private JPanel panelBotones = new JPanel();
    private CtrlPresentacion iCtrlPresentacion;
	private JFrame frameVistaCCN = new JFrame("Camino Predeterminado");	
	private TextField tf1 = new TextField("",10);
	private TextField tf2 = new TextField("",10);
	private Label Nodo = new Label("Nodo");
	private TextField tf3 = new TextField("",10);
	private Label Men = new Label("Menor");
	private Label May = new Label("Mayor");
	private JButton buttonOk = new JButton("Ok");
	private String camino;

	public vistaCP1(CtrlPresentacion pCtrlPresentacion) {
	    iCtrlPresentacion = pCtrlPresentacion;
	    inicializarComponentes();
	}

	public void activar(String camino1) {
		camino = camino1;
	    frameVistaCCN.setEnabled(true);
	    hacerVisible();
	  }

	  public void desactivar() {
	    frameVistaCCN.setEnabled(false);
	    hacerInvisible();
	  }
		  public void hacerVisible() {
		    frameVistaCCN.pack();
		    frameVistaCCN.setVisible(true);
		  }

		  public void hacerInvisible() {
		    frameVistaCCN.setVisible(false);
		  }
		  
	
	public void actionPerformed_buttonOk (ActionEvent event) {
		Component a = panelCCN.getComponent(0);
		Component c = panelCCN.getComponent(1);
		Component d = panelCCN.getComponent(2);
		JPanel a1 = (JPanel) a;
		JPanel c1 = (JPanel) c;
		JPanel d1 = (JPanel) d;
		Component a2 = a1.getComponent(1);
		Component c2 = c1.getComponent(1);
		Component d2 = d1.getComponent(1);
		TextField a3 = (TextField) a2;
		TextField c3 = (TextField) c2;
		TextField d3 = (TextField) d2;
		String a4 = a3.getText();
		String c4 = c3.getText();
		String d4 = d3.getText();
		double c5 = Double.parseDouble(c4);
		double d5 = Double.parseDouble(d4);
		System.out.println(a4);
		System.out.println(c4);
		System.out.println(d4);

		iCtrlPresentacion.sincronizacionCP1_a_BH1(a4,camino,c5,d5);
	}
  
  private void asignar_listenersComponentes3() {
	  buttonOk.addActionListener
	      (new ActionListener() {
	        public void actionPerformed (ActionEvent event) {
	          String texto = ((JButton) event.getSource()).getText();
	          System.out.println("Has clickado el boton con texto: " + texto);
	          actionPerformed_buttonOk(event);
	        }
	      });
	}
	
	public void inicializarComponentes() {
		frameVistaCCN.setMinimumSize(new Dimension(590,200));
		frameVistaCCN.setResizable(true);
		frameVistaCCN.setLocationRelativeTo(null);
		frameVistaCCN.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    JPanel contentPaneAnadir = (JPanel) frameVistaCCN.getContentPane();
	    contentPaneAnadir.add(panelCCN);
	    
	    frameVistaCCN.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	        	 iCtrlPresentacion.sincronizacionCP1_a_RC();
	         }        
	      });  
	    
	    
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 5, 15);
	    frameVistaCCN.setLayout(layout);
	    panelCCN.setLayout(new FlowLayout());
	    
	    Nodo.setText("Nodo");
	    Men.setText("Extremo menor de relevancia");
	    May.setText("Extremo mayor de relevancia");
	    panelCCN.setLayout(new BorderLayout());
	    panelCCN.add(panelCCN1,BorderLayout.NORTH);
	    panelCCN.add(panelCCN2,BorderLayout.WEST);
	    panelCCN.add(panelCCN3,BorderLayout.EAST);
	    panelCCN.add(panelCCN4,BorderLayout.SOUTH);
	    panelCCN1.add(Nodo);
	    panelCCN1.add(tf1);
	    panelCCN2.add(Men);
	    panelCCN2.add(tf2);
	    panelCCN3.add(May);
	    panelCCN3.add(tf3);
	    panelCCN4.add(panelBotones,BorderLayout.PAGE_END);
	    panelBotones.add(buttonOk);

		
	    asignar_listenersComponentes3();
	}
	
}

