package chara;

public class EnemyGoblin extends EnemyBase {

  /**
   * コンストラクタ.
   */
  public EnemyGoblin() {
    setName("ゴブリン");
    setHitPoint(100);
    setAttack(20);
    setDefense(10);
    setMagicAttack(5);
    setMagicDefense(5);
    setMaxHitPoint(100);
  }

  @Override
  public int getReward() {
    return 50;
  }

}
