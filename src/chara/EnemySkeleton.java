package chara;

public class EnemySkeleton extends EnemyBase {

  /**
   * コンストラクタ.
   */
  public EnemySkeleton() {
    setName("ガイコツ");
    setHitPoint(100);
    setAttack(15);
    setDefense(10);
    setMagicAttack(5);
    setMagicDefense(10);
    setMaxHitPoint(100);
  }

  @Override
  public int getReward() {
    return 50;
  }

}
