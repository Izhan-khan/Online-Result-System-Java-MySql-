package in.co.sunrays.proj4.bean;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.DropdownListBean;

public class MarksheetBean extends BaseBean implements DropdownListBean {

	protected String rollNo, name;
	protected long studentId;
	protected int physics, chemistry, maths;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studenId) {
		this.studentId = studenId;
	}

	public int getPhysics() {
		return physics;
	}

	public void setPhysics(int physics) {
		this.physics = physics;
	}

	public int getChemistry() {
		return chemistry;
	}

	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	public int getMaths() {
		return maths;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
