/*
 * 
  Different Types of Inheritance
 1. single - B-> A      class B extends A 
 2. multilevel - C  -> B-> A      class C extends B and  class B extends A 
 3. hierarchical - C,B-> A      class C extends A, class B extends A 
 4. Multiple - C-> A, B      class C extends B, A


 NOTE: This Multiple inheritance is not allowed in Java(but is allowed in C++).

 NOTE: But  Multiple inheritance can be possible   in Java using Interfaces
 */

 interface A{}
 interface B{}

 interface C {
 }
 
//  multiple Inheritance with interfaces
public class MultipleInheritanceExample  implements  A,B,C{
    
}
