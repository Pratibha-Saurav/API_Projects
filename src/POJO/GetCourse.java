package POJO;

public class GetCourse {

	//public static void main(String[] args) {
		
		private String url;
		private String services;
		private String expertise;
		private Courses Courses;    //return type is the class of this variable or object of this variable is coming as object of courses class. this variable courses is a mini json
		private String instructor;
		private String linkedin;
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getServices() {
			return services;
		}
		public void setServices(String services) {
			this.services = services;
		}
		public String getExpertise() {
			return expertise;
		}
		public void setExpertise(String expertise) {
			this.expertise = expertise;
		}
		public POJO.Courses getCourses() {
			return Courses;
		}
		public void setCourses(POJO.Courses courses) {
			Courses = courses;
		}
		public String getInstructor() {
			return instructor;
		}
		public void setInstructor(String instructor) {
			this.instructor = instructor;
		}
		public String getLinkedin() {
			return linkedin;
		}
		public void setLinkedin(String linkedin) {
			this.linkedin = linkedin;
		}
	
		
		
		
		
		

	}
