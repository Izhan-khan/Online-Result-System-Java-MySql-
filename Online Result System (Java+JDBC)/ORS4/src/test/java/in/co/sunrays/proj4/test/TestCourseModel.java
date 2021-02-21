package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CourseModel;
/**
 * Test class of Course model class
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */

public class TestCourseModel {

	static CourseModel model = new CourseModel();
	
	public static void TestAdd(){
		
		CourseBean bean = new CourseBean();
		
		try {
			
			bean.setCourseName("Mechanicals");
			bean.setDescription("To Know about Working of Electric components");
			bean.setDuration(4);
			
			
			model.add(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void TestDelete(long pk){
		
		CourseBean bean = new CourseBean();
		
		try {
			
			bean.setId(pk);
			
			model.delete(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestUpdate(){
		
		CourseBean bean = new CourseBean();
		
		try {
			
			bean = model.findByPk(3);
			
			bean.setCourseName("Civils");
			bean.setDescription("To know about Structural and Physical aspects of a structure");
			
			
			model.update(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public static void TestFindByPk(long pk){
		
		CourseBean bean = new CourseBean();
		
		try {
			
			bean = model.findByPk(pk);
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestList(){
		
		CourseBean bean = null;
	
		List list = new ArrayList();
		
		try {
			
			list = model.list(1,1);
			
			Iterator it = list.iterator();
			
			while(it.hasNext()){
				bean = new CourseBean();
				
				bean = (CourseBean) it.next();
		
				System.out.println(bean.getCourseName()+"\t");
				System.out.println(bean.getDescription()+"\t");
		
				
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}	
	
	public static void TestFindByCourseName(String name){

		CourseBean bean = new CourseBean();
		
		try {
			
			bean = model.findByCourseName(name);
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	 public static void testSearch() {
	        try {
	            CourseBean bean = new CourseBean();
	            List list = new ArrayList();
	            bean.setCourseName("it");
	            // bean.setAddress("borawan");
	            list = model.search(bean, 1, 10);
	            if (list.size() < 0) {
	                
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CourseBean) it.next();
	                
	                
	                
	                
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	
	public static void main(String[] args) {
		testSearch();
	}
	
}
