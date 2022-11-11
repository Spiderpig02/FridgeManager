// package fridgemanager.core;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

// import java.time.LocalDate;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import fridgemanager.core.Food;

// /** Unit test Food. */
// public class FoodTest {

//   Food paprika;

//   /** Initilize Food variables */
//   @BeforeEach
//   void init() {
//     paprika = new Food("Paprika",4, LocalDate.of(2022,1,28),"Ola");
//     paprika.setUnit("stk");
//   }

//   /** Simple constructortest */
//   @Test
//   public void testConstructor() {

//     // Checking if the initilized variables are correct
//     assertEquals(4, paprika.getQuantity());
//     assertEquals("Paprika", paprika.getName());
//     assertEquals("Ola", paprika.getOwner());
//     assertEquals(LocalDate.of(2022,1,28), paprika.getExpirationDate());

//     // Testing IllegalArgumentException for negative input
//     assertThrows(IllegalArgumentException.class, () -> {
//       Food laks = new Food("Laks",-4, LocalDate.of(2022,1,28),"Ola");
//     });
//   }

//   /**
//    * Test changeQuantity.
//   */
//   @Test
//   public void testChangeQuantity() {

//     paprika.changeQuantity(1500);

//     // Check if the quantity is changed
//     assertEquals(1500, paprika.getQuantity());

//     // Test IllegalArgumentException for negative input
//     assertThrows(
//         IllegalArgumentException.class,
//         () -> {
//           paprika.changeQuantity(-2);
//         });
//   }

//   @Test
//   public void testSetQuantity() {
//     paprika.setQuantity(1500);

//     // Check if the quantity is changed
//     assertEquals(1500, paprika.getQuantity());

//     // Test IllegalArgumentException for negative input
//     assertThrows(
//         IllegalArgumentException.class,
//         () -> {
//           paprika.setQuantity(-2);
//         });
//   }

//   /**
//    * Test toString method.
//   */
//   @Test
//   public void testToString() {
//     assertEquals(paprika.toString(),paprika.getQuantity()+ " " + paprika.getUnit() + " " +paprika.getName()+", "+paprika.getOwner()+" sin, går ut: "+paprika.getExpirationDate().toString());
//   }
// }
