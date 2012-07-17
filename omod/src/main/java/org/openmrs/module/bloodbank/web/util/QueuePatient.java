package org.openmrs.module.bloodbank.web.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openmrs.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


public class QueuePatient {
	
	private String startDate;
	private String patientIdentifier;
	private String patientName;
	private String gender;
	private Integer orderId;
	private String acceptedDate;
	private String age;
	
	@ModelAttribute("testName")
	public String gettestName(){
		return "BloodTest";
	}
	
	@ModelAttribute("status")
	public String getstatus(){
		return "unaccepted";
	}
	
	@ModelAttribute("orderId")
	public Integer getorderId(){
		return orderId;
	}
	
	@ModelAttribute("age")
	public String getage(){
		return age;
	}
	
	@ModelAttribute("acceptedDate")
	public String getacceptedDate(){
		return acceptedDate;
	}
	
	@ModelAttribute("startDate")
	public String getstartDate(){
		return startDate;
	}
	
	@ModelAttribute("patientIdentifier")
	public String getpatientIdentifier(){
		return patientIdentifier;
	}
	
	@ModelAttribute("patientName")
	public String getpatientName(){
		return patientName;
	}
	
	@ModelAttribute("gender")
	public String getgender(){
		return gender;
	}
	
	public QueuePatient(Order order){
		startDate=order.getStartDate().toString();
		try{
			patientIdentifier=order.getPatient().getPatientId().toString();
			patientName=order.getPatient().getPersonName().getFullName();
		}
		catch(NullPointerException np){
			System.out.println("problem caught");
		}
		finally{
			
		}
		
		System.out.println(patientName+" Heyyyyy ");
		gender=order.getPatient().getGender();
		orderId=order.getId();
		age=order.getPatient().getAge().toString();
	}
	
	
	
	public static List<QueuePatient> ListQueuePatient(List<Order> orders){
		List<QueuePatient> tests= new ArrayList<QueuePatient>();
		Iterator<Order> iterator = orders.iterator();
		System.out.println(" Entered List");
		int i=0;
		while (iterator.hasNext()) {
			i=i+1;
			System.out.println(i+" patient ");
			QueuePatient q =new QueuePatient(iterator.next());
			tests.add(q);
			//if(i>=11)
				//break;
		}
		return tests;
	}
	
}
