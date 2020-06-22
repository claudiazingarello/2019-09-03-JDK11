package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	FoodDao dao;
	List<String> portion_display_name;
	Graph<String, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new FoodDao();
	}
	
	public void creaGrafo(Integer calorie) {
		grafo = new SimpleWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		portion_display_name = dao.getVertex(calorie);
		//Aggiunta vertici
		Graphs.addAllVertices(grafo, portion_display_name);
		
		//Aggiungi gli archi
		for(Adiacenza a : dao.getAdiacenze()) {
			if(this.grafo.vertexSet().contains(a.getPortion_name1()) &&
					this.grafo.vertexSet().contains(a.getPortion_name2())) {
				//Se entrambi i vertici sono presenti
				if(grafo.containsVertex(a.getPortion_name1()) && grafo.containsVertex(a.getPortion_name2()))
					Graphs.addEdge(this.grafo,a.getPortion_name1(), a.getPortion_name2(), a.getPeso());
			}
		}
		
		System.out.println("Grafo creato!");
		System.out.println("#vertici: "+ grafo.vertexSet().size());
		System.out.println("#archi: "+ grafo.edgeSet().size());
	}

	public List<String> verticiGrafo() {
		return portion_display_name;
	}

	public List<PorzioneAdiacente> getAdiacenti(String s) {
		List<String> vicini = Graphs.neighborListOf(this.grafo,	s);
		List<PorzioneAdiacente> result = new ArrayList<PorzioneAdiacente>();
		for(String v : vicini) {
			PorzioneAdiacente pa = new PorzioneAdiacente(v, this.grafo.getEdgeWeight(this.grafo.getEdge(s, v)));
			result.add(pa);
		}
		return result;
	}
	
	
}
