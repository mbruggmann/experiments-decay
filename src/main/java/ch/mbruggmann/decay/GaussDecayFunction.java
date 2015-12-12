package ch.mbruggmann.decay;

/**
 * A gaussian decay function.
 *
 * This decay function assigns a score between 0.0 and 1.0 to any input value depending on the distance to origin.
 */
public final class GaussDecayFunction {

  static final double DEFAULT_OFFSET = 0;
  static final double DEFAULT_DECAY = 0.5;

  private final double origin;
  private final double scale;
  private final double decay;
  private final double offset;

  /**
   * Create a new gaussian decay function with offset {@link #DEFAULT_OFFSET} and decay {@link #DEFAULT_DECAY}.
   * @param origin The central point. Values that fall at the origin will get a full score of 1.0.
   * @param scale How quickly the score should drop the further from the origin a value is.
   * @see {@link #GaussDecayFunction(double, double, double, double)}}
   */
  public GaussDecayFunction(double origin, double scale) {
    this(origin, scale, DEFAULT_DECAY, DEFAULT_OFFSET);
  }

  /**
   * Create a new gaussian decay function.
   * @param origin The central point. Values that fall at the origin will get a full score of 1.0.
   * @param scale How quickly the score should drop the further from the origin a value is.
   * @param decay The score that a value at scale distance from the origin should receive.
   * @param offset Expands the origin to cover a range of values. All values in the range will receive score 1.0.
   */
  public GaussDecayFunction(double origin, double scale, double decay, double offset) {
    this.origin = origin;
    this.scale = scale;
    this.decay = decay;
    this.offset = offset;
  }

  /**
   * Evaluate this decay function for a value.
   * @param value the value to evaluate the function for.
   * @return the score for this value.
   */
  public double evaluate(final double value) {
    final double distance = Math.max(0.0d, Math.abs(value - origin) - offset);
    return Math.exp(0.5 * Math.pow(distance, 2.0) / (0.5 * Math.pow(scale, 2.0) / Math.log(decay)));
  }

}
