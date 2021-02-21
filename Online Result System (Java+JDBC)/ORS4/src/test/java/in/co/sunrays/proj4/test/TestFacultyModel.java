package in.co.sunrays.proj4.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.FacultyModel;
/**
 * Test class of Faculty model class
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */

public class TestFacultyModel {

	static FacultyModel model = new FacultyModel();

	public static void TestAdd() {

		FacultyBean bean = new FacultyBean();
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/yyyy");

		try {

			bean.setFirstName("Abishekh");
			bean.setLastName("Mishra");
			bean.setEmailId("AB.mishra12");
			bean.setDob(sdf.parse("09/06/2019"));
			bean.setCollegeId(1);
			bean.setCourseId(1);

			model.add(bean);

			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void TestDelete(long pk) {

		FacultyBean bean = new FacultyBean();

		try {

			bean.setId(pk);

			model.delete(bean);

			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void TestUpdate() {

		FacultyBean bean = new FacultyBean();

		try {

			bean = model.findByPk(3);

			

			bean.setMobileNo("9892228881");

			model.update(bean);
			System.out.println("Updated");

			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void TestFindByPk(long pk) {

		FacultyBean bean = new FacultyBean();

		try {

			bean = model.findByPk(pk);

			
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void TestList() {

		FacultyBean bean = null;

		List list = new ArrayList();

		try {

			list = model.list();

			Iterator it = list.iterator();

			while (it.hasNext()) {
				bean = new FacultyBean();

				bean = (FacultyBean) it.next();

				System.out.println(bean.getFirstName() + "\t");
				System.out.println(bean.getLastName() + "\t");

				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	 public static void testSearch() {
	        try {
	            FacultyBean bean = new FacultyBean();
	            List list = new ArrayList();
	            bean.setFirstName("");
	            // bean.setAddress("borawan");
	            list = model.search(bean, 1, 10);
	            if (list.size() < 0) {
	                
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (FacultyBean) it.next();
	                
	                
	                
	                
	                
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	public static void main(String[] args) {
		TestUpdate();
	}

}
