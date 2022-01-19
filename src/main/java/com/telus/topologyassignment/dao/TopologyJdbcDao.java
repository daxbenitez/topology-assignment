package com.telus.topologyassignment.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.telus.topologyassignment.model.TopologyAssignment;

@Component
public class TopologyJdbcDao implements DAO {

	private JdbcTemplate jdbcTemplate;
	
	public TopologyJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	
	@Override
	public List<String> getExternalIDs() {
		String sql = "SELECT external_id from Node";
	    return jdbcTemplate.query(sql, (resultSet) -> {
	    	List<String> ids = new ArrayList<String>();
	    	while(resultSet.next()){
	    		ids.add(resultSet.getString("external_id"));
	    	}
	    	return ids;
	    });
	}

	@Override
	public List<TopologyAssignment> getTopologyAssignment(List<String> getExternalIDs) {

		String inClauseParam = String.join(",", getExternalIDs);
		String sql = "SELECT tn, mediaroom_id, node_name, node_slot from RDB_VIEW_XVU_MEDIAROOMS where "
				+ "mediaroom_id in (" + inClauseParam + ")";
		System.out.println(sql);
		
	    return jdbcTemplate.query(sql, (resultSet) -> {
	    	List<TopologyAssignment> topologies = new ArrayList<TopologyAssignment>();
	    	while(resultSet.next()){
	    		TopologyAssignment o = new TopologyAssignment(
	    				resultSet.getString("tn"),
	    				resultSet.getString("mediaroom_id"),
	    				resultSet.getString("node_name"),
	    				resultSet.getInt("node_slot")
	    				);
	    		topologies.add(o);
	    	}
	    	return topologies;
	    });
	}

}
