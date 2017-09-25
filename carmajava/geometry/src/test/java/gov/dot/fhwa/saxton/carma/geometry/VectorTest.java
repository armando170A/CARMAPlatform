package gov.dot.fhwa.saxton.carma.geometry;

import gov.dot.fhwa.saxton.carma.geometry.cartesian.Point;
import gov.dot.fhwa.saxton.carma.geometry.cartesian.Point2D;
import gov.dot.fhwa.saxton.carma.geometry.cartesian.Point3D;
import gov.dot.fhwa.saxton.carma.geometry.cartesian.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by mcconnelms on 9/25/17.
 */
public class VectorTest {
  Log log;

  @Before
  public void setUp() throws Exception {
    log = LogFactory.getLog(HaversineStrategyTest.class);
    log.info("Setting up tests for HaversineStrategy");
  }

  @After
  public void tearDown() throws Exception {
  }

  /**
   * Tests the constructors
   * @throws Exception
   */
  @Test
  public void testConstructors() throws Exception {

    // Two point constructor
    Point head = new Point3D(1,0,0);
    Point tail = new Point3D(0,0,0);
    Vector v = new Vector(head, tail);
    assertTrue(v.getNumDimensions() == 3);
    assertTrue(Math.abs(v.getDim(0) - 1) < 0.0000001);

    tail = new Point(new double[]{1,1});
    try {
      Vector tmp = new Vector(head, tail);
    } catch (IllegalArgumentException e) {
      assertTrue("Caught exception as expected", 1 == 1);
    }

    // Single point constructor
    v = new Vector(head);
    assertTrue(v.getNumDimensions() == 3);
    assertTrue(Math.abs(v.getDim(0) - 1) < 0.0000001);

    // Deep copy constructor
    Vector v2 = new Vector(v);
    assertTrue(v2 != v);
    assertTrue(v2.getNumDimensions() == 3);
    assertTrue(Math.abs(v2.getDim(0) - 1) < 0.0000001);
  }

  /**
   * Tests the magnitude function
   * @throws Exception
   */
  @Test
  public void testMagnitude() throws Exception {
    Point head = new Point3D(1,0,0);
    Point tail = new Point3D(0,0,0);
    Vector v = new Vector(head, tail);
    assertTrue(Math.abs(v.magnitude() - 1.0) < 0.0000001);

    head = new Point3D(0,0,0);
    tail = new Point3D(0,0,0);
    v = new Vector(head, tail);
    assertTrue(Math.abs(v.magnitude() - 0.0) < 0.0000001);

    head = new Point2D(0,0);
    tail = new Point2D(3,4);
    v = new Vector(head, tail);
    assertTrue(Math.abs(v.magnitude() - 5.0) < 0.0000001);
  }

  /**
   * Tests the add function
   * @throws Exception
   */
  @Test
  public void testAdd() throws Exception {
    Point head = new Point3D(1,0,0);
    Vector v = new Vector(head);
    Vector v2 = new Vector(new Point3D(2,4,-1));
    v = v.add(v2);
    assertTrue(Math.abs(v.getDim(0) - 3.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(1) - 4.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(2) - -1.0) < 0.0000001);

    // Test dimension mismatch
    head = new Point3D(0,0,0);
    v = new Vector(head);
    v2 = new Vector(new Point2D(0,0));
    v = v.add(v2);
    assertTrue(Math.abs(v.getDim(0) - 0.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(1) - 0.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(2) - 0.0) < 0.0000001);
  }

  /**
   * Tests the subtract function
   * @throws Exception
   */
  @Test
  public void testSubtract() throws Exception {
    Point head = new Point3D(1,0,0);
    Vector v = new Vector(head);
    Vector v2 = new Vector(new Point3D(2,4,-1));
    v = v.subtract(v2);
    assertTrue(Math.abs(v.getDim(0) - -1.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(1) - -4.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(2) - 1.0) < 0.0000001);

    // Test dimension mismatch
    head = new Point3D(0,0,0);
    v = new Vector(head);
    v2 = new Vector(new Point2D(0,0));
    v = v.subtract(v2);
    assertTrue(Math.abs(v.getDim(0) - 0.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(1) - 0.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(2) - 0.0) < 0.0000001);
  }

  /**
   * Tests the scalarMultiply function
   * @throws Exception
   */
  @Test
  public void testScalarMultiply() throws Exception {
    Point head = new Point3D(1,-2.0,0);
    Vector v = new Vector(head);
    v = v.scalarMultiply(5);
    assertTrue(Math.abs(v.getDim(0) - 5.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(1) - -10.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(2) - 0.0) < 0.0000001);
  }

  /**
   * Tests the elementWiseMultiply function
   * @throws Exception
   */
  @Test
  public void testElementWiseMultiply() throws Exception {
    Point head = new Point3D(1,-2.0,0);
    Vector v = new Vector(head);
    Vector v2 = new Vector(new Point3D(2,3,4));
    v = v.elementWiseMultiply(v2);
    assertTrue(Math.abs(v.getDim(0) - 2.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(1) - -6.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(2) - 0.0) < 0.0000001);

    // Test dimension mismatch
    head = new Point3D(0,0,0);
    v = new Vector(head);
    v2 = new Vector(new Point2D(0,0));
    v = v.subtract(v2);
    assertTrue(Math.abs(v.getDim(0) - 0.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(1) - 0.0) < 0.0000001);
    assertTrue(Math.abs(v.getDim(2) - 0.0) < 0.0000001);
  }

  /**
   * Tests the dot function
   * @throws Exception
   */
  @Test
  public void testDotProduct() throws Exception {
    Point head = new Point3D(1,-2.0,0);
    Vector v = new Vector(head);
    Vector v2 = new Vector(new Point3D(2,3,4));
    assertTrue(Math.abs(v.dot(v2) - -4.0) < 0.0000001);

    head = new Point3D(0,0,0);
    v = new Vector(head);
    v2 = new Vector(new Point3D(0,0, 0));
    v = v.subtract(v2);
    assertTrue(Math.abs(v.dot(v2) - 0.0) < 0.0000001);

    // Test dimension mismatch
    try {
      Vector tmp = new Vector(new Point2D(0,0));
      v.dot(tmp);
      fail("Exception was not caught");
    } catch (IllegalArgumentException e) {
      assertTrue("Caught exception as expected", 1 == 1);
    }
  }

  /**
   * Tests the getUnitVector function
   * @throws Exception
   */
  @Test
  public void testGetUnitVector() throws Exception {
    Point head = new Point3D(9,9,9);
    Vector v = new Vector(head);
    Vector unitV = v.getUnitVector();
    assertTrue(Math.abs(unitV.getDim(0) - (1.0 / Math.sqrt(3.0))) < 0.0000001);
    assertTrue(Math.abs(unitV.getDim(1) - (1.0 / Math.sqrt(3.0))) < 0.0000001);
    assertTrue(Math.abs(unitV.getDim(2) - (1.0 / Math.sqrt(3.0))) < 0.0000001);
  }
}
