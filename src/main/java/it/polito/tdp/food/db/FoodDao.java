package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.food.model.Adiacenza;
import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Portion;

public class FoodDao {
	public List<Food> listAllFoods(){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			List<Food> list = new ArrayList<>() ;

			ResultSet res = st.executeQuery() ;

			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_code"),
							res.getString("display_name")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}

			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}

	public List<Condiment> listAllCondiments(){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			List<Condiment> list = new ArrayList<>() ;

			ResultSet res = st.executeQuery() ;

			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_code"),
							res.getString("display_name"),
							res.getDouble("condiment_calories"), 
							res.getDouble("condiment_saturated_fats")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}

			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}

	public List<Portion> listAllPortions(){
		String sql = "SELECT * FROM portion" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			List<Portion> list = new ArrayList<>() ;

			ResultSet res = st.executeQuery() ;

			while(res.next()) {
				try {
					list.add(new Portion(res.getInt("portion_id"),
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"), 
							res.getDouble("calories"),
							res.getDouble("saturated_fats"),
							res.getInt("food_code")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}

			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}

	public List<String> listAllTypePortions(){
		String sql = "SELECT DISTINCT portion_display_name AS pn " + 
				"FROM `portion` " + 
				"ORDER BY pn ASC" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			List<String> list = new ArrayList<>() ;

			ResultSet res = st.executeQuery() ;

			while(res.next()) {
				try {
					String portion_type_name = res.getString("pn");
					list.add(portion_type_name);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}

			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}

	public List<String> getVertex(Integer calorie){
		String sql = "SELECT DISTINCT portion_display_name AS pn " + 
				"FROM `portion` " + 
				"WHERE calories < ? " +
				"ORDER BY pn ASC" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, calorie);

			List<String> list = new ArrayList<>() ;

			ResultSet res = st.executeQuery() ;

			while(res.next()) {
				try {
				
				String portion_display_name = res.getString("pn");
					list.add(portion_display_name);
					
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}

			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	

	public List<Adiacenza> getAdiacenze(){
		String sql = "SELECT p1.portion_display_name AS pn1, p2.portion_display_name AS pn2, COUNT(DISTINCT p1.food_code) as cnt " + 
				"FROM `portion` AS p1, `portion` AS p2 " + 
				"WHERE p1.portion_id <> p2.portion_id AND p1.food_code = p2.food_code " + 
				"GROUP BY pn1, pn2" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;

			List<Adiacenza> list = new ArrayList<>() ;

			ResultSet res = st.executeQuery() ;

			while(res.next()) {
				try {
					list.add(new Adiacenza(res.getString("pn1"), res.getString("pn2"), res.getInt("cnt")));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}

			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
}
