package com.telus.topologyassignment.model;

public class TopologyAssignment {
	private String tn;
	private String mediaroomId;
	private String nodeName;
	private int slot;
	
	
	
	public TopologyAssignment(String tn, String mediaroomId, String nodeName, int slot) {
		super();
		this.tn = tn;
		this.mediaroomId = mediaroomId;
		this.nodeName = nodeName;
		this.slot = slot;
	}
	
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	public String getMediaroomId() {
		return mediaroomId;
	}
	public void setMediaroomId(String mediaroomId) {
		this.mediaroomId = mediaroomId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getSlot() {
		return slot;
	}
	public void setSlot(int slot) {
		this.slot = slot;
	}
	
	public String toString() {
		return String.format("tn=%s, mediaroomId=%s, nodeName=%s, slot=%d",
				tn, mediaroomId, nodeName, slot
				);
	}
}
