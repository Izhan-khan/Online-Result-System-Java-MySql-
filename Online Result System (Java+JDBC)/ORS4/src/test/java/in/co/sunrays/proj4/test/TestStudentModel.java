package in.co.sunrays.proj4.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.StudentModel;
/**
 * Test class of Student model class
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */

public class TestStudentModel {

	static StudentModel model = new StudentModel();
	
	public static void TestAdd(){
		
		StudentBean bean = new StudentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/yyyy");
		
		try {
			
			bean.setFirstName("Rahul");
			bean.setLastName("sharma");
			bean.setCollegeId(1l);
			bean.setCollegeName("College 1");
			bean.setDob(sdf.parse("6/2/1997"));
			bean.setMobileNo("9898728329");
			bean.setEmail("123@12asfgqw3");
			
			model.add(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void TestDelete(long pk){
		
		StudentBean bean = new StudentBean();
		
		try {
			
			bean.setId(pk);
			
			model.delete(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestFindByPK(long pk){
		
		StudentBean bean = new StudentBean();
		
		try {
			
			bean = model.findByPk(pk);
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestFindByEmail(String Email){
		
		StudentBean bean = new StudentBean();
		
		try {
			
			bean = model.findByEmail(Email);

			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	public static void TestList(){
		
		StudentBean bean = null;
	
		List list = new ArrayList();
		
		try {
			
			list = model.list(1,1);
			
			Iterator it = list.iterator();
			
			while(it.hasNext()){
				bean = new StudentBean();
				
				bean = (StudentBean) it.next();
				
				System.out.println (bean.getFirstName()+"\t");
				System.out.println (bean.getLastName()+"\t");
				System.out.println(bean.getCollegeId()+"\t");
				System.out.println   (bean.getDob()+"\t");
				System.out.println    (bean.getMobileNo()+"\t");
				System.out.println    (bean.getEmail()+"\t");
			
				
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestUpdate(){
		
		StudentBean bean = new StudentBean();
		
		try {
			
			bean = model.findByPk(2);
			
			bean.setFirstName("Himashu");
			bean.setEmail("Kashyap");
			bean.setCollegeId(2l);
			bean.setCollegeName("College 2");
			
			model.update(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	public static void testSearch() {

        try {
            StudentBean bean = new StudentBean();
            List list = new ArrayList();
            bean.setFirstName("himashu");
            list = model.search(bean, 0, 0);
            if (list.size() < 0) {
                
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (StudentBean) it.next();
                
                
                
                
                
                
                
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
	
	public static void main(String[] args){
		testSearch();
		
	}
	
}
