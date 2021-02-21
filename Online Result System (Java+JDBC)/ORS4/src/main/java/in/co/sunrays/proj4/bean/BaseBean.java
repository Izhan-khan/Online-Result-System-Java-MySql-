package in.co.sunrays.proj4.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import in.co.sunrays.proj4.bean.DropdownListBean;

public abstract class BaseBean implements DropdownListBean,Comparable<BaseBean>,Serializable {

	protected long id;
	protected String createdBy, modifiedBy;
	protected Timestamp createdDatetime, modifiedDatetime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public int compareTo(BaseBean next) {
		// TODO Auto-generated method stub
		return getValue().compareTo(next.getValue());
	}

}
