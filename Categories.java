public class Categories {
    private String car;
    private String school;
    private String house;
    
    public Categories(String house, String school, String car){
        this.house = house;
        this.school = school;
        this.car = car;
    }
    
    public String getCar() {
        return car;
    }
    public void setCar(String car) {
        this.car = car;
    }
    public String getSchool() {
        return school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getHouse() {
        return house;
    }
    public void setHouse(String house) {
        this.house = house;
    }
    
    @Override
    public String toString() {
        return "House: " + house + ", School: " + school + ", Car: " + car;
    }
}
