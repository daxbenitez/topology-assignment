package com.telus.topologyassignment.dao;

import java.util.List;

import com.telus.topologyassignment.model.TopologyAssignment;

public interface DAO {
	
	List<String> getExternalIDs();
	
	List<TopologyAssignment> getTopologyAssignment(List<String> getExternalIDs);
	
}
