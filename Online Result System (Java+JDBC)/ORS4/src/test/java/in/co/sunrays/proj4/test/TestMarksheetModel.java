package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.MarksheetBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.MarksheetModel;

/**
 * Test class of Marksheet model class
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */


public class TestMarksheetModel {

static MarksheetModel model = new MarksheetModel();
	
	public static void TestAdd(){
		
		MarksheetBean bean = new MarksheetBean();
		
		try {
			
			bean.setRollNo("101");
			bean.setName("Hem Shilabh");
			bean.setPhysics(54);
			bean.setChemistry(57);
			bean.setMaths(66);
			bean.setStudentId(1);
			
			model.add(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void TestDelete(long pk){
		
		MarksheetBean bean = new MarksheetBean();
		
		try {
			
			bean.setId(pk);
			
			model.delete(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestFindByPk(long pk){
		
		MarksheetBean bean = new MarksheetBean();
		
		try {
			
			bean = model.findByPk(pk);
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestFindByRollNo(String rollno){
		
		MarksheetBean bean = new MarksheetBean();
		
		try {
			
			bean = model.findByRollNo(rollno);
			
			
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void TestList(){
		
		MarksheetBean bean = null;
	
		List list = new ArrayList();
		
		try {
			
			list = model.list();
			
			Iterator it = list.iterator();
			
			while(it.hasNext()){
				bean = new MarksheetBean();
				
				bean = (MarksheetBean) it.next();
				
				System.out.print(bean.getRollNo()+"\t");
				System.out.print(bean.getName()+"\t");
				System.out.print(bean.getPhysics()+"\t");
				System.out.print(bean.getChemistry()+"\t");
				System.out.print(bean.getMaths()+"\t");
				System.out.print(bean.getStudentId()+"\t"+"\n");
			
				
			}	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}	
	
	public static void TestUpdate(){
		
		MarksheetBean bean = new MarksheetBean();
		
		try {
			
			bean = model.findByPk(2);
			
			bean.setName("Himashu Kashyap");
			bean.setStudentId(1);
			bean.setRollNo("103");
			
			
			model.update(bean);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void testSearch() {
        try {
            MarksheetBean bean = new MarksheetBean();
            List list = new ArrayList();
            list = model.search(null);
            if (list.size() < 0) {
                
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (MarksheetBean) it.next();
                
                System.out.print(bean.getRollNo()+"\t");
				System.out.print(bean.getName()+"\t");
				System.out.print(bean.getPhysics()+"\t");
				System.out.print(bean.getChemistry()+"\t");
				System.out.print(bean.getMaths()+"\t");
				System.out.print(bean.getStudentId()+"\t"+"\n");
			
                
                
                
                
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

	
	public static void main(String[] args) {
		testSearch();
	}
}
