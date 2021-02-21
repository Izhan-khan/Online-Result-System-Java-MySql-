package in.co.sunrays.proj4.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.model.UserModel;
/**
 * Test class of User model class
 * 
 * @author SunilOS
 * @version 1.0
 * 
 *
 */

public class TestUserModel {
	
	static UserModel model= new UserModel();        	
 
	public static void testAdd() {

    try {
    	  UserBean bean = new UserBean();
          SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/yyy");

         
          bean.setFirstName("Hem Shailabh");
          bean.setLastName("Sahu");
          bean.setLogin("Hem2gmail111.com");
          bean.setPassword("Shailabh");
          bean.setDob(sdf.parse("30/10/1992"));
          bean.setRoleId(1L);
          bean.setGender("Male");
          bean.setConfirmPassword("Shailabh");
          long pk = model.add(bean);
         
          
    	
    	
    } catch (Exception e) {
        e.printStackTrace();
    }

}

	public static void testDelete(int pk){
		
		UserModel model = new UserModel();
		
		try{
			UserBean bean  = new UserBean();
			
			bean.setId(pk);
			model.delete(bean);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void testfindByPk() throws ApplicationException{
		 
		
		UserBean bean = new UserBean();
         long pk = 3;
         bean = model.findByPk(pk);
         if (bean == null) {
             
         }
         System.out.println (bean.getId()+"\t");
         System.out.println     (bean.getFirstName()+"\t");
         System.out.println   (bean.getLastName()+"\t");
         System.out.println   (bean.getLogin()+"\t");
         System.out.println   (bean.getPassword()+"\t");
         System.out.println  (bean.getDob()+"\t");
         System.out.println(bean.getRoleId()+"\t");
         System.out.println (bean.getUnSuccessfulLogin()+"\t");
         System.out.println   (bean.getGender()+"\t");
         System.out.println   (bean.getLastLogin()+"\t");
         System.out.println  (bean.getLock()+"\t");
	}
	
	public static void testfindBylogin() throws ApplicationException{
		
		UserBean bean = new UserBean();
        String login = "ik@123.com";
        bean = model.findByLogin(login);
        if (bean == null) {
            
        }
        System.out.println       (bean.getId()+"\t");
        System.out.println (bean.getFirstName()+"\t");
        System.out.println  (bean.getLastName()+"\t");
        System.out.println  (bean.getLogin()+"\t");
        System.out.println (bean.getPassword()+"\t");
        System.out.println  (bean.getDob()+"\t");
        System.out.println   (bean.getRoleId()+"\t");
        System.out.println    (bean.getUnSuccessfulLogin()+"\t");
        System.out.println   (bean.getGender()+"\t");
        System.out.println  (bean.getLastLogin()+"\t");
        System.out.println (bean.getLock()+"\t");
	}
	
	public static void testUpdate() {

        try {
        	
            UserBean bean = model.findByPk(3);
            bean.setFirstName("Hem");
            bean.setLastName("Shailabh");
            bean.setLogin("Hem@gmail.com");
            bean.setPassword("1234");

            model.update(bean);
            
            System.out.println("Updated");
            
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
	}
     
    public static void testList() {

        
            UserBean bean = new UserBean();
            
            try{
            
            	List list = new ArrayList();
            	list = model.list(1, 10);
           
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (UserBean) it.next();
                System.out.println(bean.getId()+"\t");
                System.out.println   (bean.getFirstName()+"\t");
                System.out.println (bean.getLastName()+"\t");
                System.out.println (bean.getLogin()+"\t");
                System.out.println  (bean.getPassword()+"\t");
                System.out.println  (bean.getDob()+"\t");
                System.out.println(bean.getRoleId()+"\t");
                System.out.println   (bean.getUnSuccessfulLogin()+"\t");
                System.out.println  (bean.getGender()+"\t");
                System.out.println(bean.getLastLogin()+"\t");
                System.out.println  (bean.getLock()+"\t");
                System.out.println(bean.getMobileNo()+"\t");
                System.out.println(bean.getCreatedBy()+"\t");
                System.out.println(bean.getModifiedBy()+"\t");
                System.out.println   (bean.getCreatedDatetime()+"\t");
                System.out.println (bean.getModifiedDatetime()+"\t"+"\n");
                
                
                
            }
            
            }catch (Exception e) {
				// TODO: handle exception
            	e.printStackTrace();
			}
    }
    
    public static void testSearch() {

        try {
            UserBean bean = new UserBean();
            List list = new ArrayList();
            bean.setFirstName("Rahul");
            list = model.search(bean);
            if (list.size() < 0) {
                
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                bean = (UserBean) it.next();
                
            }

        } catch (ApplicationException e) {
            e.printStackTrace();
        }

    }
 
    
    public static void testChangePassword(){
    	try{
    		UserBean bean = new UserBean();
    		
    		long id = 3;
    		String oldPassword = "1234";
    		String newPassword = "12345";
    		
    		model.changePassword(id, oldPassword, newPassword);
    		System.out.println("Password Updated");
    		
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
    
    public static void testForgetPassword() {
    	
    	UserBean bean = new UserBean();
    	
    	try {
			model.forgetPassword("ik@123.com");
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    

    public static void main(String[] args) throws ApplicationException {
		
    	testChangePassword();
   
}
    
	}				
