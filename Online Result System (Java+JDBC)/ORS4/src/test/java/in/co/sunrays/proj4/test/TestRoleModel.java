package in.co.sunrays.proj4.test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.RoleModel;
/**
 * Test class of Role model class
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */

public class TestRoleModel {
	
static RoleModel model = new RoleModel();

// instance and static variable compiles 
String s1;

//But local variable don't

//public static void oo() {
//	String s;
//	Object o;
//	float f;
//	
//	System.out.println(s);
//	System.out.println(o);
//	System.out.println(f);
//	
//}
	
	public static void TestAdd(){
		
		
		RoleBean bean = new RoleBean();
	
		
		try {
			
			bean.setName("HR");
			bean.setDescription("To authorize the various departments of a company ");
			
			model.add(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void TestDelete(long pk){
		
		RoleBean bean = new RoleBean();
		
		try {
			
			bean.setId(pk);
			
			model.delete(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestFindByPK(long pk){
		
		RoleBean bean = new RoleBean();
		
		try {
			
			bean = model.findByPk(pk);
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public static void TestFindByName(String Name){
		
		RoleBean bean = new RoleBean();
		
		try {
			
			bean = model.findByName(Name);

			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	public static void TestList(){
		
		RoleBean bean = null;
	
		List list = new ArrayList();
		
		try {
			
			list = model.list();
			
			Iterator it = list.iterator();
			
			while(it.hasNext()){
				bean = new RoleBean();
				
				bean = (RoleBean) it.next();
				
				System.out.println  (bean.getName()+"\t");
				System.out.println    (bean.getDescription()+"\t");
			
				
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestUpdate(){
		
		RoleBean bean = new RoleBean();
		
		try{	
			
				bean = model.findByPk(5);
			
				bean.setName("Student");
				bean.setDescription("Clean");
				
				model.update(bean);
				
				
			
			}catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
			}
		
		}
	
	 public static void testSearch() {

	        try {
	            RoleBean bean = new RoleBean();
	            List list = new ArrayList();
	            bean.setName("");
	            list = model.search(bean);
	            if (list.size() < 0) {
	                
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (RoleBean) it.next();
	                
	                
	                
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	
	
	public static void main(String[] args) {
		TestUpdate();
		
		TestRoleModel t = new TestRoleModel();
		
		System.out.println(t.s1);
	}
}
