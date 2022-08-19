package com.laptopapp.dao;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.tree.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.laptop.exceptions.IdNotFoundException;
import com.laptop.exceptions.LaptopNotFoundException;
import com.laptop.model.Laptop;
import com.laptopapp.util.DBQueries;

@Repository
public class LaptopRepositoryImpl implements ILaptopRepository {
	 @Autowired
		private JdbcTemplate jdbcTemplate;
		
		
		public JdbcTemplate getJdbcTemplate() {
			return jdbcTemplate;
		}
	   
		public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

	
	
	public LaptopRepositoryImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Laptop> findAll() {
		RowMapper<Laptop> mapper = new Laptopmapper();
		List<Laptop> investments = 
		jdbcTemplate.query(DBQueries.SELECTQUERY, mapper);
		return laptop;
		

	@Override
	public List<Laptop> findByType(String type) throws LaptopNotFoundException {
		
		return showAllLaptops().stream().filter(type1->type1.getType().equals(type)).collect(Collectors.toList());
	}

	

	@Override
	public List<Laptop> findByName(String name) throws LaptopNotFoundException {
		// TODO Auto-generated method stub
		return showAllLaptops().stream().filter(type1->type1.getName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public List<Laptop> findByPrice(double price) throws LaptopNotFoundException {
		// TODO Auto-generated method stub
		return showAllLaptops().stream().filter(amount->amount.getPrice()<=price).collect(Collectors.toList());
	}

	@Override
	public Laptop findById(int laptopId) throws IdNotFoundException {
		
		return (Laptop) showAllLaptops().stream().filter(policy->policy.getLaptopId()==laptopId).
				findFirst().orElseThrow(IdNotFoundException::new);
	}
    private List<Laptop> showAllLaptops(){
	return Arrays.asList(
			new Laptop("asus","chromebook",5,2456.8),
			new Laptop("dell","macbook",6,2579.0),
			new Laptop("thoshiba","netbook",5,26677.0),
			new Laptop("hp","ultrabook",9,2778.0));
			
}

	@Override
	public void addLaptop(Laptop laptop) {
		String sql = DBQueries.INSERTQUERY;
		Object[] laptopArray = {
				laptop.getName(),laptop.getType(),laptop.getLaptopId(),laptop.getPrice()		
		};
		
		jdbcTemplate.update(DBQueries.INSERTQUERY,laptopArray);
		
	}

	@Override
	public void updateLaptop(int laptopId, double price) {
		String sql = DBQueries.UPDATEQUERY;
		jdbcTemplate.update(sql,price,laptopId);
		
		
	}

	@Override
	public void deleteLaptop(int laptopId) {
		String sql = DBQueries.DElETEQUERY;
		jdbcTemplate.update(sql,laptopId);
		
	}

}
