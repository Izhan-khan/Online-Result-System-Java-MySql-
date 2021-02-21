package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CollegeModel;
/**
 * Test class of College model class
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */
public class TestCollegeModel {

	static CollegeModel model = new CollegeModel();
	
	public static void TestAdd()  {
		
		CollegeBean bean = new CollegeBean();
		try{
			
		bean.setName("College 3");
		bean.setAddress("junwani");
		bean.setCity("Bhilai");
		bean.setState("CG");
		bean.setPhoneNo("863678866");
		
		model.add(bean);
		
		
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestDelete(long pk) {
		CollegeBean bean = new CollegeBean();
		
		try {
			bean.setId(pk);
			
			model.delete(bean);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void TestFindByPk(long pk){
		
		CollegeBean bean = new CollegeBean();
		
		try {
			
			bean = model.findByPk(pk);
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public static void TestFindByName(String name){
		
		CollegeBean bean = new CollegeBean();
		
		try {
		
			bean = model.findByName(name);
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void TestList(){
		
		CollegeBean bean = new CollegeBean();
		
		try {
			
			List list = new ArrayList();
			list = model.list(1, 1);
			
			Iterator it  = list.iterator();
			while(it.hasNext()){
				bean = (CollegeBean) it.next();
				       System.out.println(bean.getName()+"\t");
				       System.out.println(bean.getAddress()+"\t");
				       System.out.println(bean.getCity()+"\t");
				       System.out.println(bean.getState()+"\t");
				       System.out.println(bean.getPhoneNo()+"\t");
			
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestUpdate(){
		
		try {
			CollegeBean bean = model.findByPk(2);
			
			bean.setName("College 2");
			bean.setAddress("junwani");
			bean.setCity("Bhilai");
			bean.setState("CG");
			
			model.update(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	 public static void testSearch() {
	        try {
	            CollegeBean bean = new CollegeBean();
	            List list = new ArrayList();
	            bean.setCity("Raipur");
	            // bean.setAddress("borawan");
	            list = model.search(bean, 1, 10);
	            if (list.size() < 0) {
	                
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CollegeBean) it.next();
	                
	                
	                
	                
	                
	                
	                
	                
	                
	                
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	
	public static void main(String[] args){
		
		TestList();
		
	}
	
	
}
