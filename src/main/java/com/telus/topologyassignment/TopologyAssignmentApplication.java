package com.telus.topologyassignment;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.telus.topologyassignment.dao.DAO;

@SpringBootApplication
public class TopologyAssignmentApplication {
	
	private static DAO dao;

	public TopologyAssignmentApplication(DAO dao) {
		this.dao = dao;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TopologyAssignmentApplication.class, args);
		
		//Get external_id from Node table
		List<String> ids = dao.getExternalIDs();
		System.out.println("External IDs from Node table");
		ids.forEach(System.out::println);
		
		
		//Split ids into chunk
		int chunkSize = 2;
		AtomicInteger counter = new AtomicInteger();
        final Collection<List<String>> chunkedIds = 
        		ids.stream().collect(Collectors.groupingBy(i -> counter.getAndIncrement() / chunkSize))
                            .values();
        
        //Query RDB_VIEW_XVU_MEDIAROOMS chunkSize at a time to prevent 1k IN CLAUSE limit in where condition
		System.out.println("\nQuerying RDB_VIEW_XVU_MEDIAROOMS " + chunkSize + " mediaroom_id at a time.");
        chunkedIds.stream().map(dao::getTopologyAssignment)
        .flatMap(Collection::stream)
        .collect(Collectors.toList()).forEach(System.out::println);
	}

}
