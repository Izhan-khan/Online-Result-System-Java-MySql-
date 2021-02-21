package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.SubjectModel;
/**
 * Test class of Subject model class
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */

public class TestSubjectModel {

	static SubjectModel model = new SubjectModel();
	
	public static void TestAdd() {

		SubjectBean bean = new SubjectBean();

		try {

			bean.setCourseId(1);
			bean.setCourseName("Mechanical");
			//bean.setSubjectId(1);
			bean.setSubjectName("kinematic of machines");

			model.add(bean);

			System.out.println("Added");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void TestDelete(long pk) {

		SubjectBean bean = new SubjectBean();

		try {

			bean.setId(pk);

			model.delete(bean);

			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void TestUpdate() {

		SubjectBean bean = new SubjectBean();

		try {

			bean = model.findByPk(2);

			bean.setCourseName("Mechatronics");
			bean.setSubjectName("Machine Design");

			model.update(bean);

			System.out.println("Updated");
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void TestFindByPk(long pk) {

		SubjectBean bean = new SubjectBean();

		try {

			bean = model.findByPk(pk);

			System.out.println(bean.getCourseName() + "\t");
			System.out.println(bean.getSubjectName() + "\t");

			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void TestList() {

		SubjectBean bean = null;

		List list = new ArrayList();

		try {

			list = model.list();

			Iterator it = list.iterator();

			while (it.hasNext()) {
				bean = new SubjectBean();

				bean = (SubjectBean) it.next();

				System.out.println(bean.getCourseName() + "\t");
				System.out.println(bean.getSubjectName() + "\t");

				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void testSearch() {

        try {
            SubjectBean bean = new SubjectBean();
            List list = new ArrayList();
            bean.setSubjectName("kinematic");
            list = model.search(bean, 0, 0);
            if (list.size() < 0) {
                
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (SubjectBean) it.next();
                
                System.out.println(bean.getCourseName() + "\t");
				System.out.println(bean.getSubjectName() + "\t");

                
                
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
	
	
	public static void main(String[] args) {
		TestDelete(3);
		
		
	}
}
