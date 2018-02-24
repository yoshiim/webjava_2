package chara;

public class EnemyDemon extends EnemyBase {

  /**
   * コンストラクタ.
   */
  public EnemyDemon() {
    setName("悪魔");
    setHitPoint(100);
    setAttack(5);
    setDefense(5);
    setMagicAttack(15);
    setMagicDefense(15);
    setMaxHitPoint(100);
  }

  @Override
  public int getReward() {
    return 50;
  }

}
