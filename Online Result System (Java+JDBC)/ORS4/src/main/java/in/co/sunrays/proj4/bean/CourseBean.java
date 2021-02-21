
package in.co.sunrays.proj4.bean;

import in.co.sunrays.proj4.bean.BaseBean;

public class CourseBean extends BaseBean {
	private String courseName;
	private String description;
	private long Duration;

	public long getDuration() {
		return Duration;
	}

	public void setDuration(long Duration) {
		this.Duration = Duration;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getKey() {
		return id + "";
	}

	@Override
	public String getValue() {
		return courseName;
	}

}
