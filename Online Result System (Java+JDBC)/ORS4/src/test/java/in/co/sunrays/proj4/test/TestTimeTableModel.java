package in.co.sunrays.proj4.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.TimeTableModel;
/**
 * Test class of Time Table model class
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */

public class TestTimeTableModel {

static TimeTableModel model = new TimeTableModel();
	
	public static void TestAdd() {

		TimeTableBean bean = new TimeTableBean();
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/yyyy");
		try {

			bean.setCourseId(1);
			bean.setCourseName("Mechanical");
			bean.setSubjectId(1);
			bean.setSubjectName("kinematic of machines");
			bean.setExamDate(sdf.parse("12/12/12"));
			
			model.add(bean);

			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void TestDelete(long pk) {

		TimeTableBean bean = new TimeTableBean();

		try {

			bean.setId(pk);

			model.delete(bean);

			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public static void TestUpdate() {

		 TimeTableBean bean = new  TimeTableBean();

		try {

			bean = model.findByPk(2);

			bean.setCourseName("Mechatronics");
			bean.setSubjectName("Machine Design");

			model.update(bean);

			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}	
	
	public static void TestFindByPk(long pk) {

		TimeTableBean bean = new TimeTableBean();

		try {

			bean = model.findByPk(pk);

			
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void TestList() {

		TimeTableBean bean = null;

		List list = new ArrayList();

		try {

			list = model.list();

			Iterator it = list.iterator();

			while (it.hasNext()) {
				bean = new TimeTableBean();

				bean = (TimeTableBean) it.next();

				System.out.println  (bean.getCourseName() + "\t");
				System.out.println   (bean.getSubjectName() + "\t");

				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public static void testSearch() {

        try {
            TimeTableBean bean = new TimeTableBean();
            List list = new ArrayList();
            System.out.println(bean+"\n");
            list = model.search(null, 0, 0);
            if (list.size() < 0) {
                
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (TimeTableBean) it.next();
                
                System.out.print  (bean.getCourseName() + "\t");
				System.out.print   (bean.getSubjectName() + "\t");
				System.out.print   (bean.getExamDate() + "\t");
				System.out.print   (bean.getExamTime() + "\t");
				System.out.println   (bean.getSemester() + "\t");
                
                             
                
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
	
	
	
	public static void main(String[] args) {
		testSearch();
	}
}
