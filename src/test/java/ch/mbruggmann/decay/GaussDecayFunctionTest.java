package ch.mbruggmann.decay;

import org.junit.Before;
import org.junit.Test;

import static ch.mbruggmann.decay.GaussDecayFunction.DEFAULT_DECAY;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class GaussDecayFunctionTest {

  private static final int ORIGIN = 40;
  private static final int SCALE = 5;
  private static final double DECAY = 0.5;
  private static final int OFFSET = 5;

  private GaussDecayFunction func;

  @Before
  public void setUp() {
    func = new GaussDecayFunction(ORIGIN, SCALE, DECAY, OFFSET);
  }

  @Test
  public void origin() {
    assertThat(func.evaluate(ORIGIN), is(1.0));
  }

  @Test
  public void offset() {
    assertThat(func.evaluate(ORIGIN + OFFSET), is(1.0));
    assertThat(func.evaluate(ORIGIN + 0.5 * OFFSET), is(1.0));
    assertThat(func.evaluate(ORIGIN - OFFSET), is(1.0));
    assertThat(func.evaluate(ORIGIN - 0.5 * OFFSET), is(1.0));
  }

  @Test
  public void scale() {
    assertThat(func.evaluate(ORIGIN + (OFFSET + SCALE)), is(DECAY));
    assertThat(func.evaluate(ORIGIN - (OFFSET + SCALE)), is(DECAY));
  }

  @Test
  public void outside() {
    assertThat(func.evaluate(ORIGIN + 2 * (OFFSET + SCALE)), is(closeTo(0.0, 0.01)));
    assertThat(func.evaluate(ORIGIN - 2 * (OFFSET + SCALE)), is(closeTo(0.0, 0.01)));
  }

  @Test
  public void curve() {
    assertThat(func.evaluate(ORIGIN + OFFSET + 0.25 * SCALE), is(closeTo(0.95, 0.01)));
    assertThat(func.evaluate(ORIGIN + OFFSET + 0.5 * SCALE), is(closeTo(0.84, 0.01)));
    assertThat(func.evaluate(ORIGIN + OFFSET + 0.75 * SCALE), is(closeTo(0.67, 0.01)));
    assertThat(func.evaluate(ORIGIN + OFFSET + 1.25 * SCALE), is(closeTo(0.33, 0.01)));
    assertThat(func.evaluate(ORIGIN + OFFSET + 1.5 * SCALE), is(closeTo(0.21, 0.01)));
    assertThat(func.evaluate(ORIGIN + OFFSET + 1.75 * SCALE), is(closeTo(0.11, 0.01)));
  }

  @Test
  public void defaults() {
    final GaussDecayFunction func = new GaussDecayFunction(13, 2);
    assertThat(func.evaluate(11), is(DEFAULT_DECAY));
  }

}
