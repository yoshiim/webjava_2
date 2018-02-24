package chara;

public abstract class Character {

  /** 名前. */
  private String name;
  /** HP. */
  private int hitPoint;
  /** 攻撃力. */
  private int attack;
  /** 魔法攻撃力. */
  private int magicAttack;
  /** 防御力. */
  private int defense;
  /** 魔法防御力. */
  private int magicDefense;
  /** 最大HP. */
  protected int maxHitPoint;

  /**
   * 名前を設定.
   * @param name 名前.
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * 名前を取得.
   * @return 名前.
   */
  public String getName() {
    return this.name;
  }

  /**
   * HPを設定.
   * @param hitPoint HP.
   */
  public void setHitPoint(final int hitPoint) {
    this.hitPoint = hitPoint;
  }

  /**
   * HPを取得.
   * @return HP.
   */
  public int getHitPoint() {
    return this.hitPoint;
  }

  /**
   * 攻撃力を設定.
   * @param attack 攻撃力.
   */
  public void setAttack(final int attack) {
    this.attack = attack;
  }

  /**
   * 攻撃力を取得.
   * @return 攻撃力.
   */
  public int getAttack() {
    return this.attack;
  }

  /**
   * 魔法攻撃力を設定.
   * @param magicAttack 魔法攻撃力.
   */
  public void setMagicAttack(final int magicAttack) {
    this.magicAttack = magicAttack;
  }

  /**
   * 魔法攻撃力を取得.
   * @return 魔法攻撃力.
   */
  public int getMagicAttack() {
    return this.magicAttack;
  }

  /**
   * 防御力を設定.
   * @param defense  防御力.
   */
  public void setDefense(final int defense) {
    this.defense = defense;
  }

  /**
   * 防御力を取得.
   * @return 防御力.
   */
  public int getDefense() {
    return this.defense;
  }

  /**
   * 魔法防御力を設定.
   * @param magicDefense  魔法防御力.
   */
  public void setMagicDefense(final int magicDefense) {
    this.magicDefense = magicDefense;
  }

  /**
   * 魔法防御力を取得.
   * @return 魔法防御力.
   */
  public int getMagicDefense() {
    return this.magicDefense;
  }

  /**
   * 最大HPを設定.
   * @param maxHitPoint 最大HP.
   */
  public void setMaxHitPoint(final int maxHitPoint) {
    this.maxHitPoint = maxHitPoint;
  }

  /**
   * 最大HPを取得.
   * @return 最大HP.
   */
  public int getMaxHitPoint() {
    return maxHitPoint;
  }
}
