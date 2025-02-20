/*

  Encapsulation 
  - Wrapping of data an d methids into a single unit.
  - It happens in Class


  Access modifiers
  - default
  - public
  - private
  - protected
 */

class Encapsulation_and_AccessModifiers {

    public static void main(String[] args) {
        System.out.println("Encapsulation and Access Mofifiers.");

        Student s1 = new Student();
        s1.setStudent(1, "Shyam", 18);
        s1.getStudent();
    }
}