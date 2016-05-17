package clasesPrivadas.Dominio.Clases;

import java.util.ArrayList;
import java.util.HashMap;
/*
 @author: 
*/






import clasesCompartidas.Pair;

/**
 * @author Pablo Navarro Izquierdo
 *
 */
public class CtrlHetesim{

	public CtrlHetesim(){
		//constructura por defecto

	}
	

	/*
		Pre: Num columnas de a = Num filas de b
		Post: Devuelve el producto matricial a*b
	*/
	private static HashMap<Integer,ArrayList<Pair<Integer,Double>>> producto_mat(HashMap<Integer,ArrayList<Pair<Integer,Double>>> a, HashMap<Integer,ArrayList<Pair<Integer,Double>>> b) {
		HashMap<Integer,ArrayList<Pair<Integer,Double>>> r = new HashMap<Integer,ArrayList<Pair<Integer,Double>>>(); 
		for(int i: a.keySet()) {																//Iteramos sobre el numero de filas de a
			ArrayList<Pair<Integer,Double>> rfila = new ArrayList<Pair<Integer,Double>>();
			ArrayList<Pair<Integer,Double>> afila = a.get(i);
			for (int j: b.keySet()){															//Iteramos sobre el numero de columnas de b
				ArrayList<Pair<Integer,Double>> bfila = b.get(j);
				Pair<Integer,Double> rval = new Pair<Integer, Double>(j,(double)0);
				int z=0;																		//variable para interar sobre afila
				int y=0;																		//variable para iterar sobre bfila
				while(z< afila.size() && y < bfila.size()) {
					Pair<Integer, Double> aval=afila.get(z);									
					Pair<Integer, Double> bval=bfila.get(y);
					if(aval.getFirst() == bval.getFirst()) {									//En el caso que las variables apunten  al un elemento con el mismo indice
						double v=rval.getSecond();
						v+=aval.getSecond() * bval.getSecond();								//Multiplicamos sus valores y los sumamos al total
						rval.setSecond(v);							
						++z;																	//Y augmentamos las dos variables
						++y;
					}
					else if (aval.getFirst() < bval.getFirst()) ++z;							//En caso contrario, solo augmentamos la variable mas pequeña
					else ++y;
				}
				if (rval.getSecond()!= 0)rfila.add(rval);				
			}
			r.put(i, rfila);
		}
		return r;
	}
	/*
		Pre: Num columnas de a = Num filas de b
				a debe ser la matriz corespondiente al camino PL
				b debe ser la matriz correspondiente al camino PR-1
		Post Devuelve la matriz normalizada del hetesim, donde cada elemento ij corresponde a el valor hetesim del elemento i sobre el j;
	 */
	
	private static HashMap<Integer,ArrayList<Pair<Integer,Double>>> producto_norm_mat(HashMap<Integer,ArrayList<Pair<Integer,Double>>> a, HashMap<Integer,ArrayList<Pair<Integer,Double>>> b) {
		HashMap<Integer,ArrayList<Pair<Integer,Double>>> r = new HashMap<Integer,ArrayList<Pair<Integer,Double>>>(); 
		for(int i: a.keySet()) {																//Iteramos sobre el numero de filas de a
			ArrayList<Pair<Integer,Double>> rfila = new ArrayList<Pair<Integer,Double>>();
			ArrayList<Pair<Integer,Double>> afila = a.get(i);
			for (int j: b.keySet()){															//Iteramos sobre el numero de columnas de b
				ArrayList<Pair<Integer,Double>> bfila = b.get(j);
				Pair<Integer,Double> rval = new Pair<Integer, Double>(j,(double)0);
				int z=0;																		//variable para interar sobre afila
				int y=0;																		//variable para iterar sobre bfila
				while(z< afila.size() && y < bfila.size()) {
					Pair<Integer, Double> aval=afila.get(z);									
					Pair<Integer, Double> bval=bfila.get(y);
					if(aval.getFirst() == bval.getFirst()) {									//En el caso que las variables apunten  al un elemento con el mismo indice
						double v=rval.getSecond();
						v+=aval.getSecond() * bval.getSecond();								//Multiplicamos sus valores y los sumamos al total
						rval.setSecond(v);							
						++z;																	//Y augmentamos las dos variables
						++y;
					}
					else if (aval.getFirst() < bval.getFirst()) ++z;							//En caso contrario, solo augmentamos la variable mas pequeña
					else ++y;
				}
				if (rval.getSecond()!=0) {
					double mod,  mod1=0, mod2=0;													//Ahora necesitamos dividir cada elemento por el producto de los modulos de su fila y columna respectiva
					for (int x=0; x<afila.size(); ++x){												//Calculamos el modulo de la fila
						Pair<Integer,Double> p=afila.get(x);
						double v=p.getSecond();
						mod1+=v*v;
					}
					mod1=Math.sqrt(mod1);															//Calculamos el modulo de la columna
					for (int x=0; x<bfila.size(); ++x){
						Pair<Integer,Double> p=bfila.get(x);
						double v=p.getSecond();
						mod2+=v*v;
					}
					mod2=Math.sqrt(mod2);															
					mod=mod1*mod2;
					Double d=rval.getSecond();					
					d=d/mod;																		//Y dividimos el elemento por el producto de los modulos
					rval.setSecond(d);
					rfila.add(rval);
				}
			}
			r.put(i, rfila);
		}
		return r;
	}
	
	/*
	  Pre: R es la matriz de una relaciÃ³n AB entre cualquier par de nodos
	  Post: Devuelve la matriz correspondiente a la relacion AE.
	 */
	private static HashMap<Integer,ArrayList<Pair<Integer,Double>>> Relacion_Dummy(HashMap<Integer,ArrayList<Pair<Integer,Double>>> a){		//Obtencion de la matriz RL
		HashMap<Integer,ArrayList<Pair<Integer,Double>>> r = new HashMap<Integer,ArrayList<Pair<Integer,Double>>>();
		int etiqueta=0;
		for (int id: a.keySet()){ //iteramos sobre R
			ArrayList<Pair<Integer,Double>> afila = a.get(id); 											//fila[id] de a
			for (int j=0; j< afila.size(); ++j) {
				ArrayList<Pair<Integer,Double>> rfila = new ArrayList<Pair<Integer,Double>>(); 			//futura fila[id] de r1
				Pair<Integer,Double> p=new Pair<Integer,Double>(id, 1.0/afila.size());
				rfila.add(p);
				++etiqueta;
				r.put(etiqueta, rfila);
			}
		}
		
		return r;
	}
	
	/*
	 Pre: path es un camino valido 
	 Post: devuelve la matriz normalizada de aplicar hete sim en el camino p. En la fila i-esima se encuentran la relevancia de el elemento j sobre i;
	 */
	public static HashMap<Integer,ArrayList<Pair<Integer,Double>>> HeteSim(String p, Grafo g){
		HashMap<Integer,ArrayList<Pair<Integer,Double>>> r=new HashMap<Integer,ArrayList<Pair<Integer,Double>>>();
		HashMap<Integer,ArrayList<Pair<Integer,Double>>> pl = new HashMap<Integer,ArrayList<Pair<Integer,Double>>>();
		HashMap<Integer,ArrayList<Pair<Integer,Double>>> pr = new HashMap<Integer,ArrayList<Pair<Integer,Double>>>();
		HashMap<Integer,ArrayList<Pair<Integer,Double>>> opl = new HashMap<Integer,ArrayList<Pair<Integer,Double>>>();
		HashMap<Integer,ArrayList<Pair<Integer,Double>>> opr = new HashMap<Integer,ArrayList<Pair<Integer,Double>>>();
		String path=p;
		boolean first=true;
		if (path.length()%2==0) path=path.substring(0, path.length()/2) + "E" + path.substring(path.length()/2);			//Si el camino es par, le aÃ±adimos el caracter 'E' en la posicion central
		for (int i=0; i<path.length()/2; ++i) {								//Recorremos la parte izquierda del camino
			if (first) {													//En caso de primera iteracion
				String rel=path.substring(i,i+2);							//Miramos la primera relacion;
				if (path.charAt(i+1)=='E') {								//Si contiene el elemento E
					rel=path.substring(i,i+1) + path.substring(i+2, i+3);									//obtenemos el string de la relacion cambiando el elemento E por el contiguo
					return g.getRelaciones(rel,true);
				}	
				else pl=g.getRelaciones(rel, true);
				first=false;												//Marcamos que ya hemos completado la primera iteracion
			}
			else  {															//En una iteracion cualquiera
				String rel=path.substring(i+1,i+2) + path.substring(i,i+1);							//Miramos la relacion i-esima inversa
				if (path.charAt(i+1)=='E') {								//Si contiene el elemento E
					rel=path.substring(i,i+1) + path.substring(i+2, i+3);								//obtenemos el string de la relacion cambiando el elemento E por el contiguo
					opl=Relacion_Dummy(g.getRelaciones(rel,false));							//Y normalizamos por filas
				}
				else opl=g.getRelaciones(rel,false);
				pl=producto_mat(pl, opl);									//Hacemos el producto matricial entre los dos operandos
			}
			
		}
		first=true;
		for (int i=path.length()-1; i>=(path.length()/2)+1; --i) {			//Iteramos la parte derecha del camino desde la posicion final a la central
			if (first) {													//En caso de primera iteracion
				String rel=path.substring(i,i+1)+path.substring(i-1, i);	//Obtenemos el string de la relacion inverso de la posicion i-esima
				pr=g.getRelaciones(rel,true);							//Y normalizamos por filas
				first=false;												//Marcamos que ya hemos completado la primera iteracion
			}
			else  {															//En una iteracion cualquiera
				String rel=path.substring(i-1, i)+path.substring(i,i+1);	//Obtenemos el string de la relacion inverso de la posicion i-esima
				if (path.charAt(i-1)=='E') {								//Si contiene el elemento E
					rel=path.substring(i,i+1)+path.substring(i-2, i-1);			//obtenemos el string de la relacion cambiando el elemento E por el contiguo
					opr=Relacion_Dummy(g.getRelaciones(rel,false));								//Y normalizamos por filas
				}
				else opr=g.getRelaciones(rel,false);						//Y normalizamos por filas
				pr=producto_mat(pr, opr);									//Hacemos el producto matricial entre los dos operandos
			}
			
		}												//Hacemos la transpuesta de la matriz correspondiene al camino derecho
		r=producto_norm_mat(pl,pr);											//Y finalmente hacemos el producto matricial normalizado entre las dos matrices de los caminos
		
		return r;
	}
}
